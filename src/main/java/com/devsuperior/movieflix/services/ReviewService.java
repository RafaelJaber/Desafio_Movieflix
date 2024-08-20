package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final AuthService authService;

    public ReviewService(ReviewRepository reviewRepository, MovieRepository movieRepository, AuthService authService) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.authService = authService;
    }


    public ReviewDTO insert(ReviewDTO request) {
        Movie movie = movieRepository.getReferenceById(request.getMovieId());
        User user = authService.authenticated();

        Review review = new Review();
        review.setText(request.getText());
        review.setMovie(movie);
        review.setUser(user);

        Review inserted = reviewRepository.save(review);
        return new ReviewDTO(inserted, user);
    }

}
