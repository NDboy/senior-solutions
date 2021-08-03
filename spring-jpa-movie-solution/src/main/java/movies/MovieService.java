package movies;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.asm.Type;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService {

    private MovieRepository repository;

    private ModelMapper modelMapper;

    public List<MovieDTO> getMovies() {
        return repository.findAll()
                .stream()
                .map(m -> modelMapper.map(m, MovieDTO.class))
                .collect(Collectors.toList());
    }


    public MovieDTO createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(command.getTitle());
        repository.save(movie);
        return modelMapper.map(movie, MovieDTO.class);
    }

    @Transactional
    public MovieDTO addRating(long id, CreateRatingCommand command) {
        Movie movie = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        movie.calculateRating(command.getRating());

//        repository.save(movie);
        return modelMapper.map(movie, MovieDTO.class);
    }
}
