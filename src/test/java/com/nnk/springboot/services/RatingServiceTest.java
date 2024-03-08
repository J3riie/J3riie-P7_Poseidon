package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Rating;

@SpringBootTest
public class RatingServiceTest {

    @Autowired
    private RatingService ratingService;

    @Test
    public void ratingTest() {
        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

        // Save
        rating = ratingService.save(rating);
        assertThat(rating.getId()).isNotNull();
        assertThat(rating.getOrderNumber()).isEqualTo(10);

        // Update
        rating.setOrderNumber(20);
        rating = ratingService.save(rating);
        assertThat(rating.getOrderNumber()).isEqualTo(20);

        // Find
        final List<Rating> listResult = ratingService.getAllRatings();
        assertThat(listResult).isNotEmpty();

        // Delete
        final Integer id = rating.getId();
        final Rating ratingListBeforeDelete = ratingService.getRatingById(id);
        ratingService.delete(rating);
        assertThat(ratingListBeforeDelete).isNotNull();
        assertThrows(IllegalArgumentException.class, () -> ratingService.getRatingById(id));
    }
}
