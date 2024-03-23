package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Role;
import com.anhfuentes.concertcapstone.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    @Transactional
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
    @Override
    @Transactional
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public List<Role> getRolesByUser(long id) {
        return roleRepository.findRoleByUser(id);
    }

    @Override
    public boolean roleExists(String name) {
       Role role = roleRepository.findRoleByName(name);
        return role != null;
    }
}