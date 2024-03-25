package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Role;
import com.anhfuentes.concertcapstone.repository.RoleRepository;
import com.anhfuentes.concertcapstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
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

    @Transactional
    public boolean deleteRole(Long roleId) {
        // Check if any users are assigned this role
        boolean isRoleAssignedToAnyUser = userRepository.existsByRolesId(roleId);
        if (isRoleAssignedToAnyUser) {
            // Remove the role from those users or inform the user that the role is in use
            return false;
        } else {
            // Safe to delete the role
            roleRepository.deleteById(roleId);
            return true;
        }
    }
}