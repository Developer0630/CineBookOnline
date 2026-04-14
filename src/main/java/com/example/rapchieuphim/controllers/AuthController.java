package com.example.rapchieuphim.controllers;

import com.example.rapchieuphim.model.ERole;
import com.example.rapchieuphim.model.Role;
import com.example.rapchieuphim.model.User;
import com.example.rapchieuphim.repositories.RoleRepository;
import com.example.rapchieuphim.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // THÊM MỚI: Công cụ mã hóa mật khẩu và truy xuất quyền
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    // ==========================================
    // PHẦN ĐĂNG KÝ
    // ==========================================
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; 
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, 
                               @RequestParam String email, // Bắt buộc thêm Email
                               @RequestParam String password, 
                               Model model) {
        
        // 1. Kiểm tra trùng lặp
        if (userRepository.existsByUsername(username)) {
            model.addAttribute("error", "Tên tài khoản đã tồn tại!");
            return "register";
        }
        if (userRepository.existsByEmail(email)) {
            model.addAttribute("error", "Email đã được sử dụng!");
            return "register";
        }
        
        // 2. Tạo User mới và MÃ HÓA MẬT KHẨU
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password)); // Biến "123456" thành "$2a$10$..."

        // 3. Phân quyền (Role) kiểu mới
        Set<Role> roles = new HashSet<>();
        
        if (username.equalsIgnoreCase("admin")) {
            // Lấy quyền ADMIN từ Database
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Lỗi: Không tìm thấy Role Admin."));
            roles.add(adminRole);
        } else {
            // Lấy quyền USER từ Database
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Lỗi: Không tìm thấy Role User."));
            roles.add(userRole);
        }
        
        newUser.setRoles(roles); // Gán danh sách quyền cho User
        userRepository.save(newUser); // Lưu vào database
        
        model.addAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
        return "login";
    }

    // ==========================================
    // PHẦN ĐĂNG NHẬP & ĐĂNG XUẤT
    // ==========================================
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    /* * LƯU Ý QUAN TRỌNG: 
     * Mình tạm thời comment phần xử lý @PostMapping("/login") và logout lại.
     * Lý do: Khi dùng Spring Security và JWT, việc kiểm tra đăng nhập và quản lý 
     * Session/Token sẽ do các bộ lọc (Filter) của Spring Security tự động đảm nhận. 
     * Chúng ta sẽ không tự so sánh mật khẩu bằng tay trong Controller nữa.
     */
}