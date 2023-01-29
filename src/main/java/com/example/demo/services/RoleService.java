package com.example.demo.services;



import com.example.demo.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

//    Role getRoleByName(String name);

    void saveRole(Role role);
}
