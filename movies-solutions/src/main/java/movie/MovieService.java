package movie;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class MovieService {

    private List<Movie> movies;

    public MovieService(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void save(Movie movie) {
        movies.add(movie);
    }

    public Movie getLatestMovie() {
        return movies.stream()
                .sorted(Comparator.comparing(Movie::getReleaseDate).reversed())
                .limit(1)  // van olyan, hogy findFirst()
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Nincs ilyen elem!"));
    }

    public Movie getLatestMovie2() {
        return movies.stream()
                .max(Comparator.comparing(Movie::getReleaseDate))
                .orElseThrow(() -> new IllegalArgumentException("movie.Movie not found"));
    }

    public List <Movie> getMovieByName(String name) {
        return movies.stream()
                .filter(m -> m.getName().contains(name))
                .collect(Collectors.toList());
    }


}