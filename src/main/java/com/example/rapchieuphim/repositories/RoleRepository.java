package com.example.rapchieuphim.repositories;

import com.example.rapchieuphim.model.ERole;
import com.example.rapchieuphim.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    // Tìm quyền dựa vào tên Enum (Ví dụ: tìm ROLE_USER)
    Optional<Role> findByName(ERole name);
}