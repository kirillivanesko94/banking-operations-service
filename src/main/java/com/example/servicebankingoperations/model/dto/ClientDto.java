package com.example.servicebankingoperations.model.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ClientDto {

    private String login;
    private String fullName;
    private Date birthDay;
    private String password;
    private String accountNumber;
    private BigDecimal startDeposit;
    private BigDecimal balance;
    private String email;
    private String phone;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getStartDeposit() {
        return startDeposit;
    }

    public void setStartDeposit(BigDecimal startDeposit) {
        this.startDeposit = startDeposit;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}