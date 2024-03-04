package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

import jakarta.validation.Valid;

@Controller
public class CurveController {

    private final CurvePointService curveService;

    private static final String CURVE_POINTS = "curvePoints";

    private static final String REDIRECT_SUCCESS = "redirect:/curvePoint/list";

    public CurveController(CurvePointService curveService) {
        this.curveService = curveService;
    }

    @GetMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute(CURVE_POINTS, curveService.getAllCurves());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
            return "curvePoint/add";
        }
        curveService.save(curvePoint);
        redirectAttributes.addFlashAttribute("additionSuccess", "Curve Point added successfully!");
        model.addAttribute(CURVE_POINTS, curveService.getAllCurves());
        return REDIRECT_SUCCESS;
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        final CurvePoint curve = curveService.getCurveById(id);
        model.addAttribute("curvePoint", curve);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
            Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
            return "curvePoint/update";
        }
        curveService.update(id, curvePoint);
        redirectAttributes.addFlashAttribute("updateSuccess", "Curve Point updated successfully!");
        model.addAttribute(CURVE_POINTS, curveService.getAllCurves());
        return REDIRECT_SUCCESS;
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        curveService.delete(curveService.getCurveById(id));
        model.addAttribute(CURVE_POINTS, curveService.getAllCurves());
        return REDIRECT_SUCCESS;
    }
}
