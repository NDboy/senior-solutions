package com.example.moviessolutions2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {


    private String title;
    private double length;

    private double ratingAverage;


}
