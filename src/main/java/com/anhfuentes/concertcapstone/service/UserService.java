package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User createUser(User user);
    User getUserByUsername(String username) throws UsernameNotFoundException;
    User updateUser(User user);
    User findUserByEmail(String email) throws UsernameNotFoundException;
    boolean deleteUser(Long id) throws UsernameNotFoundException;
}
