package io.javabrains.movieinfoservice.controller;

import io.javabrains.movieinfoservice.models.MovieInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class MovieInfoController {

    @RequestMapping("/{movieId}")
    public MovieInfo getMovieInfo(@PathVariable String movieId){
        return MovieInfo.builder().id(movieId).name("hadi-films-"+movieId).build();
    }
}
