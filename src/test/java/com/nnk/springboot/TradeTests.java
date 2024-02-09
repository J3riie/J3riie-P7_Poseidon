package com.nnk.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@SpringBootTest
public class TradeTests {

    @Autowired
    private TradeRepository tradeRepository;

    @Test
    public void tradeTest() {
        Trade trade = new Trade("Trade Account", "Type");

        // Save
        trade = tradeRepository.save(trade);
        assertThat(trade.getTradeId()).isNotNull();
        assertThat(trade.getAccount()).isEqualTo("Trade Account");

        // Update
        trade.setAccount("Trade Account Update");
        trade = tradeRepository.save(trade);
        assertThat(trade.getAccount()).isEqualTo("Trade Account Update");

        // Find
        final List<Trade> listResult = tradeRepository.findAll();
        assertThat(listResult).isNotEmpty();

        // Delete
        final Integer id = trade.getTradeId();
        final Optional<Trade> tradeListBeforeDelete = tradeRepository.findById(id);
        tradeRepository.delete(trade);
        final Optional<Trade> tradeListAfterDelete = tradeRepository.findById(id);
        assertThat(tradeListBeforeDelete).isPresent();
        assertThat(tradeListAfterDelete).isEmpty();
    }
}
