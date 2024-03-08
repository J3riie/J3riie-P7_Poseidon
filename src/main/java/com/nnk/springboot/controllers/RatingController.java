package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

import jakarta.validation.Valid;

@Controller
public class RatingController {

    private final RatingService ratingService;

    private static final String RATINGS = "ratings";

    private static final String REDIRECT_SUCCESS = "redirect:/rating/list";

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/rating/list")
    public String home(Model model) {
        model.addAttribute(RATINGS, ratingService.getAllRatings());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
            return "rating/add";
        }
        ratingService.save(rating);
        redirectAttributes.addFlashAttribute("additionSuccess", "Rating added successfully!");
        model.addAttribute(RATINGS, ratingService.getAllRatings());
        return REDIRECT_SUCCESS;
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        final Rating rating = ratingService.getRatingById(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
            return "rating/update";
        }
        ratingService.update(id, rating);
        redirectAttributes.addFlashAttribute("updateSuccess", "Rating updated successfully!");
        model.addAttribute(RATINGS, ratingService.getAllRatings());
        return REDIRECT_SUCCESS;
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        ratingService.delete(ratingService.getRatingById(id));
        redirectAttributes.addFlashAttribute("deletionSuccess", "Rating deleted successfully!");
        model.addAttribute(RATINGS, ratingService.getAllRatings());
        return REDIRECT_SUCCESS;
    }
}
