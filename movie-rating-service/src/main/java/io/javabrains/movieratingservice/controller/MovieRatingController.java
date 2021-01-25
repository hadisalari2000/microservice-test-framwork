package io.javabrains.movieratingservice.controller;

import io.javabrains.movieratingservice.models.Rating;
import io.javabrains.movieratingservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/MovieRating")
public class MovieRatingController {

    @RequestMapping("/{movieId}")
    public Rating getMovieRating(@PathVariable String movieId){
        return Rating.builder().movieId(movieId).rate(4).build();
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserMovieRating(@PathVariable String userId){

        List<Rating> ratings= Arrays.asList(
                Rating.builder().movieId("1").rate(4).build(),
                Rating.builder().movieId("2").rate(3).build(),
                Rating.builder().movieId("3").rate(1).build()
        );

        return UserRating.builder().userRating(ratings).build();
    }
}
