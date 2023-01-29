package com.example.demo.services;




import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();
    User getUser(Long id);
    void deleteUser(Long id);
    void saveUser(User user);

    User findByEmail(String email);
     void updateUser(User updateUser);

    List<Role> getAllRoles();
}
