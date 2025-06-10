package com.devsuperior.dsmeta.dto;

public class SummaryMinDTO {

    private String name;
    private Double total;

    public SummaryMinDTO(){
    }

    public SummaryMinDTO(String name, Double total) {
        this.name = name;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public Double getTotal() {
        return total;
    }
}
