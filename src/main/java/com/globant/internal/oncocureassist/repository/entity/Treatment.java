package com.globant.internal.oncocureassist.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "treatment")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "treatment_generator")
    @SequenceGenerator(name = "treatment_generator", sequenceName = "treatment_id_seq")
    private Long id;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @NotNull
    @Column(name = "surgery_applied")
    private Boolean surgeryApplied;

    @NotNull
    @Column(name = "chemotherapy_applied")
    private Boolean chemotherapyApplied;

    @Column(name = "radiation_therapy_applied")
    private Boolean radiationTherapyApplied;

    @Min(value = 0)
    @Column(name = "surgery_code")
    private Integer surgeryCode;

    @Size(max = 127)
    @Column(name = "surgeon_name")
    private String surgeonName;

    @Column(name = "surgery_date")
    private LocalDate surgeryDate;

    @Min(value = 0)
    @Column(name = "first_line_course")
    private Integer firstLineCourse;

    @Min(value = 0)
    @Column(name = "second_line_course")
    private Integer secondLineCourse;

    @Min(value = 0)
    @Column(name = "third_line_course")
    private Integer thirdLineCourse;


    public Long getId() {
        return id;
    }


    public Boolean isSurgeryApplied() {
        return surgeryApplied;
    }


    public Boolean isChemotherapyApplied() {
        return chemotherapyApplied;
    }


    public Boolean isRadiationTherapyApplied() {
        return radiationTherapyApplied;
    }


    public Integer getSurgeryCode() {
        return surgeryCode;
    }


    public String getSurgeonName() {
        return surgeonName;
    }


    public LocalDate getSurgeryDate() {
        return surgeryDate;
    }


    public Integer getFirstLineCourse() {
        return firstLineCourse;
    }


    public Integer getSecondLineCourse() {
        return secondLineCourse;
    }


    public Integer getThirdLineCourse() {
        return thirdLineCourse;
    }


    public Patient getPatient() {
        return patient;
    }


    public void setSurgeryApplied(Boolean surgeryApplied) {
        this.surgeryApplied = surgeryApplied;
    }


    public void setChemotherapyApplied(Boolean chemotherapyApplied) {
        this.chemotherapyApplied = chemotherapyApplied;
    }


    public void setRadiationTherapyApplied(Boolean radiationTherapyApplied) {
        this.radiationTherapyApplied = radiationTherapyApplied;
    }


    public void setSurgeryCode(Integer surgeryCode) {
        this.surgeryCode = surgeryCode;
    }


    public void setSurgeonName(String surgeonName) {
        this.surgeonName = surgeonName;
    }


    public void setSurgeryDate(LocalDate surgeryDate) {
        this.surgeryDate = surgeryDate;
    }


    public void setFirstLineCourse(Integer firstLineCourse) {
        this.firstLineCourse = firstLineCourse;
    }


    public void setSecondLineCourse(Integer secondLineCourse) {
        this.secondLineCourse = secondLineCourse;
    }


    public void setThirdLineCourse(Integer thirdLineCourse) {
        this.thirdLineCourse = thirdLineCourse;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
