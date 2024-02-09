package com.nnk.springboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {

    private final BidListRepository repository;

    public BidListService(BidListRepository bidListRepository) {
        this.repository = bidListRepository;
    }

    public List<BidList> getAllBids() {
        return repository.findAll();
    }

    public void save(BidList bid) {
        repository.save(bid);

    }

    public BidList getBidById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
    }

    public void update(Integer id, BidList bidList) {
        bidList.setBidListId(id);
        repository.save(bidList);
    }

    public void delete(BidList bid) {
        repository.delete(bid);
    }

}
