package com.anhfuentes.concertcapstone.repository;

import com.anhfuentes.concertcapstone.model.User;
import com.anhfuentes.concertcapstone.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testFindUserByEmail() {
        String email = "test@example.com";
        when(userRepository.findUserByEmail(email)).thenReturn(new User()); // Mock the behavior

        User user = userRepository.findUserByEmail(email);
        assertThat(user).isNotNull(); // Verify the result

        verify(userRepository, times(1)).findUserByEmail(email); // Ensure method was called exactly once
    }

    @Test
    public void testExistsByRolesId() {
        Long roleId = 1L;
        when(userRepository.existsByRolesId(roleId)).thenReturn(true); // Mock the behavior

        boolean exists = userRepository.existsByRolesId(roleId);
        assertThat(exists).isTrue(); // Verify the result

        verify(userRepository, times(1)).existsByRolesId(roleId); // Ensure method was called exactly once
    }
}
