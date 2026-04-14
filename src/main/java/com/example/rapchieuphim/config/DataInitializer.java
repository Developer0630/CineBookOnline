package com.example.rapchieuphim.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.rapchieuphim.model.ERole;
import com.example.rapchieuphim.model.Role;
import com.example.rapchieuphim.repositories.RoleRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            // Kiểm tra nếu chưa có ROLE_ADMIN thì mới thêm
            if (roleRepository.findByName(ERole.ROLE_ADMIN).isEmpty()) {
                roleRepository.save(new Role(ERole.ROLE_ADMIN));
                System.out.println("--- Đã khởi tạo ROLE_ADMIN ---");
            }
            // Kiểm tra nếu chưa có ROLE_USER thì mới thêm
            if (roleRepository.findByName(ERole.ROLE_USER).isEmpty()) {
                roleRepository.save(new Role(ERole.ROLE_USER));
                System.out.println("--- Đã khởi tạo ROLE_USER ---");
            }
        };
    }
}