package com.example.moviessolutions2;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private List<Movie> movies = new ArrayList<>();

    private ModelMapper modelMapper;

    private AtomicLong idGenerator = new AtomicLong();

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public List<MovieDto> listMoviesByTitle(Optional<String> partOfTitle) {

        return movies.stream()
                .filter(m -> partOfTitle.isEmpty() || m.getTitle().toLowerCase().contains(partOfTitle.get().toLowerCase()))
                .map(mov -> modelMapper.map(mov, MovieDto.class)).collect(Collectors.toList());
    }

    public List<MovieDto> listMovies() {
        Type targetListType = new TypeToken<List<MovieDto>>(){}.getType();
        return modelMapper.map(movies, targetListType);

    }

    public MovieDto findMovieById(long id) {
        Movie movieById = movies.stream()
                .filter(m -> m.getId() == id)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Movie not found " + id));
        return modelMapper.map(movieById, MovieDto.class);

    }

    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(idGenerator.incrementAndGet(), command.getTitle(), command.getLength());
        movies.add(movie);

        return modelMapper.map(movie, MovieDto.class);
    }




    public void deleteMovie(long id) {
        Movie movie = movies.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Movie not found: " + id));
        movies.remove(movie);
    }

    private Movie findById(long id) {
        return movies.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

    }

    public MovieDto addRating(long id, NewMovieRatingCommand command) {
        Movie movie = findById(id);
        movie.addRating(command.getRating());

        return modelMapper.map(movie, MovieDto.class);
    }
}