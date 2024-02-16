package com.nnk.springboot.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@DataJpaTest
public class RatingRepositoryTest {

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    public void ratingTest() {
        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

        // Save
        rating = ratingRepository.save(rating);
        assertThat(rating.getId()).isNotNull();
        assertThat(rating.getOrderNumber()).isEqualTo(10);

        // Update
        rating.setOrderNumber(20);
        rating = ratingRepository.save(rating);
        assertThat(rating.getOrderNumber()).isEqualTo(20);

        // Find
        final List<Rating> listResult = ratingRepository.findAll();
        assertThat(listResult).isNotEmpty();

        // Delete
        final Integer id = rating.getId();
        final Optional<Rating> ratingListBeforeDelete = ratingRepository.findById(id);
        ratingRepository.delete(rating);
        final Optional<Rating> ratingListAfterDelete = ratingRepository.findById(id);
        assertThat(ratingListBeforeDelete).isPresent();
        assertThat(ratingListAfterDelete).isEmpty();
    }
}
