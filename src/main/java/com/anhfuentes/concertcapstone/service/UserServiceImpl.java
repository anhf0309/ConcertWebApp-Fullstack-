package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.dto.UserDTO;
import com.anhfuentes.concertcapstone.model.Role;
import com.anhfuentes.concertcapstone.model.User;
import com.anhfuentes.concertcapstone.repository.RoleRepository;
import com.anhfuentes.concertcapstone.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        super();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public boolean create(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // Find the role by name
        Role defaultRole = roleRepository.findRoleByName("ROLE_USER");

        // If role not found, create a new one
        if (defaultRole == null) {
            defaultRole = new Role();
            defaultRole.setName("ROLE_USER");
            roleRepository.save(defaultRole);
        }

        // Check if the user should be an admin
        if (userDTO.getEmail().endsWith("@punzelave.com")) {
            Role adminRole = roleRepository.findRoleByName("ROLE_ADMIN");

            // If role not found, create a new one
            if (adminRole == null) {
                adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                roleRepository.save(adminRole);
            }
            user.setRoles(Collections.singletonList(adminRole));
        } else {
            user.setRoles(Collections.singletonList(defaultRole));
        }

        userRepository.save(user);
        return true;
    }


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        System.out.println("Fetched users size: " + users.size()); // Debug log

        List<UserDTO> userDTOs = users.stream()
                .map(this::mapToUserDTO)
                .collect(Collectors.toList());
        System.out.println("User DTOs size: " + userDTOs.size()); // Debug log
        return userDTOs;
    }


    @Transactional
    public boolean deleteUserAndRolesById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return false;
        }
        // Assuming roles are directly managed within the User entity
        user.getRoles().clear(); // Remove the associations to roles
        userRepository.save(user); // Update the user to reflect the removal of roles

        userRepository.deleteById(userId); // Now delete the user
        return true;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return mapToUserDTO(user);
        }
        return null;
    }

    @Transactional
    public void updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);
    }


    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}