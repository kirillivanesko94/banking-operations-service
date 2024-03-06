package com.example.servicebankingoperations.model;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "login")
    private String login;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "birth_date")
    private Date birthDay;
    @Column(name = "password")
    private String password;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "start_deposit")
    private BigDecimal startDeposit;
    @Column(name = "balance")
    private BigDecimal balance;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Email> emails = new ArrayList<>();
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<>();


    public Client() {
    }

    public Client(String login, String fullName, Date birthDay, String password, String accountNumber, BigDecimal startDeposit, BigDecimal balance) {
        this.login = login;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.password = password;
        this.accountNumber = accountNumber;
        this.startDeposit = startDeposit;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void addEmail(Email email) {
        email.setClient(this);
        emails.add(email);
    }
    public void addPhone(Phone phone) {
        phone.setClient(this);
        phones.add(phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
