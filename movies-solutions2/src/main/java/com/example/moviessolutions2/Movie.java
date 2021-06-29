package com.example.moviessolutions2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private Long id;
    private String title;
    private int length;
    private List<Integer> ratings = new ArrayList<>();
    private double ratingAverage;

    public Movie(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public Movie(Long id, String title, int length) {
        this.id = id;
        this.title = title;
        this.length = length;
    }

    public void addRating(int rating) {
        ratings.add(rating);
        ratingAverage = ratings.stream().collect(Collectors.summarizingDouble(Integer::doubleValue)).getAverage();
    }
}


//    A mai feladatban ismét egy filmekkel foglalkozó alkalmazást kell összeraknod.

//        A `Movie` entitásnak legyen egy azonosítója, egy címe, egy hossza,
//        egy az eddigi értékeléseket tartalmazó listája és egy értékelésátlaga.
//        Minden egyes alkalommal amikor egy értékelést kap a film, akkor az értékelésátlag
//        ennek megfelelően változik!

//        Legyen egy `MovieService` osztályod, ami listában tárolja a filmeket. Kezdetben
//        a lista üres később tudunk filmet hozzáadni.


//        Legyen egy `MovieController` ami alapértelmezetten az `api/movies` URL-n várja a kéréseket.

//        A következő funkciókat kell megvalósítani.
//        * Lehessen lekérni a filmeket, de csak a cím a hossz és az átlagértékelés jelenjen meg,
//        opcionálisan quryként lehessen szűrni film címre
//        * a `/{id}` URL-n keresztül lehessen egy aktuális filmet lekérdezni
//        * Lehessen felvenni új filmet a listába, ilyenkor csak a címet és a hosszt adjuk meg
//        * A `/{id}/rating` URL-n keresztül lehessen egy filmre értékelést adni és lekérdezni.
//        Ekkor csak egy számot kapunk egész számként, és ezt adjuk hozzá a film értékelés listájához,
//        majd térjünk vissza a már frissített értékekkel. Figyeljünk arra,
//        hogy ugyanazt az értékelést többször is megkaphatja a film.
//        * Lehessen filmet törölni az id alapján.

//        Ne felejts el unit és integrációs tesztet írni!
