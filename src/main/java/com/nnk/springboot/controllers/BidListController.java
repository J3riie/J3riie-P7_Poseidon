package com.nnk.springboot.controllers;

import java.util.List;
import java.util.Objects;

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

    public BidListController(BidListService bidService) {
        this.bidService = bidService;
    }

    @GetMapping("/bidList/list")
    public String home(Model model) {
        // TODO: call service find all bids to show to the view
        final List<BidList> allBids = bidService.getAllBids();
        model.addAttribute(allBids);
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        if (bid.getBidListId() == null || bid.getAccount().isBlank() || bid.getType().isBlank()) {
            throw new RuntimeException();
        }
        bidService.save(bid);
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        final BidList bid = bidService.getBidById(id);
        model.addAttribute(bid);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        if (!Objects.equals(bidList.getBidListId(), id) || bidList.getAccount().isBlank()
                || bidList.getType().isBlank()) {
            throw new RuntimeException();
        }
        bidService.update(bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        bidService.delete(bidService.getBidById(id));
        return "redirect:/bidList/list";
    }
}
