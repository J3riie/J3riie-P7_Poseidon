package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "RuleName")
@Getter
@Setter
@NoArgsConstructor
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Json is mandatory")
    private String json;

    @NotBlank(message = "Template is mandatory")
    private String template;

    @NotBlank(message = "sqlStr is mandatory")
    private String sqlStr;

    @NotBlank(message = "sqlPart is mandatory")
    private String sqlPart;

    public RuleName(@NotBlank(message = "Name is mandatory") String name,
            @NotBlank(message = "Description is mandatory") String description,
            @NotBlank(message = "Json is mandatory") String json,
            @NotBlank(message = "Template is mandatory") String template,
            @NotBlank(message = "sqlStr is mandatory") String sqlStr,
            @NotBlank(message = "sqlPart is mandatory") String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }
}
