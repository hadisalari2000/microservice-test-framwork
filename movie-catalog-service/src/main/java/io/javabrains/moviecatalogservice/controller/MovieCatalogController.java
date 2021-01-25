package io.javabrains.moviecatalogservice.controller;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.MovieInfo;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;

    public MovieCatalogController(RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
    }

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalogs(@PathVariable("userId") String userId){

        UserRating ratings=restTemplate.getForObject("http://localhost:4001/MovieRating/users/"+userId,UserRating.class);

        //*****************RestTemplate Sample*******************
        return ratings != null ? ratings.getUserRating().stream().map(rating -> {
            MovieInfo movieInfo = restTemplate.getForObject("http://localhost:3001/info/" + rating.getMovieId(), MovieInfo.class);
            assert movieInfo != null;
            return CatalogItem.builder().name(movieInfo.getName()).description("-").rating(rating.getRate()).build();
        }).collect(Collectors.toList()) : null;


        //*****************WebClient Sample*******************
        /*return ratings.stream().map(rating ->{
            MovieInfo movieInfo=webClientBuilder.build()
                    .get()
                    .uri("http://localhost:3001/info/"+rating.getMovieId())
                    .retrieve()
                    .bodyToMono(MovieInfo.class)
                    .block();
            return CatalogItem.builder().name(movieInfo.getName()).description("-").rating(rating.getRate()).build();
        }).collect(Collectors.toList());*/

    }
}
