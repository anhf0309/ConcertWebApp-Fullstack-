package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Role;

import java.util.List;

public interface RoleService {
    void saveRole(Role role);
    Role findRoleByName(String name);
    List<Role> getAllRoles();
    List<Role> getRolesByUser(long id);
    boolean roleExists(String name);
}