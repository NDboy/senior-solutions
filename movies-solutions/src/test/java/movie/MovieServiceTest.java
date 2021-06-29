package movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    MovieService movieService;

    @BeforeEach
    void setUp() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Pulp Fiction", 178, LocalDate.of(1995, 4,13)));
        movies.add(new Movie("The Hateful Eight", 182, LocalDate.of(2015, 12,25)));
        movies.add(new Movie("From Dusk Till Dawn", 108, LocalDate.of(1996, 1,19)));
        movieService = new MovieService(movies);
    }

    @Test
    void testSave() {
        movieService.save(new Movie("Django Unchained", 165, LocalDate.of(2012,12,25)));
        Movie savedMovie = movieService.getMovies().get(3);

        assertEquals(4, movieService.getMovies().size());
        assertEquals("Django Unchained", savedMovie.getName());
    }

    @Test
    void testGetLatestMovie() {
        Movie latestMovie = movieService.getLatestMovie();
        Movie latestMovie2 = movieService.getLatestMovie2();

        assertEquals("The Hateful Eight", latestMovie.getName());
        assertEquals("The Hateful Eight", latestMovie2.getName());
    }

    @Test
    void testGetMovieByName() {
        List<Movie> moviesByName = movieService.getMovieByName("ul");
        assertEquals(2, moviesByName.size());
    }
}