package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.BidList;

@SpringBootTest
public class BidListRepositoryTest {

    @Autowired
    private BidListService bidListService;

    @Test
    public void bidListTest() {
        BidList bid = new BidList("Account Test", "Type Test", 10d);
        // Save
        bid = bidListService.save(bid);
        assertThat(bid.getId()).isNotNull();
        assertThat(bid.getBidQuantity()).isEqualTo(10d);

        // Update
        bid.setBidQuantity(20d);
        bid = bidListService.save(bid);
        assertThat(bid.getBidQuantity()).isEqualTo(20d);

        // Find
        final List<BidList> listResult = bidListService.getAllBids();
        assertThat(listResult).isNotEmpty();

        // Delete
        final Integer id = bid.getId();
        final BidList bidListBeforeDelete = bidListService.getBidById(id);
        bidListService.delete(bid);
        assertThat(bidListBeforeDelete).isNotNull();
        assertThrows(IllegalArgumentException.class, () -> bidListService.getBidById(id));
    }
}
