package com.anhfuentes.concertcapstone.repository;

import com.anhfuentes.concertcapstone.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setup() {
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        entityManager.persist(adminRole);

        Role userRole = new Role();
        userRole.setName("USER");
        entityManager.persist(userRole);
    }

    @Test
    public void whenFindRoleByName_thenReturnRole() {
        Role found = roleRepository.findRoleByName("ADMIN");
        assertThat(found.getName()).isEqualTo("ADMIN");
    }

}
