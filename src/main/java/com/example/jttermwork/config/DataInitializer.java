package com.example.jttermwork.config;

import com.example.jttermwork.entity.Role;
import com.example.jttermwork.entity.RoleName;
import com.example.jttermwork.entity.User;
import com.example.jttermwork.repository.RoleRepository;
import com.example.jttermwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseGet(() -> roleRepository.save(Role.builder().name(RoleName.ROLE_ADMIN).build()));
        Role instructorRole = roleRepository.findByName(RoleName.ROLE_INSTRUCTOR)
                .orElseGet(() -> roleRepository.save(Role.builder().name(RoleName.ROLE_INSTRUCTOR).build()));
        Role studentRole = roleRepository.findByName(RoleName.ROLE_STUDENT)
                .orElseGet(() -> roleRepository.save(Role.builder().name(RoleName.ROLE_STUDENT).build()));

        createDefaultUser("Admin User", "admin@jt.local", "admin123", Set.of(adminRole));
        createDefaultUser("Instructor User", "instructor@jt.local", "instructor123", Set.of(instructorRole));
        createDefaultUser("Student User", "student@jt.local", "student123", Set.of(studentRole));
    }

    private void createDefaultUser(String name, String email, String rawPassword, Set<Role> roles) {
        if (userRepository.existsByEmail(email)) {
            return;
        }

        User user = User.builder()
                .fullName(name)
                .email(email)
                .password(passwordEncoder.encode(rawPassword))
                .roles(roles)
                .build();

        userRepository.save(user);
    }
}
