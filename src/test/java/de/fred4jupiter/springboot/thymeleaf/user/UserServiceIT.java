package de.fred4jupiter.springboot.thymeleaf.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceIT {

    @Autowired
    private UserService userService;

    @Test
    public void loadUsersFromFile() {
        Collection<UserDetails> userDetails = userService.loadUsersFromFile();
        assertNotNull(userDetails);
        assertThat(userDetails.size(), greaterThanOrEqualTo(1));
    }
}
