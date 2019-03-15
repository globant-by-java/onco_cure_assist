package com.globant.internal.oncocureassist.domain.dictionary;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Genotype {

    VEGF_634("vegf634", Arrays.asList("G/G", "G/C", "C/C")),
    VEGF_2578("vegf2578", Arrays.asList("C/C", "A/C", "A/A")),
    VEGF_936("vegf936", Arrays.asList("C/C", "C/T", "T/T")),
    EGF("egf", Arrays.asList("A/A", "G/A", "G/G")),
    GSTP("gstp", Arrays.asList("A/A", "G/A", "G/G")),
    NAT_KPN("natkpn", Arrays.asList("C/C", "C/T", "T/T")),
    NAT_TAG("nattag", Arrays.asList("G/G", "G/A", "A/A")),
    NAT_BAM("natbam", Arrays.asList("G/G", "G/A", "A/A")),
    CYP_1_A_2("cyp1a2", Arrays.asList("C/C", "C/A", "A/A")),
    CYP_2_D_6("cyp2d6", Arrays.asList("G/G", "G/A", "A/A")),
    MDR("mdr", Arrays.asList("C/C", "C/T", "T/T")),
    TGF_509("tgf509", Arrays.asList("C/C", "C/T", "T/T")),
    TGF_25_CORDON("tgf25Codon", Arrays.asList("G/G", "G/C", "C/C")),
    TGF_R_206("tgfr206", Arrays.asList("C/C", "C/T", "T/T")),
    KDR_1719("kdr1719", Arrays.asList("T/T", "T/A", "A/A")),
    KDR_906("kdr906", Arrays.asList("T/T", "T/C", "C/C")),
    MMP_92660("mmp92660", Arrays.asList("A/A", "G/A", "G/G")),
    MMP_91562("mmp91562", Arrays.asList("C/C", "C/T")),
    MMP_2735("mmp2735", Arrays.asList("C/C", "C/T", "T/T")),
    MMP_21575("mmp21575", Arrays.asList("G/G", "G/A", "A/A")),
    DNMT_149("dnmt149", Arrays.asList("C/C", "C/T", "T/T")),
    DNMT_579("dnmt579", Arrays.asList("G/G", "G/T", "T/T")),
    SULT_1("sult1", Arrays.asList("GA", "GG")),
    GSTT("gstt", Arrays.asList("1", "0")),
    GSTM("gstm", Arrays.asList("1", "0")),
    EGFR_18_TUMOR("egfr18Tumor", Arrays.asList("no", "c.2203G/A", "c.2203G/G", "c.2203A/A")),
    EGFR_18_NORM("egfr18Norm", Collections.singletonList("no")),
    EGFR_19_TUMOR("egfr19Tumor", Arrays.asList("no", "2235_2249del", "2236_2250del", "2240_2257del")),
    EGFR_19_NORM("egfr19Norm", Collections.singletonList("no")),
    EGFR_20_TUMOR("egfr20Tumor", Arrays.asList("no", "c.2361A/A", "c.2361G/G", "c.2361G/A", "p.A763_Y764insFQEA")),
    EGFR_20_NORM("egfr20Norm", Arrays.asList("no", "c.2361G/A")),
    EGFR_21_TUMOR("egfr21Tumor", Arrays.asList("no", "2508C/T", "L858R")),
    EGFR_21_NORM("egfr21Norm", Arrays.asList("no", "2508C/T")),
    EGFR_21_BLOOD("egfr21Blood", Arrays.asList("no", "2508C/T")),
    KRAS_2_EX_TUMOR("kras2exTumor", Arrays.asList("no", "c.35G/A", "c.37G/T", "c.34G/T")),
    KRAS_2_EX_NORM("kras2exNorm", Collections.singletonList("no")),
    PIK_3_CA_9_EX("pik3ca9ex", Collections.singletonList("no")),
    PIK_3_CA_20_EX("pik3ca20ex", Collections.singletonList("no")),
    PTEN("pten", Collections.singletonList("no"));


    private final String code;
    private final List<String> values;


    Genotype(String code, List<String> values) {
        this.code = code;
        this.values = values;
    }


    public List<String> getValues() {
        return values;
    }


    public String getCode() {
        return code;
    }
}
