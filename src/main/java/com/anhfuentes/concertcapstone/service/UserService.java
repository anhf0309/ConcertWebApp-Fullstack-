package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.dto.UserDTO;
import com.anhfuentes.concertcapstone.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
     boolean create(UserDTO userDTO);
     User findUserByEmail(String email);
     List<UserDTO> getAllUsers();
     boolean deleteUserAndRolesById(Long userId);
     UserDTO getUserById(Long userId);
     void updateUser(UserDTO userDTO);


}