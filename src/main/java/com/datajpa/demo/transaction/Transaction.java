package com.datajpa.demo.transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Builder
//@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount;
    private LocalDate date;
    private String UPIDetails;
    private TransactionType type;

    public Transaction() {
    }

    public Transaction(TransactionType type, String UPIDetails, LocalDate date, Double amount, Integer id) {
        this.type = type;
        this.UPIDetails = UPIDetails;
        this.date = date;
        this.amount = amount;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUPIDetails() {
        return UPIDetails;
    }

    public void setUPIDetails(String UPIDetails) {
        this.UPIDetails = UPIDetails;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
