package com.nnk.springboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
public class RatingService {

    private final RatingRepository repository;

    public RatingService(RatingRepository ratingRepository) {
        this.repository = ratingRepository;
    }

    public List<Rating> getAllRatings() {
        return repository.findAll();
    }

    public Rating save(Rating rating) {
        return repository.save(rating);
    }

    public Rating getRatingById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
    }

    public Rating update(Integer id, Rating rating) {
        rating.setId(id);
        return repository.save(rating);

    }

    public void delete(Rating rating) {
        repository.delete(rating);
    }

}
