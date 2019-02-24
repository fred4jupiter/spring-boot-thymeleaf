package de.fred4jupiter.springboot.thymeleaf.user;

import de.fred4jupiter.springboot.thymeleaf.props.LocalUser;
import de.fred4jupiter.springboot.thymeleaf.props.UserRolesProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRolesProperties userRolesProperties;

    private List<UserDetails> userDetailsList = new ArrayList<>();

    @PostConstruct
    public void initLoad() {
        LOG.info("Loading users from config...");

        List<LocalUser> users = userRolesProperties.getUsers();

        users.forEach(localUser -> {
            UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder::encode)
                    .username(localUser.getUsername()).password(localUser.getPassword()).roles(localUser.getRolesAsArray()).build();
            userDetailsList.add(userDetails);
        });
    }

    public Collection<UserDetails> loadUsersFromFile() {
        return userDetailsList;
    }
}
