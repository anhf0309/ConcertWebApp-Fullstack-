package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.dto.UserDTO;
import com.anhfuentes.concertcapstone.model.Role;
import com.anhfuentes.concertcapstone.model.User;
import com.anhfuentes.concertcapstone.repository.RoleRepository;
import com.anhfuentes.concertcapstone.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
        return users.stream()
                .map(this::mapToUserDTO)
                .collect(Collectors.toList());
    }

    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

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