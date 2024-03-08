package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

import jakarta.validation.Valid;

@Controller
public class TradeController {

    private final TradeService tradeService;

    private static final String TRADES = "trades";

    private static final String REDIRECT_SUCCESS = "redirect:/trade/list";

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute(TRADES, tradeService.getAllTrades());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTrade(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
            return "trade/add";
        }
        tradeService.save(trade);
        redirectAttributes.addFlashAttribute("additionSuccess", "Trade added successfully!");
        model.addAttribute(TRADES, tradeService.getAllTrades());
        return REDIRECT_SUCCESS;
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        final Trade trade = tradeService.getTradeById(id);
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
            return "trade/update";
        }
        tradeService.update(id, trade);
        redirectAttributes.addFlashAttribute("updateSuccess", "Trade updated successfully!");
        model.addAttribute(TRADES, tradeService.getAllTrades());
        return REDIRECT_SUCCESS;
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        tradeService.delete(tradeService.getTradeById(id));
        redirectAttributes.addFlashAttribute("deletionSuccess", "Trade deleted successfully!");
        model.addAttribute(TRADES, tradeService.getAllTrades());
        return REDIRECT_SUCCESS;
    }
}
