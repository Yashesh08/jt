package com.example.jttermwork.service.impl;

import com.example.jttermwork.dto.UserRequest;
import com.example.jttermwork.entity.Role;
import com.example.jttermwork.entity.RoleName;
import com.example.jttermwork.entity.User;
import com.example.jttermwork.repository.RoleRepository;
import com.example.jttermwork.repository.UserRepository;
import com.example.jttermwork.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(resolveRoles(request.getRoles()))
                .build();

        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public User update(Long id, UserRequest request) {
        User user = getById(id);
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(resolveRoles(request.getRoles()));
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private Set<Role> resolveRoles(Set<RoleName> roleNames) {
        Set<RoleName> safeRoles = (roleNames == null || roleNames.isEmpty())
                ? Set.of(RoleName.ROLE_STUDENT)
                : roleNames;

        return safeRoles.stream()
                .map(name -> roleRepository.findByName(name)
                        .orElseGet(() -> roleRepository.save(Role.builder().name(name).build())))
                .collect(Collectors.toSet());
    }
}
