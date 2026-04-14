package com.example.rapchieuphim.controllers;

import java.security.Principal;
import java.util.List; // Thêm import này

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.rapchieuphim.model.Movie;
import com.example.rapchieuphim.model.Showtime;
import com.example.rapchieuphim.model.User;
import com.example.rapchieuphim.repositories.MovieRepository;
import com.example.rapchieuphim.repositories.ShowtimeRepository;
import com.example.rapchieuphim.repositories.UserRepository; // Cần lấy user từ DB

@Controller
public class HomeController {

    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
public String home(Principal principal, Model model) {
    if (principal != null) {
        String username = principal.getName();
        // Tìm User trong DB dựa trên username
        User user = userRepository.findByUsername(username).orElse(null);
        // Truyền nguyên đối tượng user sang để index.html không bị lỗi null
        model.addAttribute("user", user); 
    }
    
    List<Movie> movieList = movieRepository.findByActiveTrue();
    model.addAttribute("movies", movieList); 
    
    return "index";
}

    @GetMapping("/movie/detail/{id}")
    public String movieDetail(@PathVariable Long id, Model model) {
        Movie movie = movieRepository.findById(id).orElse(null);
        List<Showtime> showtimes = showtimeRepository.findByMovieId(id);
        
        model.addAttribute("movie", movie);
        model.addAttribute("showtimes", showtimes);
        return "customer/movie_detail";
    }
}