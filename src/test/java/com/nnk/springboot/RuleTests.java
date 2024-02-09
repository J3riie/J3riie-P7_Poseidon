package com.nnk.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@SpringBootTest
public class RuleTests {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Test
    public void ruleTest() {
        RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        // Save
        rule = ruleNameRepository.save(rule);
        assertThat(rule.getId()).isNotNull();
        assertThat(rule.getName()).isEqualTo("Rule Name");

        // Update
        rule.setName("Rule Name Update");
        rule = ruleNameRepository.save(rule);
        assertThat(rule.getName()).isEqualTo("Rule Name Update");

        // Find
        final List<RuleName> listResult = ruleNameRepository.findAll();
        assertThat(listResult).isNotEmpty();

        // Delete
        final Integer id = rule.getId();
        final Optional<RuleName> ruleListBeforeDelete = ruleNameRepository.findById(id);
        ruleNameRepository.delete(rule);
        final Optional<RuleName> ruleListAfterDelete = ruleNameRepository.findById(id);
        assertThat(ruleListBeforeDelete).isPresent();
        assertThat(ruleListAfterDelete).isEmpty();
    }
}
