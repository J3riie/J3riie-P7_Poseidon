package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Trade;

@SpringBootTest
public class TradeServiceTest {

    @Autowired
    private TradeService tradeService;

    @Test
    public void tradeTest() {
        Trade trade = new Trade("Trade Account", "Type");

        // Save
        trade = tradeService.save(trade);
        assertThat(trade.getId()).isNotNull();
        assertThat(trade.getAccount()).isEqualTo("Trade Account");

        // Update
        trade.setAccount("Trade Account Update");
        trade = tradeService.save(trade);
        assertThat(trade.getAccount()).isEqualTo("Trade Account Update");

        // Find
        final List<Trade> listResult = tradeService.getAllTrades();
        assertThat(listResult).isNotEmpty();

        // Delete
        final Integer id = trade.getId();
        final Trade tradeListBeforeDelete = tradeService.getTradeById(id);
        tradeService.delete(trade);
        assertThat(tradeListBeforeDelete).isNotNull();
        assertThrows(IllegalArgumentException.class, () -> tradeService.getTradeById(id));
    }
}
