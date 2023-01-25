package com.example.demo.admin;

import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.model.User;
import com.example.demo.model.Role;
import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class Admin {

    private final RoleService roleService;
    private final UserService usersService;

    @Autowired
    public Admin(RoleService roleService, UserService usersService) {
        this.roleService = roleService;
        this.usersService = usersService;
    }

    @PostConstruct
    public void initialization() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);
        User admin = new User("admin", "admin", "admin@mail.ru", 12, "admin");
        admin.setRoles(Set.of(roleAdmin));
        usersService.saveUser(admin);

    }
}
