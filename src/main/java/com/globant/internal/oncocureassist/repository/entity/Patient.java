package com.globant.internal.oncocureassist.repository.entity;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_generator")
    @SequenceGenerator(name = "patient_generator", sequenceName = "patient_id_seq")
    private Long id;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private Diagnostics diagnostics;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private Treatment treatment;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private GeneticPredictors geneticPredictors;

    @Size(max = 127)
    @NotNull
    @Column(name = "full_name")
    private String fullName;

    @NotNull
    private Integer gender;

    @NotNull
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotNull
    @Column(name = "contact_date")
    private LocalDate contactDate;

    @Column(name = "survival_month")
    private Double survivalMonth;

    @Size(max = 64)
    @NotNull
    @Column(name = "patient_card_number")
    private String cardNumber;

    @NotNull
    private Boolean alive;

    private Boolean employed;

    @Min(value = 1)
    @Column(name = "profession_code")
    private Integer professionCode;

    @Size(max = 512)
    private String address;

    @Size(max = 128)
    private String phone;


    @Column(name = "class_id")
    private Integer classId;

    @NotNull
    private Integer age;

    @NotNull
    @Column(name = "age_class")
    private Integer ageClass;

    private Boolean deleted;

    @Column(name = "version", insertable = false)
    @Version
    private Long version;


    public Long getId() {
        return id;
    }


    public String getFullName() {
        return fullName;
    }


    public Integer getGender() {
        return gender;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }


    public LocalDate getContactDate() {
        return contactDate;
    }


    public Boolean isAlive() {
        return alive;
    }


    public Double getSurvivalMonth() {
        return survivalMonth;
    }


    public Boolean isEmployed() {
        return employed;
    }


    public Integer getProfessionCode() {
        return professionCode;
    }


    public String getAddress() {
        return address;
    }


    public String getPhone() {
        return phone;
    }


    public String getCardNumber() {
        return cardNumber;
    }


    public Integer getClassId() {
        return classId;
    }


    public Integer getAge() {
        return age;
    }


    public Integer getAgeClass() {
        return ageClass;
    }


    public Diagnostics getDiagnostics() {
        return diagnostics;
    }


    public Treatment getTreatment() {
        return treatment;
    }


    public GeneticPredictors getGeneticPredictors() {
        return geneticPredictors;
    }


    public Boolean isDeleted() {
        return deleted;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public void setGender(Integer gender) {
        this.gender = gender;
    }


    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    public void setContactDate(LocalDate contactDate) {
        this.contactDate = contactDate;
    }


    public void setAlive(Boolean alive) {
        this.alive = alive;
    }


    public void setSurvivalMonth(Double survivalMonth) {
        this.survivalMonth = survivalMonth;
    }


    public void setEmployed(Boolean employed) {
        this.employed = employed;
    }


    public void setProfessionCode(Integer professionCode) {
        this.professionCode = professionCode;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public void setClassId(Integer classId) {
        this.classId = classId;
    }


    public void setAge(Integer age) {
        this.age = age;
    }


    public void setAgeClass(Integer ageClass) {
        this.ageClass = ageClass;
    }


    public void setDiagnostics(Diagnostics diagnostics) {
        this.diagnostics = diagnostics;
        if (diagnostics != null && diagnostics.getPatient() != this) {
            diagnostics.setPatient(this);
        }
    }


    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
        if (treatment != null && treatment.getPatient() != this) {
            treatment.setPatient(this);
        }
    }


    public void setGeneticPredictors(GeneticPredictors geneticPredictors) {
        this.geneticPredictors = geneticPredictors;
        if (geneticPredictors != null && geneticPredictors.getPatient() != this) {
            geneticPredictors.setPatient(this);
        }
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }


    public Long getVersion() {
        return version;
    }


    public void setVersion(Long version) {
        this.version = version;
    }
}
