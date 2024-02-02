package com.nnk.springboot.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

import jakarta.validation.Valid;

@Controller
public class CurveController {

    private final CurvePointService curveService;

    public CurveController(CurvePointService curveService) {
        this.curveService = curveService;
    }

    @GetMapping("/curvePoint/list")
    public String home(Model model) {
        // TODO: find all Curve Point, add to model
        final List<CurvePoint> allCurves = curveService.getAllCurves();
        model.addAttribute(allCurves);
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
        if (curvePoint.getId() == null) {
            throw new RuntimeException();
        }
        curveService.save();
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        final CurvePoint curve = curveService.getCurveById(id);
        model.addAttribute(curve);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
            Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        if (!Objects.equals(curvePoint.getId(), id)) {
            throw new RuntimeException();
        }
        curveService.update(curvePoint);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        curveService.delete(curveService.getCurveById(id));
        return "redirect:/curvePoint/list";
    }
}
