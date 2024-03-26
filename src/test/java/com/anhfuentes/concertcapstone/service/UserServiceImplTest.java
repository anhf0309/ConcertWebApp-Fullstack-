package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.dto.UserDTO;
import com.anhfuentes.concertcapstone.model.User;
import com.anhfuentes.concertcapstone.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private List<User> sampleUsers;

    @BeforeEach
    public void setUp() {
        User user1 = new User("hanh","nguyen","hanh@gmail.com","password");
        User user2 = new User("john","mayer","john@gmail.com","password");
        sampleUsers = Arrays.asList(user1, user2);
    }

    @Test
    public void getAllUsers_ShouldReturnUserDTOs() {
        when(userRepository.findAll()).thenReturn(sampleUsers);
        List<UserDTO> result = userService.getAllUsers();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(sampleUsers.size());
        List<Long> expectedIds = sampleUsers.stream().map(User::getId).collect(Collectors.toList());
        List<Long> resultIds = result.stream().map(UserDTO::getId).collect(Collectors.toList());
        assertThat(resultIds).containsExactlyInAnyOrderElementsOf(expectedIds);
    }

}