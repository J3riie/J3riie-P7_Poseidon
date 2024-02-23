package com.nnk.springboot.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@DataJpaTest
public class BidListRepositoryTest {

    @Autowired
    private BidListRepository bidListRepository;

    @Test
    public void bidListTest() {
        BidList bid = new BidList("Account Test", "Type Test", 10d);
        // Save
        bid = bidListRepository.save(bid);
        assertThat(bid.getId()).isNotNull();
        assertThat(bid.getBidQuantity()).isEqualTo(10d);

        // Update
        bid.setBidQuantity(20d);
        bid = bidListRepository.save(bid);
        assertThat(bid.getBidQuantity()).isEqualTo(20d);

        // Find
        final List<BidList> listResult = bidListRepository.findAll();
        assertThat(listResult).isNotEmpty();

        // Delete
        final Integer id = bid.getId();
        final Optional<BidList> bidListBeforeDelete = bidListRepository.findById(id);
        bidListRepository.delete(bid);
        final Optional<BidList> bidListAfterDelete = bidListRepository.findById(id);
        assertThat(bidListBeforeDelete).isPresent();
        assertThat(bidListAfterDelete).isEmpty();
    }
}
