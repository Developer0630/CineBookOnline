package com.example.rapchieuphim.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.rapchieuphim.model.Movie;
import com.example.rapchieuphim.repositories.MovieRepository;
import com.example.rapchieuphim.repositories.UserRepository;
import java.security.Principal;
import com.example.rapchieuphim.model.User;
@Controller
@RequestMapping("/admin/movies")
public class AdminMovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String listMovies(Model model) {
        
            List<Movie> movies = movieRepository.findAll();
           
        
        
        model.addAttribute("movies", movies);
        return "admin/admin_movies";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "admin/movie_form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) return "redirect:/admin/movies";

        model.addAttribute("movie", movie);
        return "admin/movie_form";
    }

    @PostMapping("/save")
    public String saveMovie(@ModelAttribute Movie movie) {
        movieRepository.save(movie);
        return "redirect:/admin/movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieRepository.deleteById(id);
        return "redirect:/admin/movies";
    }
}