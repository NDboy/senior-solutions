package movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ElementCollection
    private List<Integer> ratings;

    private double averageRating;

    public Movie(String title) {
        this.title = title;
    }

    public double calculateRating(int rating) {
        if (ratings == null) {
            ratings = new ArrayList<>();
        }
        ratings.add(rating);
        averageRating = ratings.stream()
                .mapToDouble(Double::valueOf)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("Something went wrong"));
        return averageRating;
    }


}
