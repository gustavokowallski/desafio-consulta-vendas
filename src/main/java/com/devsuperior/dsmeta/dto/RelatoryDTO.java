package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

public class RelatoryDTO {
    private Long id;
    private LocalDate date;
    private Double amount;
    private String sellerName;

    public RelatoryDTO(){

    }

    public RelatoryDTO(Long id, LocalDate date, Double amount, String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = sellerName;
    }

    public Long getId() {
        return id;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
