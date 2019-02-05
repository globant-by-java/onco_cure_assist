package com.globant.internal.oncocureassist.core.pojo;

import java.util.Date;

public class Patient {
    private Long id;
    private String fullName;
    private String cardNumber;
    private String gender;
    private Date birthDate;
    private Date contactDate;
    private boolean alive;
    private Long survivalMonth;
    private boolean employed;
    private Integer professionCode;
    private String address;
    private String phone;
    private Diagnostics diagnostics;
    private Treatment treatment;
    private GeneticPredictors geneticPredictors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getContactDate() {
        return contactDate;
    }

    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Long getSurvivalMonth() {
        return survivalMonth;
    }

    public void setSurvivalMonth(Long survivalMonth) {
        this.survivalMonth = survivalMonth;
    }

    public boolean isEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    public Integer getProfessionCode() {
        return professionCode;
    }

    public void setProfessionCode(Integer professionCode) {
        this.professionCode = professionCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Diagnostics getDiagnostics() {
        return diagnostics;
    }

    public void setDiagnostics(Diagnostics diagnostics) {
        this.diagnostics = diagnostics;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public GeneticPredictors getGeneticPredictors() {
        return geneticPredictors;
    }

    public void setGeneticPredictors(GeneticPredictors geneticPredictors) {
        this.geneticPredictors = geneticPredictors;
    }
}
