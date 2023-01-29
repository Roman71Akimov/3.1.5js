package com.example.demo.services;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User updateUser) {
        User user = userRepository.getById(updateUser.getId());
        if (!user.getPassword().equals(updateUser.getPassword())) {
            updateUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        }
        userRepository.save(updateUser);
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseGet(User::new);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new IllegalArgumentException("Email not found!!"));
    }
}
