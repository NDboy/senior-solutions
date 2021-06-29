package com.example.moviessolutions2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovieCommand {

    private String title;
    private int length;

}
