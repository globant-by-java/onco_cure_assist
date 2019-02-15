package com.globant.internal.oncocureassist.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "diagnostics")
public class Diagnostics {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diagnostics_generator")
    @SequenceGenerator(name = "diagnostics_generator", sequenceName = "diagnostics_id_seq")
    private Long id;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @Pattern(regexp = "T[0-4][a-b]?N[0-3]M[0-1]")
    @NotNull
    private String tnm;

    @Column(name = "primary_tumour")
    private Integer primaryTumour;

    @Column(name = "regional_lymph_nodes")
    private Integer regionalLymphNodes;

    @Column(name = "distant_metastasis")
    private Integer distantMetastasis;

    @Min(value = 0)
    @Max(value = 4)
    @Column(name = "tumour_size")
    private Long tumourSize;

    @Min(value = 0)
    @Max(value = 2)
    private Integer side;

    @Column(name = "bronchial_carcinoma")
    private Boolean bronchialCarcinoma;

    @Column(name = "lung_carcinoma")
    private Boolean lungCarcinoma;

    @Column(name = "peribroncial_carcinoma")
    private Boolean peribroncialCarcinoma;

    private Boolean interlobar;

    private Boolean subcarinal;

    @Column(name = "lower_paratracheal")
    private Boolean lowerParatracheal;

    @Column(name = "upper_paratracheal")
    private Boolean upperParatracheal;

    @Column(name = "same_lung_metastasis")
    private Boolean sameLungMetastasis;


    private Boolean symptoms;

    @Min(value = 0)
    private Double vlc;

    @Min(value = 0)
    private Double tvc;

    @Min(value = 0)
    @Column(name = "tiff_number")
    private Double tiffNumber;

    @Min(value = 0)
    @Column(name = "volume_force_exp")
    private Double volumeForceExp;

    @Min(value = 1)
    @Column(name = "heart_rate")
    private Integer heartRate;

    @Column(name = "heart_block")
    private Boolean heartBlock;

    private Boolean extrasistols;

    private Boolean somatic;

    private Boolean bac;

    @Column(name = "onco_anamesys")
    private Boolean oncoAnamesys;

    @Size(max = 16)
    private String stage;

    private Boolean complains;

    private Integer grade;

    @Min(value = 1)
    @Max(value = 26)
    @Column(name = "histology_diagnosis")
    private Integer histologyDiagnosis;

    private Boolean smoking;

    @Size(max = 64)
    @Column(name = "histology_code")
    private String histologyCode;

    private String t;

    private String n;

    private String m;

    private Boolean copd;

    private Boolean tuberculomas;

    private Boolean chd;

    private Boolean lcd;

    @Column(name = "inflammatory_digestive_processes")
    private Boolean inflammatoryDigestiveProcesses;

    private Boolean hepatitis;

    private Boolean cirrhosis;

    private Boolean pancreatitis;

    @Column(name = "musculoskeletal_diseases")
    private Boolean musculoskeletalDiseases;

    @Column(name = "inflammatory_kidneys_bladder_diseases")
    private Boolean inflammatoryKidneysBladderDiseases;

    @Column(name = "prostate_benign_diseases")
    private Boolean prostateBenignDiseases;

    @Column(name = "veins_diseases")
    private Boolean veinsDiseases;

    @Column(name = "blood_vessels_diseases")
    private Boolean bloodVesselsDiseases;

    @Column(name = "rhythm_disturbances")
    private Boolean rhythmDisturbances;

    @Column(name = "thyroid_gland_benign_diseases")
    private Boolean thyroidGlandBenignDiseases;

    @Column(name = "nervous_diseases")
    private Boolean nervousDiseases;


    private Boolean strokes;

    @Column(name = "rheumatic_diseases")
    private Boolean rheumaticDiseases;

    private Boolean anemia;


    public Long getId() {
        return id;
    }


    public String getTnm() {
        return tnm;
    }


    public Integer getPrimaryTumour() {
        return primaryTumour;
    }


    public Integer getRegionalLymphNodes() {
        return regionalLymphNodes;
    }


    public Integer getDistantMetastasis() {
        return distantMetastasis;
    }


    public Long getTumourSize() {
        return tumourSize;
    }


    public Integer getSide() {
        return side;
    }


    public Boolean getBronchialCarcinoma() {
        return bronchialCarcinoma;
    }


    public Boolean getLungCarcinoma() {
        return lungCarcinoma;
    }


    public Boolean getPeribroncialCarcinoma() {
        return peribroncialCarcinoma;
    }


    public Boolean getInterlobar() {
        return interlobar;
    }


    public Boolean getSubcarinal() {
        return subcarinal;
    }


    public Boolean getLowerParatracheal() {
        return lowerParatracheal;
    }


    public Boolean getUpperParatracheal() {
        return upperParatracheal;
    }


    public Boolean getSameLungMetastasis() {
        return sameLungMetastasis;
    }


    public Boolean getSymptoms() {
        return symptoms;
    }


    public Double getVlc() {
        return vlc;
    }


    public Double getTvc() {
        return tvc;
    }


    public Double getTiffNumber() {
        return tiffNumber;
    }


    public Double getVolumeForceExp() {
        return volumeForceExp;
    }


    public Integer getHeartRate() {
        return heartRate;
    }


    public Boolean getHeartBlock() {
        return heartBlock;
    }


    public Boolean getExtrasistols() {
        return extrasistols;
    }


    public Boolean getSomatic() {
        return somatic;
    }


    public Boolean getBac() {
        return bac;
    }


    public Boolean getOncoAnamesys() {
        return oncoAnamesys;
    }


    public String getStage() {
        return stage;
    }


    public Boolean isComplains() {
        return complains;
    }


    public Integer getGrade() {
        return grade;
    }


    public Integer getHistologyDiagnosis() {
        return histologyDiagnosis;
    }


    public Boolean getSmoking() {
        return smoking;
    }


    public String getHistologyCode() {
        return histologyCode;
    }


    public String getT() {
        return t;
    }


    public String getN() {
        return n;
    }


    public String getM() {
        return m;
    }


    public Boolean getCopd() {
        return copd;
    }


    public Boolean getTuberculomas() {
        return tuberculomas;
    }


    public Boolean getChd() {
        return chd;
    }


    public Boolean getLcd() {
        return lcd;
    }


    public Boolean getInflammatoryDigestiveProcesses() {
        return inflammatoryDigestiveProcesses;
    }


    public Boolean getHepatitis() {
        return hepatitis;
    }


    public Boolean getCirrhosis() {
        return cirrhosis;
    }


    public Boolean getPancreatitis() {
        return pancreatitis;
    }


    public Boolean getMusculoskeletalDiseases() {
        return musculoskeletalDiseases;
    }


    public Boolean getInflammatoryKidneysBladderDiseases() {
        return inflammatoryKidneysBladderDiseases;
    }


    public Boolean getProstateBenignDiseases() {
        return prostateBenignDiseases;
    }


    public Boolean getVeinsDiseases() {
        return veinsDiseases;
    }


    public Boolean getBloodVesselsDiseases() {
        return bloodVesselsDiseases;
    }


    public Boolean getRhythmDisturbances() {
        return rhythmDisturbances;
    }


    public Boolean getThyroidGlandBenignDiseases() {
        return thyroidGlandBenignDiseases;
    }


    public Boolean getNervousDiseases() {
        return nervousDiseases;
    }


    public Boolean getStrokes() {
        return strokes;
    }


    public Boolean getRheumaticDiseases() {
        return rheumaticDiseases;
    }


    public Boolean getAnemia() {
        return anemia;
    }


    public Patient getPatient() {
        return patient;
    }


    public void setTnm(String tnm) {
        this.tnm = tnm;
    }


    public void setPrimaryTumour(Integer primaryTumour) {
        this.primaryTumour = primaryTumour;
    }


    public void setRegionalLymphNodes(Integer regionalLymphNodes) {
        this.regionalLymphNodes = regionalLymphNodes;
    }


    public void setDistantMetastasis(Integer distantMetastasis) {
        this.distantMetastasis = distantMetastasis;
    }


    public void setTumourSize(Long tumourSize) {
        this.tumourSize = tumourSize;
    }


    public void setSide(Integer side) {
        this.side = side;
    }


    public void setBronchialCarcinoma(Boolean bronchialCarcinoma) {
        this.bronchialCarcinoma = bronchialCarcinoma;
    }


    public void setLungCarcinoma(Boolean lungCarcinoma) {
        this.lungCarcinoma = lungCarcinoma;
    }


    public void setPeribroncialCarcinoma(Boolean peribroncialCarcinoma) {
        this.peribroncialCarcinoma = peribroncialCarcinoma;
    }


    public void setInterlobar(Boolean interlobar) {
        this.interlobar = interlobar;
    }


    public void setSubcarinal(Boolean subcarinal) {
        this.subcarinal = subcarinal;
    }


    public void setLowerParatracheal(Boolean lowerParatracheal) {
        this.lowerParatracheal = lowerParatracheal;
    }


    public void setUpperParatracheal(Boolean upperParatracheal) {
        this.upperParatracheal = upperParatracheal;
    }


    public void setSameLungMetastasis(Boolean sameLungMetastasis) {
        this.sameLungMetastasis = sameLungMetastasis;
    }


    public void setSymptoms(Boolean symptoms) {
        this.symptoms = symptoms;
    }


    public void setVlc(Double vlc) {
        this.vlc = vlc;
    }


    public void setTvc(Double tvc) {
        this.tvc = tvc;
    }


    public void setTiffNumber(Double tiffNumber) {
        this.tiffNumber = tiffNumber;
    }


    public void setVolumeForceExp(Double volumeForceExp) {
        this.volumeForceExp = volumeForceExp;
    }


    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }


    public void setHeartBlock(Boolean heartBlock) {
        this.heartBlock = heartBlock;
    }


    public void setExtrasistols(Boolean extrasistols) {
        this.extrasistols = extrasistols;
    }


    public void setSomatic(Boolean somatic) {
        this.somatic = somatic;
    }


    public void setBac(Boolean bac) {
        this.bac = bac;
    }


    public void setOncoAnamesys(Boolean oncoAnamesys) {
        this.oncoAnamesys = oncoAnamesys;
    }


    public void setStage(String stage) {
        this.stage = stage;
    }


    public void setComplains(Boolean complains) {
        this.complains = complains;
    }


    public void setGrade(Integer grade) {
        this.grade = grade;
    }


    public void setHistologyDiagnosis(Integer histologyDiagnosis) {
        this.histologyDiagnosis = histologyDiagnosis;
    }


    public void setSmoking(Boolean smoking) {
        this.smoking = smoking;
    }


    public void setHistologyCode(String histologyCode) {
        this.histologyCode = histologyCode;
    }


    public void setT(String t) {
        this.t = t;
    }


    public void setN(String n) {
        this.n = n;
    }


    public void setM(String m) {
        this.m = m;
    }


    public void setCopd(Boolean copd) {
        this.copd = copd;
    }


    public void setTuberculomas(Boolean tuberculomas) {
        this.tuberculomas = tuberculomas;
    }


    public void setChd(Boolean chd) {
        this.chd = chd;
    }


    public void setLcd(Boolean lcd) {
        this.lcd = lcd;
    }


    public void setInflammatoryDigestiveProcesses(Boolean inflammatoryDigestiveProcesses) {
        this.inflammatoryDigestiveProcesses = inflammatoryDigestiveProcesses;
    }


    public void setHepatitis(Boolean hepatitis) {
        this.hepatitis = hepatitis;
    }


    public void setCirrhosis(Boolean cirrhosis) {
        this.cirrhosis = cirrhosis;
    }


    public void setPancreatitis(Boolean pancreatitis) {
        this.pancreatitis = pancreatitis;
    }


    public void setMusculoskeletalDiseases(Boolean musculoskeletalDiseases) {
        this.musculoskeletalDiseases = musculoskeletalDiseases;
    }


    public void setInflammatoryKidneysBladderDiseases(Boolean inflammatoryKidneysBladderDiseases) {
        this.inflammatoryKidneysBladderDiseases = inflammatoryKidneysBladderDiseases;
    }


    public void setProstateBenignDiseases(Boolean prostateBenignDiseases) {
        this.prostateBenignDiseases = prostateBenignDiseases;
    }


    public void setVeinsDiseases(Boolean veinsDiseases) {
        this.veinsDiseases = veinsDiseases;
    }


    public void setBloodVesselsDiseases(Boolean bloodVesselsDiseases) {
        this.bloodVesselsDiseases = bloodVesselsDiseases;
    }


    public void setRhythmDisturbances(Boolean rhythmDisturbances) {
        this.rhythmDisturbances = rhythmDisturbances;
    }


    public void setThyroidGlandBenignDiseases(Boolean thyroidGlandBenignDiseases) {
        this.thyroidGlandBenignDiseases = thyroidGlandBenignDiseases;
    }


    public void setNervousDiseases(Boolean nervousDiseases) {
        this.nervousDiseases = nervousDiseases;
    }


    public void setStrokes(Boolean strokes) {
        this.strokes = strokes;
    }


    public void setRheumaticDiseases(Boolean rheumaticDiseases) {
        this.rheumaticDiseases = rheumaticDiseases;
    }


    public void setAnemia(Boolean anemia) {
        this.anemia = anemia;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
