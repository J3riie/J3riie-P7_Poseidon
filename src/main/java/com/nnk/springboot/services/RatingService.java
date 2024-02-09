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

    public void save(Rating rating) {
        repository.save(rating);
    }

    public Rating getRatingById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
    }

    public void update(Integer id, Rating rating) {
        rating.setId(id);
        repository.save(rating);

    }

    public void delete(Rating rating) {
        repository.delete(rating);
    }

}
