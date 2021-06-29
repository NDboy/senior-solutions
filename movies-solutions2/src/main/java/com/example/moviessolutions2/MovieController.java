package com.example.moviessolutions2;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getMovies() {
        return movieService.listMovies();
    }

    @GetMapping("/{id}")
    public MovieDto findMovieById(@PathVariable("id") long id) {
        return movieService.findMovieById(id);
    }

//    @GetMapping
//    public List<EmployeeDto> listEmployees(@RequestParam Optional<String> prefix) {
//        return employeesService.listEmployees(prefix);
//    }

//    @GetMapping
//    public List<MovieDto> getMovies2(@RequestParam Optional<String> partOfTitle) {
//        return movieService.listMoviesByTitle(partOfTitle);
//    }

    @PostMapping
    public MovieDto createMovie(@RequestBody CreateMovieCommand command) {
        return movieService.createMovie(command);
    }

    @PostMapping("/{id}/rating")
    public MovieDto updateMovieRating(@PathVariable("id") long id, @RequestBody NewMovieRatingCommand command) {
        return movieService.addRating(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") long id) {
        movieService.deleteMovie(id);
    }


}
