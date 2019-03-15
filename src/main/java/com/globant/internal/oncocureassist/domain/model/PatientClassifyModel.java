package com.globant.internal.oncocureassist.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PatientClassifyModel {

    private Integer gender;

    @JsonProperty("age_class")
    private Integer ageClass;

    @JsonProperty("surgery_applied")
    private Boolean surgeryApplied;

    @JsonProperty("chemotherapy_applied")
    private Boolean chemotherapyApplied;

    @JsonProperty("radiation_therapy_applied")
    private Boolean radiationTherapyApplied;

    @JsonProperty("tumour_size")
    private String tumourSize;

    @JsonProperty("bronchial_carcinoma")
    private Boolean bronchialCarcinoma;

    @JsonProperty("lung_carcinoma")
    private Boolean lungCarcinoma;

    @JsonProperty("peribroncial_carcinoma")
    private Boolean peribroncialCarcinoma;

    private Boolean interlobar;

    private Boolean subcarinal;

    @JsonProperty("lower_paratracheal")
    private Boolean lowerParatracheal;

    @JsonProperty("upper_paratracheal")
    private Boolean upperParatracheal;

    @JsonProperty("onco_anamesys")
    private Boolean oncoAnamesys;

    private String stage;

    private Boolean complains;

    private Integer grade;

    @JsonProperty("histology_diagnosis")
    private Integer histologyDiagnosis;

    private Boolean smoking;

    private String t;

    private String n;

    private String m;

    private Boolean copd;

    private Boolean tuberculomas;

    private Boolean chd;

    private Boolean lcd;

    @JsonProperty("inflammatory_digestive_processes")
    private Boolean inflammatoryDigestiveProcesses;

    private Boolean hepatitis;

    private Boolean cirrhosis;

    private Boolean pancreatitis;

    @JsonProperty("musculoskeletal_diseases")
    private Boolean musculoskeletalDiseases;

    @JsonProperty("inflammatory_kidneys_bladder_diseases")
    private Boolean inflammatoryKidneysBladderDiseases;

    @JsonProperty("prostate_benign_diseases")
    private Boolean prostateBenignDiseases;

    @JsonProperty("veins_diseases")
    private Boolean veinsDiseases;

    @JsonProperty("blood_vessels_diseases")
    private Boolean bloodVesselsDiseases;

    @JsonProperty("rhythm_disturbances")
    private Boolean rhythmDisturbances;

    @JsonProperty("thyroid_gland_benign_diseases")
    private Boolean thyroidGlandBenignDiseases;

    @JsonProperty("nervous_diseases")
    private Boolean nervousDiseases;

    private Boolean strokes;
    @JsonProperty("rheumatic_diseases")
    private Boolean rheumaticDiseases;

    private Boolean anemia;

    @JsonProperty("vegf_634")
    private String vegf634;

    @JsonProperty("vegf_2578")
    private String vegf2578;

    @JsonProperty("vegf_936")
    private String vegf936;

    private String egf;

    private String gstt;

    private String gstm;

    private String gstp;

    private String natkpn;

    private String nattag;

    private String natbam;

    @JsonProperty("acetylation_type")
    private String acetylationType;

    @JsonProperty("cyp_1a2")
    private String cyp1a2;

    @JsonProperty("cyp_2d6")
    private String cyp2d6;

    private String mdr;

    @JsonProperty("egfr_18_tumor")
    private String egfr18Tumor;

    @JsonProperty("egfr_19_tumor")
    private String egfr19Tumor;

    @JsonProperty("egfr_19_norm")
    private String egfr19Norm;

    @JsonProperty("egfr_20_tumor")
    private String egfr20Tumor;

    @JsonProperty("egfr_20_norm")
    private String egfr20Norm;

    @JsonProperty("egfr_21_tumor")
    private String egfr21Tumor;

    @JsonProperty("egfr_21_norm")
    private String egfr21Norm;

    @JsonProperty("egfr_21_blood")
    private String egfr21Blood;

    @JsonProperty("tgf_509")
    private String tgf509;

    @JsonProperty("tgf_25_codon")
    private String tgf25Codon;

    @JsonProperty("tgfr_206")
    private String tgfr206;

    @JsonProperty("kdr_1719")
    private String kdr1719;

    @JsonProperty("kdr_906")
    private String kdr906;

    private String sult1;

    @JsonProperty("mmp9_2660")
    private String mmp92660;

    @JsonProperty("mmp9_1562")
    private String mmp91562;

    @JsonProperty("mmp2_735")
    private String mmp2735;

    @JsonProperty("mmp2_1575")
    private String mmp21575;

    @JsonProperty("kras_2ex_tumor")
    private String kras2exTumor;

    @JsonProperty("pik3ca_9ex")
    private String pik3ca9ex;

    @JsonProperty("pik3ca_20ex")
    private String pik3ca20ex;

    private String pten;
    @JsonProperty("dnmt_149")
    private String dnmt149;

    @JsonProperty("dnmt_579")
    private String dnmt579;

    @JsonProperty("class_id")
    private Integer classId;


    public Integer getGender() {
        return gender;
    }


    public Integer getAgeClass() {
        return ageClass;
    }


    public Boolean getSurgeryApplied() {
        return surgeryApplied;
    }


    public Boolean getChemotherapyApplied() {
        return chemotherapyApplied;
    }


    public Boolean getRadiationTherapyApplied() {
        return radiationTherapyApplied;
    }


    public String getTumourSize() {
        return tumourSize;
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


    public Boolean getOncoAnamesys() {
        return oncoAnamesys;
    }


    public String getStage() {
        return stage;
    }


    public Boolean getComplains() {
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


    public String getVegf634() {
        return vegf634;
    }


    public String getVegf2578() {
        return vegf2578;
    }


    public String getVegf936() {
        return vegf936;
    }


    public String getEgf() {
        return egf;
    }


    public String getGstt() {
        return gstt;
    }


    public String getGstm() {
        return gstm;
    }


    public String getGstp() {
        return gstp;
    }


    public String getNatkpn() {
        return natkpn;
    }


    public String getNattag() {
        return nattag;
    }


    public String getNatbam() {
        return natbam;
    }


    public String getAcetylationType() {
        return acetylationType;
    }


    public String getCyp1a2() {
        return cyp1a2;
    }


    public String getCyp2d6() {
        return cyp2d6;
    }


    public String getMdr() {
        return mdr;
    }


    public String getEgfr18Tumor() {
        return egfr18Tumor;
    }


    public String getEgfr19Tumor() {
        return egfr19Tumor;
    }


    public String getEgfr19Norm() {
        return egfr19Norm;
    }


    public String getEgfr20Tumor() {
        return egfr20Tumor;
    }


    public String getEgfr20Norm() {
        return egfr20Norm;
    }


    public String getEgfr21Tumor() {
        return egfr21Tumor;
    }


    public String getEgfr21Norm() {
        return egfr21Norm;
    }


    public String getEgfr21Blood() {
        return egfr21Blood;
    }


    public String getTgf509() {
        return tgf509;
    }


    public String getTgf25Codon() {
        return tgf25Codon;
    }


    public String getTgfr206() {
        return tgfr206;
    }


    public String getKdr1719() {
        return kdr1719;
    }


    public String getKdr906() {
        return kdr906;
    }


    public String getSult1() {
        return sult1;
    }


    public String getMmp92660() {
        return mmp92660;
    }


    public String getMmp91562() {
        return mmp91562;
    }


    public String getMmp2735() {
        return mmp2735;
    }


    public String getMmp21575() {
        return mmp21575;
    }


    public String getKras2exTumor() {
        return kras2exTumor;
    }


    public String getPik3ca9ex() {
        return pik3ca9ex;
    }


    public String getPik3ca20ex() {
        return pik3ca20ex;
    }


    public String getPten() {
        return pten;
    }


    public String getDnmt149() {
        return dnmt149;
    }


    public String getDnmt579() {
        return dnmt579;
    }


    public Integer getClassId() {
        return classId;
    }


    public void setGender(Integer gender) {
        this.gender = gender;
    }


    public void setAgeClass(Integer ageClass) {
        this.ageClass = ageClass;
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


    public void setTumourSize(String tumourSize) {
        this.tumourSize = tumourSize;
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


    public void setVegf634(String vegf634) {
        this.vegf634 = vegf634;
    }


    public void setVegf2578(String vegf2578) {
        this.vegf2578 = vegf2578;
    }


    public void setVegf936(String vegf936) {
        this.vegf936 = vegf936;
    }


    public void setEgf(String egf) {
        this.egf = egf;
    }


    public void setGstt(String gstt) {
        this.gstt = gstt;
    }


    public void setGstm(String gstm) {
        this.gstm = gstm;
    }


    public void setGstp(String gstp) {
        this.gstp = gstp;
    }


    public void setNatkpn(String natkpn) {
        this.natkpn = natkpn;
    }


    public void setNattag(String nattag) {
        this.nattag = nattag;
    }


    public void setNatbam(String natbam) {
        this.natbam = natbam;
    }


    public void setAcetylationType(String acetylationType) {
        this.acetylationType = acetylationType;
    }


    public void setCyp1a2(String cyp1a2) {
        this.cyp1a2 = cyp1a2;
    }


    public void setCyp2d6(String cyp2d6) {
        this.cyp2d6 = cyp2d6;
    }


    public void setMdr(String mdr) {
        this.mdr = mdr;
    }


    public void setEgfr18Tumor(String egfr18Tumor) {
        this.egfr18Tumor = egfr18Tumor;
    }


    public void setEgfr19Tumor(String egfr19Tumor) {
        this.egfr19Tumor = egfr19Tumor;
    }


    public void setEgfr19Norm(String egfr19Norm) {
        this.egfr19Norm = egfr19Norm;
    }


    public void setEgfr20Tumor(String egfr20Tumor) {
        this.egfr20Tumor = egfr20Tumor;
    }


    public void setEgfr20Norm(String egfr20Norm) {
        this.egfr20Norm = egfr20Norm;
    }


    public void setEgfr21Tumor(String egfr21Tumor) {
        this.egfr21Tumor = egfr21Tumor;
    }


    public void setEgfr21Norm(String egfr21Norm) {
        this.egfr21Norm = egfr21Norm;
    }


    public void setEgfr21Blood(String egfr21Blood) {
        this.egfr21Blood = egfr21Blood;
    }


    public void setTgf509(String tgf509) {
        this.tgf509 = tgf509;
    }


    public void setTgf25Codon(String tgf25Codon) {
        this.tgf25Codon = tgf25Codon;
    }


    public void setTgfr206(String tgfr206) {
        this.tgfr206 = tgfr206;
    }


    public void setKdr1719(String kdr1719) {
        this.kdr1719 = kdr1719;
    }


    public void setKdr906(String kdr906) {
        this.kdr906 = kdr906;
    }


    public void setSult1(String sult1) {
        this.sult1 = sult1;
    }


    public void setMmp92660(String mmp92660) {
        this.mmp92660 = mmp92660;
    }


    public void setMmp91562(String mmp91562) {
        this.mmp91562 = mmp91562;
    }


    public void setMmp2735(String mmp2735) {
        this.mmp2735 = mmp2735;
    }


    public void setMmp21575(String mmp21575) {
        this.mmp21575 = mmp21575;
    }


    public void setKras2exTumor(String kras2exTumor) {
        this.kras2exTumor = kras2exTumor;
    }


    public void setPik3ca9ex(String pik3ca9ex) {
        this.pik3ca9ex = pik3ca9ex;
    }


    public void setPik3ca20ex(String pik3ca20ex) {
        this.pik3ca20ex = pik3ca20ex;
    }


    public void setPten(String pten) {
        this.pten = pten;
    }


    public void setDnmt149(String dnmt149) {
        this.dnmt149 = dnmt149;
    }


    public void setDnmt579(String dnmt579) {
        this.dnmt579 = dnmt579;
    }


    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
