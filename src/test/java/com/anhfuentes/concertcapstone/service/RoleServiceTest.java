package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Role;
import com.anhfuentes.concertcapstone.repository.RoleRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService; // Replace RoleServiceImpl with your service class name

    private List<Role> sampleRoles;

    @BeforeEach
    public void setUp() {
        // Initialize your sample roles here
        Role role1 = new Role();
        Role role2 = new Role();
        sampleRoles = Arrays.asList(role1, role2);
    }

    @Test
    public void getRolesByUser_ShouldReturnRolesList() {

        long userId = 1L;
        when(roleRepository.findRoleByUser(userId)).thenReturn(sampleRoles);

        List<Role> result = roleService.getRolesByUser(userId);

        verify(roleRepository).findRoleByUser(userId);
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).containsAll(sampleRoles);
    }
}