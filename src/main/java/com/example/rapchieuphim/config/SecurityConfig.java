// File: config/SecurityConfig.java
package com.example.rapchieuphim.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
private UserDetailsService userDetailsService;
    // 1. Cấu hình băm mật khẩu (Bạn đã làm ở bước trước, mình chuyển vào đây cho gọn)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Cấu hình Provider để Spring biết dùng Class Service nào và Encoder 
    // 2. Cấu hình Provider cho phiên bản Spring Security Mới
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        // Đưa thẳng userDetailsService vào trong ngoặc theo đúng yêu cầu của Spring
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        
        // Chỉ set PasswordEncoder, tuyệt đối KHÔNG viết dòng ép kiểu (UserDetailsPasswordService) nào ở đây
        authProvider.setPasswordEncoder(passwordEncoder());
        
        return authProvider;
    }

    // 3. Cấu hình Phân quyền (Filter Chain)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Tạm thời tắt CSRF để sinh viên dễ test form (Sau này lên production nên bật lại)
            .csrf(csrf -> csrf.disable()) 
            
            // Cấu hình các đường dẫn
            .authorizeHttpRequests(auth -> auth
                // CÁC ĐƯỜNG DẪN CÔNG KHAI (Ai cũng vào được)
                .requestMatchers("/", "/home", "/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()
                
                // CÁC ĐƯỜNG DẪN CỦA ADMIN (Chỉ ROLE_ADMIN mới vào được)
                // Quan trọng: Tên quyền ở đây không cần chữ ROLE_ ở đầu
                .requestMatchers("/admin/**").hasRole("ADMIN")
                
                // Các đường dẫn còn lại: Phải đăng nhập
                .anyRequest().authenticated()
            )
            
            // Cấu hình Form Login
            .formLogin(form -> form
                .loginPage("/login") // Đường dẫn đến trang login của bạn
                .loginProcessingUrl("/perform_login") // URL do Spring tự xử lý, form HTML trỏ đến đây
                .defaultSuccessUrl("/", true) // Đăng nhập xong về trang chủ
                .failureUrl("/login?error=true") // Sai thì về login báo lỗi
                .permitAll()
            )
            
            // Cấu hình Logout
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            );

        http.authenticationProvider(authenticationProvider());

        return http.build();
    }
}