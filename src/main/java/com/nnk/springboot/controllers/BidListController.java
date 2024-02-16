package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

import jakarta.validation.Valid;

@Controller
public class BidListController {

    private final BidListService bidService;

    private static final String BIDS = "bids";

    private static final String REDIRECT_SUCCESS = "redirect:/bidList/list";

    public BidListController(BidListService bidService) {
        this.bidService = bidService;
    }

    @GetMapping("/bidList/list")
    public String home(Model model) {
        model.addAttribute(BIDS, bidService.getAllBids());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
            return "bidList/add";
        }
        bidService.save(bid);
        model.addAttribute(BIDS, bidService.getAllBids());
        return REDIRECT_SUCCESS;
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        final BidList bid = bidService.getBidById(id);
        model.addAttribute("bid", bid);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
            return "bidList/update";
        }
        bidService.update(id, bidList);
        model.addAttribute(BIDS, bidService.getAllBids());
        return REDIRECT_SUCCESS;
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidService.delete(bidService.getBidById(id));
        model.addAttribute(BIDS, bidService.getAllBids());
        return REDIRECT_SUCCESS;
    }
}
