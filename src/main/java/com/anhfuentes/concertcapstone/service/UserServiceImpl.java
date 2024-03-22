package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.dto.UserDTO;
import com.anhfuentes.concertcapstone.model.Role;
import com.anhfuentes.concertcapstone.model.User;
import com.anhfuentes.concertcapstone.repository.RoleRepository;
import com.anhfuentes.concertcapstone.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, PasswordEncoder passwordEncoder1){
        super();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void create(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getFirstName() + " " + userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Role role = roleRepository.findRoleByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDTO(user))
                .collect(Collectors.toList());
    }

    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        String[] str = user.getName().split(" ");
        userDTO.setFirstName(str[0]);
        userDTO.setLastName(str[1]);
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

}