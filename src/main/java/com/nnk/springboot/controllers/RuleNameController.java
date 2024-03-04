package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

import jakarta.validation.Valid;

@Controller
public class RuleNameController {

    private final RuleNameService ruleNameService;

    private static final String RULE_NAMES = "ruleNames";

    private static final String REDIRECT_SUCCESS = "redirect:/ruleName/list";

    public RuleNameController(RuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    @GetMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute(RULE_NAMES, ruleNameService.getAllRuleNames());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
            return "ruleName/add";
        }
        ruleNameService.save(ruleName);
        redirectAttributes.addFlashAttribute("additionSuccess", "Rule Name added successfully!");
        model.addAttribute(RULE_NAMES, ruleNameService.getAllRuleNames());
        return REDIRECT_SUCCESS;
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        final RuleName ruleName = ruleNameService.getRuleNameById(id);
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result,
            Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
            return "ruleName/update";
        }
        ruleNameService.update(id, ruleName);
        redirectAttributes.addFlashAttribute("updateSuccess", "Rule Name updated successfully!");
        model.addAttribute(RULE_NAMES, ruleNameService.getAllRuleNames());
        return REDIRECT_SUCCESS;
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.delete(ruleNameService.getRuleNameById(id));
        model.addAttribute(RULE_NAMES, ruleNameService.getAllRuleNames());
        return REDIRECT_SUCCESS;
    }
}
