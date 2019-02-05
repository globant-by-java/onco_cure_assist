package com.globant.internal.oncocureassist.core.pojo;

import java.util.Date;

public class Treatment {
    private Long id;
    private boolean surgeryApplied;
    private boolean chemotherapyApplied;
    private boolean radiationTherapyApplied;
    private Date surgeryDate;
    private Integer surgeryCode;
    private String surgeonName;
    private Integer firstLineCourse;
    private Integer secondLineCourse;
    private Integer thirdLineCourse;
    private Long patientId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isSurgeryApplied() {
        return surgeryApplied;
    }

    public void setSurgeryApplied(boolean surgeryApplied) {
        this.surgeryApplied = surgeryApplied;
    }

    public boolean isChemotherapyApplied() {
        return chemotherapyApplied;
    }

    public void setChemotherapyApplied(boolean chemotherapyApplied) {
        this.chemotherapyApplied = chemotherapyApplied;
    }

    public boolean isRadiationTherapyApplied() {
        return radiationTherapyApplied;
    }

    public void setRadiationTherapyApplied(boolean radiationTherapyApplied) {
        this.radiationTherapyApplied = radiationTherapyApplied;
    }

    public Integer getSurgeryCode() {
        return surgeryCode;
    }

    public void setSurgeryCode(Integer surgeryCode) {
        this.surgeryCode = surgeryCode;
    }

    public String getSurgeonName() {
        return surgeonName;
    }

    public void setSurgeonName(String surgeonName) {
        this.surgeonName = surgeonName;
    }

    public Integer getFirstLineCourse() {
        return firstLineCourse;
    }

    public void setFirstLineCourse(Integer firstLineCourse) {
        this.firstLineCourse = firstLineCourse;
    }

    public Integer getSecondLineCourse() {
        return secondLineCourse;
    }

    public void setSecondLineCourse(Integer secondLineCourse) {
        this.secondLineCourse = secondLineCourse;
    }

    public Integer getThirdLineCourse() {
        return thirdLineCourse;
    }

    public void setThirdLineCourse(Integer thirdLineCourse) {
        this.thirdLineCourse = thirdLineCourse;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Date getSurgeryDate() {
        return surgeryDate;
    }

    public void setSurgeryDate(Date surgeryDate) {
        this.surgeryDate = surgeryDate;
    }
}
