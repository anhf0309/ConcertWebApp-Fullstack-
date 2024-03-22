package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.dto.UserDTO;
import com.anhfuentes.concertcapstone.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
public interface UserService {
    public void create(UserDTO userDTO);
    public User findUserByEmail(String email);
    public List<UserDTO> getAllUsers();
}