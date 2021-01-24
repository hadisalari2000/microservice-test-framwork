package io.javabrains.movieratingservice.controller;


import io.javabrains.movieratingservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/MovieRating")
public class MovieRatingController {

    @RequestMapping("/{movieId}")
    public Rating getMovieRating(@PathVariable String movieId){
        return Rating.builder().movieId(movieId).rate(10).build();
    }
}
