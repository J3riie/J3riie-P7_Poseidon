package com.nnk.springboot.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Trade")
@Getter
@Setter
@NoArgsConstructor
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TradeId")
    private Integer id;

    @NotBlank
    private String account;

    @NotBlank
    private String type;

    @Min(value = 0, message = "Quantity must be over 0")
    private double buyQuantity;

    private double sellQuantity;

    private double buyPrice;

    private double sellPrice;

    private LocalDateTime tradeDate;

    private String security;

    private String status;

    private String trader;

    private String benchmark;

    private String book;

    private String creationName;

    private LocalDateTime creationDate;

    private String revisionName;

    private LocalDateTime revisionDate;

    private String dealName;

    private String dealType;

    private String sourceListId;

    private String side;

    public Trade(@NotBlank String account, @NotBlank String type) {
        this.account = account;
        this.type = type;
    }
}
