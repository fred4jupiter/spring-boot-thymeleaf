package de.fred4jupiter.springboot.thymeleaf.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "demo")
public class UserRolesProperties {

    private List<LocalUser> users = new ArrayList<>();

    public List<LocalUser> getUsers() {
        return users;
    }

    public void setUsers(List<LocalUser> users) {
        this.users = users;
    }
}
