package movies;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDTO> getMovies() {
        return movieService.getMovies();
    }

    @PostMapping
    public MovieDTO createMovie(@RequestBody CreateMovieCommand command) {
        return movieService.createMovie(command);
    }

    @PostMapping("/{id}/rating")
    public MovieDTO updateMovieRating(@PathVariable("id") long id, @RequestBody CreateRatingCommand command) {

        return movieService.addRating(id, command);
    }

}
