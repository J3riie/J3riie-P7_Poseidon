package com.nnk.springboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
public class RuleNameService {

    private final RuleNameRepository repository;

    public RuleNameService(RuleNameRepository ruleNameRepository) {
        this.repository = ruleNameRepository;
    }

    public List<RuleName> getAllRuleNames() {
        return repository.findAll();
    }

    public RuleName save(RuleName ruleName) {
        return repository.save(ruleName);
    }

    public RuleName getRuleNameById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rule name Id:" + id));
    }

    public RuleName update(Integer id, RuleName ruleName) {
        ruleName.setId(id);
        return repository.save(ruleName);
    }

    public void delete(RuleName ruleName) {
        repository.delete(ruleName);

    }

}
