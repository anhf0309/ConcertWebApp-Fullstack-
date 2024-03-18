package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.User;

public interface UserService {
    User createUser(User user);
    User getUserByUsername(String username);
}
