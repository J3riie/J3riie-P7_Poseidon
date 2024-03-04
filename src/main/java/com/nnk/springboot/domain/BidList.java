package com.nnk.springboot.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
@Table(name = "BidList")
@Getter
@Setter
@NoArgsConstructor
public class BidList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BidListId")
    private Integer id;

    @NotBlank(message = "Account is mandatory")
    private String account;

    @NotBlank(message = "Type is mandatory")
    private String type;

    private double bidQuantity;

    private double askQuantity;

    private double bid;

    private double ask;

    private String benchmark;

    private LocalDateTime bidListDate;

    private String commentary;

    private String security;

    private String status;

    private String trader;

    private String book;

    private String creationName;

    private LocalDateTime creationDate;

    private String revisionName;

    private LocalDateTime revisionDate;

    private String dealName;

    private String dealType;

    private String sourceListId;

    private String side;

    public BidList(@NotBlank String account, @NotBlank String type, double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

}
