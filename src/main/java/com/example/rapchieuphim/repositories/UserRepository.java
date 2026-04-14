package com.example.rapchieuphim.repositories;

import com.example.rapchieuphim.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Đã xóa findByUsernameAndPassword

    // TÌM MỚI: Spring Security rất thích dùng Optional để tránh lỗi NullPointerException
    Optional<User> findByUsername(String username);
    
    // Tự động kiểm tra xem username đã tồn tại chưa (Dùng cho lúc Register)
    boolean existsByUsername(String username);

    // THÊM MỚI: Nên kiểm tra cả email xem có ai dùng để đăng ký chưa
    boolean existsByEmail(String email);
}