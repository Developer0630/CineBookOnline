package com.example.rapchieuphim.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.rapchieuphim.model.Showtime;
import com.example.rapchieuphim.repositories.MovieRepository;
import com.example.rapchieuphim.repositories.ShowtimeRepository;

@Controller
@RequestMapping("/admin/showtimes")
public class AdminShowtimeController {
    
    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private ShowtimeRepository showtimeRepository;

    // ❌ Đã xóa hoàn toàn hàm isAdmin() và HttpSession

    @GetMapping("/add")
    public String showAddShowtimeForm(Model model) {
        model.addAttribute("showtime", new Showtime());
        // Lấy danh sách phim để đổ vào Dropdown
        model.addAttribute("movies", movieRepository.findAll());
        return "admin/showtime_form";
    }

    @PostMapping("/save")
    public String saveShowtime(@ModelAttribute Showtime showtime) {
        // Lưu suất chiếu mới vào database
        showtimeRepository.save(showtime);
        return "redirect:/admin/movies"; 
    }
}