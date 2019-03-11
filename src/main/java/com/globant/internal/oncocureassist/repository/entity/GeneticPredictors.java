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

@Entity
@Table(name = "genetic_predictors")
public class GeneticPredictors {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genetic_predictors_generator")
    @SequenceGenerator(name = "genetic_predictors_generator", sequenceName = "genetic_predictors_id_seq")
    private Long id;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @Column(name = "vegf_634")
    private String vegf634;

    @Column(name = "vegf_2578")
    private String vegf2578;

    @Column(name = "vegf_936")
    private String vegf936;

    private String egf;

    private String gstt;

    private String gstm;

    private String gstp;

    private String natkpn;

    private String nattag;

    private String natbam;

    @Column(name = "acetylation_type")
    private String acetylationType;

    @Column(name = "cyp_1a2")
    private String cyp1a2;

    @Column(name = "cyp_2d6")
    private String cyp2d6;

    private String mdr;

    @Column(name = "egfr_18_tumor")
    private String egfr18Tumor;

    @Column(name = "egfr_18_norm")
    private String egfr18Norm;

    @Column(name = "egfr_19_tumor")
    private String egfr19Tumor;

    @Column(name = "egfr_19_norm")
    private String egfr19Norm;

    @Column(name = "egfr_20_tumor")
    private String egfr20Tumor;

    @Column(name = "egfr_20_norm")
    private String egfr20Norm;

    @Column(name = "egfr_21_tumor")
    private String egfr21Tumor;

    @Column(name = "egfr_21_norm")
    private String egfr21Norm;

    @Column(name = "egfr_21_blood")
    private String egfr21Blood;

    @Column(name = "tgf_509")
    private String tgf509;

    @Column(name = "tgf_25_codon")
    private String tgf25Codon;

    @Column(name = "tgfr_206")
    private String tgfr206;

    @Column(name = "kdr_1719")
    private String kdr1719;

    @Column(name = "kdr_906")
    private String kdr906;

    private String sult1;

    @Column(name = "mmp9_2660")
    private String mmp92660;

    @Column(name = "mmp9_1562")
    private String mmp91562;

    @Column(name = "mmp2_735")
    private String mmp2735;

    @Column(name = "mmp2_1575")
    private String mmp21575;

    @Column(name = "kras_2ex_tumor")
    private String kras2exTumor;

    @Column(name = "kras_2ex_norm")
    private String kras2exNorm;

    @Column(name = "pik3ca_9ex")
    private String pik3ca9ex;

    @Column(name = "pik3ca_20ex")
    private String pik3ca20ex;

    private String pten;

    @Column(name = "dnmt_149")
    private String dnmt149;

    @Column(name = "dnmt_579")
    private String dnmt579;


    public Long getId() {
        return id;
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


    public String getEgfr18Norm() {
        return egfr18Norm;
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


    public String getKras2exNorm() {
        return kras2exNorm;
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


    public Patient getPatient() {
        return patient;
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


    public void setEgfr18Norm(String egfr18Norm) {
        this.egfr18Norm = egfr18Norm;
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


    public void setKras2exNorm(String kras2exNorm) {
        this.kras2exNorm = kras2exNorm;
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


    public void setId(Long id) {
        this.id = id;
    }


    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
