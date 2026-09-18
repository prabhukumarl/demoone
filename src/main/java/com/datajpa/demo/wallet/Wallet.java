package com.datajpa.demo.wallet;

import com.datajpa.demo.transaction.Transaction;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wallet {
    @Id
    //To create primary key
    @GeneratedValue
    private Integer id;
    private String email;

    @NotNull
    //@NotBlank
    @Size(min = 3, max = 30, message = "Name size must be between 3 to 30 characters.")
    private String name;
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must have min 1 digit, etc")
    private String password;
    @NotNull
    @Min(value = 500, message = "Minimun opening balance need 500 rupees.")
    private Double balance;
    private LocalDate createdOnDate;
    private Boolean isActive;

    @OneToMany(fetch = FetchType.LAZY) //One wallet many transaction
    private List<Transaction> transaction = new ArrayList<>();

    public Wallet() {
        this.isActive = true;
    }

    public Wallet(Integer id, String email, String name, String password, Double balance, LocalDate createdOnDate, Boolean IsActive) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.createdOnDate = createdOnDate;
        //this.isActive=true;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDate getCreatedOnDate() {
        return createdOnDate;
    }

    public void setCreatedOnDate(LocalDate createdOnDate) {
        this.createdOnDate = createdOnDate;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }
}
