package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.RuleName;

@SpringBootTest
public class RuleNameServiceTest {

    @Autowired
    private RuleNameService ruleNameService;

    @Test
    public void ruleTest() {
        RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        // Save
        rule = ruleNameService.save(rule);
        assertThat(rule.getId()).isNotNull();
        assertThat(rule.getName()).isEqualTo("Rule Name");

        // Update
        rule.setName("Rule Name Update");
        rule = ruleNameService.save(rule);
        assertThat(rule.getName()).isEqualTo("Rule Name Update");

        // Find
        final List<RuleName> listResult = ruleNameService.getAllRuleNames();
        assertThat(listResult).isNotEmpty();

        // Delete
        final Integer id = rule.getId();
        final RuleName ruleListBeforeDelete = ruleNameService.getRuleNameById(id);
        ruleNameService.delete(rule);
        assertThat(ruleListBeforeDelete).isNotNull();
        assertThrows(IllegalArgumentException.class, () -> ruleNameService.getRuleNameById(id));
    }
}
