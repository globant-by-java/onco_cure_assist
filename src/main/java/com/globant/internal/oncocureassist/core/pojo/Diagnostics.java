package com.globant.internal.oncocureassist.core.pojo;

import java.util.HashSet;
import java.util.Set;

public class Diagnostics {
    private Long id;
    private Long patientId;
    private String tnm;
    private String t;
    private String n;
    private String m;
    private String primaryTumour;
    private String regionalLymphNodes;
    private String distantMetastasis;
    private Long tumourSize;
    private String side;
    private boolean bronchialCarcinoma;
    private boolean lungCarcinoma;
    private boolean peribroncialCarcinoma;
    private boolean interlobar;
    private boolean subcarinal;
    private boolean lowerParatracheal;
    private boolean upperParatracheal;
    private boolean sameLungMetastasis;
    private boolean symptoms;
    private Double vlc;
    private Double tvc;
    private Double tiffNumber;
    private Double volumeForceExp;
    private Integer heartRate;
    private boolean complains;
    private boolean heartBlock;
    private boolean extrasistols;
    private Integer[] miocardDiseases;
    private boolean arythmya;
    private boolean somatic;
    private String hystologyCode;
    private boolean bac;
    private boolean oncoAnamesys;
    private String stage;
    private Integer[] vascularDiseases;
    private Integer[] breathingOrgansDiseases;
    private boolean atherosclerosis;
    private boolean ag;
    private Integer[] heartDiseases;
    private boolean bloodDiseases;
    private Integer[] digestiveSystemDiseases;
    private boolean diabetes;
    private boolean endocrineSystemDiseases;
    private Integer[] strokeConsequences;
    private boolean nervousSystemDiseases;
    private Integer[] genitourinarySystemDiseases;
    private boolean rheumaticDiseases;
    private boolean musculoskeletalSystemDiseases;
    private boolean tuberculosis;
    private Integer[] othersDiseases;
    private Integer grade;
    private boolean ulcerDisease;
    private Integer histologyDiagnosis;
    private Boolean smoking;
    private Set<Integer> diseases = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getTnm() {
        return tnm;
    }

    public void setTnm(String tnm) {
        this.tnm = tnm;
    }

    public String getPrimaryTumour() {
        return primaryTumour;
    }

    public void setPrimaryTumour(String primaryTumour) {
        this.primaryTumour = primaryTumour;
    }

    public String getRegionalLymphNodes() {
        return regionalLymphNodes;
    }

    public void setRegionalLymphNodes(String regionalLymphNodes) {
        this.regionalLymphNodes = regionalLymphNodes;
    }

    public String getDistantMetastasis() {
        return distantMetastasis;
    }

    public void setDistantMetastasis(String distantMetastasis) {
        this.distantMetastasis = distantMetastasis;
    }

    public Long getTumourSize() {
        return tumourSize;
    }

    public void setTumourSize(Long tumourSize) {
        this.tumourSize = tumourSize;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public boolean isBronchialCarcinoma() {
        return bronchialCarcinoma;
    }

    public void setBronchialCarcinoma(boolean bronchialCarcinoma) {
        this.bronchialCarcinoma = bronchialCarcinoma;
    }

    public boolean isLungCarcinoma() {
        return lungCarcinoma;
    }

    public void setLungCarcinoma(boolean lungCarcinoma) {
        this.lungCarcinoma = lungCarcinoma;
    }

    public boolean isPeribroncialCarcinoma() {
        return peribroncialCarcinoma;
    }

    public void setPeribroncialCarcinoma(boolean peribroncialCarcinoma) {
        this.peribroncialCarcinoma = peribroncialCarcinoma;
    }

    public boolean isInterlobar() {
        return interlobar;
    }

    public void setInterlobar(boolean interlobar) {
        this.interlobar = interlobar;
    }

    public boolean isSubcarinal() {
        return subcarinal;
    }

    public void setSubcarinal(boolean subcarinal) {
        this.subcarinal = subcarinal;
    }

    public boolean isLowerParatracheal() {
        return lowerParatracheal;
    }

    public void setLowerParatracheal(boolean lowerParatracheal) {
        this.lowerParatracheal = lowerParatracheal;
    }

    public boolean isUpperParatracheal() {
        return upperParatracheal;
    }

    public void setUpperParatracheal(boolean upperParatracheal) {
        this.upperParatracheal = upperParatracheal;
    }

    public boolean isSameLungMetastasis() {
        return sameLungMetastasis;
    }

    public void setSameLungMetastasis(boolean sameLungMetastasis) {
        this.sameLungMetastasis = sameLungMetastasis;
    }

    public boolean isSymptoms() {
        return symptoms;
    }

    public void setSymptoms(boolean symptoms) {
        this.symptoms = symptoms;
    }

    public Double getVlc() {
        return vlc;
    }

    public void setVlc(Double vlc) {
        this.vlc = vlc;
    }

    public Double getTvc() {
        return tvc;
    }

    public void setTvc(Double tvc) {
        this.tvc = tvc;
    }

    public Double getTiffNumber() {
        return tiffNumber;
    }

    public void setTiffNumber(Double tiffNumber) {
        this.tiffNumber = tiffNumber;
    }

    public Double getVolumeForceExp() {
        return volumeForceExp;
    }

    public void setVolumeForceExp(Double volumeForceExp) {
        this.volumeForceExp = volumeForceExp;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public boolean isHeartBlock() {
        return heartBlock;
    }

    public void setHeartBlock(boolean heartBlock) {
        this.heartBlock = heartBlock;
    }

    public boolean isExtrasistols() {
        return extrasistols;
    }

    public void setExtrasistols(boolean extrasistols) {
        this.extrasistols = extrasistols;
    }

    public Integer[] getMiocardDiseases() {
        return miocardDiseases;
    }

    public void setMiocardDiseases(Integer[] miocardDiseases) {
        this.miocardDiseases = miocardDiseases;
    }

    public boolean isArythmya() {
        return arythmya;
    }

    public void setArythmya(boolean arythmya) {
        this.arythmya = arythmya;
    }

    public boolean isSomatic() {
        return somatic;
    }

    public void setSomatic(boolean somatic) {
        this.somatic = somatic;
    }

    public String getHystologyCode() {
        return hystologyCode;
    }

    public void setHystologyCode(String hystologyCode) {
        this.hystologyCode = hystologyCode;
    }

    public boolean isBac() {
        return bac;
    }

    public void setBac(boolean bac) {
        this.bac = bac;
    }

    public boolean isOncoAnamesys() {
        return oncoAnamesys;
    }

    public void setOncoAnamesys(boolean oncoAnamesys) {
        this.oncoAnamesys = oncoAnamesys;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Integer[] getVascularDiseases() {
        return vascularDiseases;
    }

    public void setVascularDiseases(Integer[] vascularDiseases) {
        this.vascularDiseases = vascularDiseases;
    }

    public Integer[] getBreathingOrgansDiseases() {
        return breathingOrgansDiseases;
    }

    public void setBreathingOrgansDiseases(Integer[] breathingOrgansDiseases) {
        this.breathingOrgansDiseases = breathingOrgansDiseases;
    }

    public boolean getAtherosclerosis() {
        return atherosclerosis;
    }

    public void setAtherosclerosis(boolean atherosclerosis) {
        this.atherosclerosis = atherosclerosis;
    }

    public boolean isAg() {
        return ag;
    }

    public void setAg(boolean ag) {
        this.ag = ag;
    }

    public Integer[] getHeartDiseases() {
        return heartDiseases;
    }

    public void setHeartDiseases(Integer[] heartDiseases) {
        this.heartDiseases = heartDiseases;
    }

    public boolean getBloodDiseases() {
        return bloodDiseases;
    }

    public void setBloodDiseases(boolean bloodDiseases) {
        this.bloodDiseases = bloodDiseases;
    }

    public Integer[] getDigestiveSystemDiseases() {
        return digestiveSystemDiseases;
    }

    public void setDigestiveSystemDiseases(Integer[] digestiveSystemDiseases) {
        this.digestiveSystemDiseases = digestiveSystemDiseases;
    }

    public boolean isDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public Integer[] getStrokeConsequences() {
        return strokeConsequences;
    }

    public void setStrokeConsequences(Integer[] strokeConsequences) {
        this.strokeConsequences = strokeConsequences;
    }

    public Integer[] getGenitourinarySystemDiseases() {
        return genitourinarySystemDiseases;
    }

    public void setGenitourinarySystemDiseases(Integer[] genitourinarySystemDiseases) {
        this.genitourinarySystemDiseases = genitourinarySystemDiseases;
    }

    public Integer[] getOthersDiseases() {
        return othersDiseases;
    }

    public void setOthersDiseases(Integer[] othersDiseases) {
        this.othersDiseases = othersDiseases;
    }

    public boolean isComplains() {
        return complains;
    }

    public void setComplains(boolean complains) {
        this.complains = complains;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public boolean isUlcerDisease() {
        return ulcerDisease;
    }

    public void setUlcerDisease(boolean ulcerDisease) {
        this.ulcerDisease = ulcerDisease;
    }

    public Integer getHistologyDiagnosis() {
        return histologyDiagnosis;
    }

    public void setHistologyDiagnosis(Integer histologyDiagnosis) {
        this.histologyDiagnosis = histologyDiagnosis;
    }

    public Boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(Boolean smoking) {
        this.smoking = smoking;
    }

    public boolean isEndocrineSystemDiseases() {
        return endocrineSystemDiseases;
    }

    public void setEndocrineSystemDiseases(boolean endocrineSystemDiseases) {
        this.endocrineSystemDiseases = endocrineSystemDiseases;
    }

    public boolean isNervousSystemDiseases() {
        return nervousSystemDiseases;
    }

    public void setNervousSystemDiseases(boolean nervousSystemDiseases) {
        this.nervousSystemDiseases = nervousSystemDiseases;
    }

    public boolean isRheumaticDiseases() {
        return rheumaticDiseases;
    }

    public void setRheumaticDiseases(boolean rheumaticDiseases) {
        this.rheumaticDiseases = rheumaticDiseases;
    }

    public boolean isMusculoskeletalSystemDiseases() {
        return musculoskeletalSystemDiseases;
    }

    public void setMusculoskeletalSystemDiseases(boolean musculoskeletalSystemDiseases) {
        this.musculoskeletalSystemDiseases = musculoskeletalSystemDiseases;
    }

    public boolean isTuberculosis() {
        return tuberculosis;
    }

    public void setTuberculosis(boolean tuberculosis) {
        this.tuberculosis = tuberculosis;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public Set<Integer> getDiseases() {
        return diseases;
    }

    public void setDiseases(Set<Integer> diseases) {
        this.diseases = diseases;
    }
}
