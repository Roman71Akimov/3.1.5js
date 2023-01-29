package com.example.demo.services;

import com.example.demo.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import com.example.demo.model.Role;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return new ArrayList<>(roleRepository.findAll());
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleRepository.save(role);
    }


}

