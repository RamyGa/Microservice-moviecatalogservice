package com.ramy.moviecatalogservice.resources;

import com.ramy.moviecatalogservice.models.CatalogItem;
import com.ramy.moviecatalogservice.models.Movie;
import com.ramy.moviecatalogservice.models.Rating;
import com.ramy.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {


        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);

        List<CatalogItem> catalogItems = new ArrayList<>();

        for (Rating rating: ratings.getUserRating()) {
            //for each movie ID, call movie info service and get all details
            Movie movie =restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
            catalogItems.add(new CatalogItem(movie.getName(), "Desc", rating.getRating()));
        }
        //put them all together
        return  catalogItems;



    }

}
