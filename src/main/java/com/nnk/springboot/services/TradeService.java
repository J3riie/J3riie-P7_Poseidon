package com.nnk.springboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeService {

    private final TradeRepository repository;

    public TradeService(TradeRepository tradeRepository) {
        this.repository = tradeRepository;
    }

    public List<Trade> getAllTrades() {
        return repository.findAll();
    }

    public Trade save(Trade trade) {
        return repository.save(trade);
    }

    public Trade getTradeById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
    }

    public Trade update(Integer id, Trade trade) {
        trade.setId(id);
        return repository.save(trade);
    }

    public void delete(Trade trade) {
        repository.delete(trade);
    }

}
