package com.globant.internal.oncocureassist.mapper;

import com.globant.internal.oncocureassist.domain.model.PatientClassifyModel;
import com.globant.internal.oncocureassist.repository.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PatientClassifyModelMapper {

    @Mapping(target = "surgeryApplied", source = "treatment.surgeryApplied")
    @Mapping(target = "chemotherapyApplied", source = "treatment.chemotherapyApplied")
    @Mapping(target = "radiationTherapyApplied", source = "treatment.radiationTherapyApplied")
    @Mapping(target = "tumourSize", source = "diagnostics.tumourSize")
    @Mapping(target = "bronchialCarcinoma", source = "diagnostics.bronchialCarcinoma")
    @Mapping(target = "lungCarcinoma", source = "diagnostics.lungCarcinoma")
    @Mapping(target = "peribroncialCarcinoma", source = "diagnostics.peribroncialCarcinoma")
    @Mapping(target = "interlobar", source = "diagnostics.interlobar")
    @Mapping(target = "subcarinal", source = "diagnostics.subcarinal")
    @Mapping(target = "lowerParatracheal", source = "diagnostics.lowerParatracheal")
    @Mapping(target = "upperParatracheal", source = "diagnostics.upperParatracheal")
    @Mapping(target = "oncoAnamesys", source = "diagnostics.oncoAnamesys")
    @Mapping(target = "stage", source = "diagnostics.stage")
    @Mapping(target = "complains", source = "diagnostics.complains")
    @Mapping(target = "grade", source = "diagnostics.grade")
    @Mapping(target = "histologyDiagnosis", source = "diagnostics.histologyDiagnosis")
    @Mapping(target = "smoking", source = "diagnostics.smoking")
    @Mapping(target = "t", source = "diagnostics.t")
    @Mapping(target = "n", source = "diagnostics.n")
    @Mapping(target = "m", source = "diagnostics.m")
    @Mapping(target = "copd", source = "diagnostics.copd")
    @Mapping(target = "tuberculomas", source = "diagnostics.tuberculomas")
    @Mapping(target = "chd", source = "diagnostics.chd")
    @Mapping(target = "lcd", source = "diagnostics.lcd")
    @Mapping(target = "inflammatoryDigestiveProcesses", source = "diagnostics.inflammatoryDigestiveProcesses")
    @Mapping(target = "hepatitis", source = "diagnostics.hepatitis")
    @Mapping(target = "cirrhosis", source = "diagnostics.cirrhosis")
    @Mapping(target = "pancreatitis", source = "diagnostics.pancreatitis")
    @Mapping(target = "musculoskeletalDiseases", source = "diagnostics.musculoskeletalDiseases")
    @Mapping(target = "inflammatoryKidneysBladderDiseases", source = "diagnostics.inflammatoryKidneysBladderDiseases")
    @Mapping(target = "prostateBenignDiseases", source = "diagnostics.prostateBenignDiseases")
    @Mapping(target = "veinsDiseases", source = "diagnostics.veinsDiseases")
    @Mapping(target = "bloodVesselsDiseases", source = "diagnostics.bloodVesselsDiseases")
    @Mapping(target = "rhythmDisturbances", source = "diagnostics.rhythmDisturbances")
    @Mapping(target = "thyroidGlandBenignDiseases", source = "diagnostics.thyroidGlandBenignDiseases")
    @Mapping(target = "nervousDiseases", source = "diagnostics.nervousDiseases")
    @Mapping(target = "strokes", source = "diagnostics.strokes")
    @Mapping(target = "rheumaticDiseases", source = "diagnostics.rheumaticDiseases")
    @Mapping(target = "anemia", source = "diagnostics.anemia")
    @Mapping(target = "vegf634", source = "geneticPredictors.vegf634")
    @Mapping(target = "vegf2578", source = "geneticPredictors.vegf2578")
    @Mapping(target = "vegf936", source = "geneticPredictors.vegf936")
    @Mapping(target = "egf", source = "geneticPredictors.egf")
    @Mapping(target = "gstt", source = "geneticPredictors.gstt")
    @Mapping(target = "gstm", source = "geneticPredictors.gstm")
    @Mapping(target = "gstp", source = "geneticPredictors.gstp")
    @Mapping(target = "natkpn", source = "geneticPredictors.natkpn")
    @Mapping(target = "nattag", source = "geneticPredictors.nattag")
    @Mapping(target = "natbam", source = "geneticPredictors.natbam")
    @Mapping(target = "acetylationType", source = "geneticPredictors.acetylationType")
    @Mapping(target = "cyp1a2", source = "geneticPredictors.cyp1a2")
    @Mapping(target = "cyp2d6", source = "geneticPredictors.cyp2d6")
    @Mapping(target = "mdr", source = "geneticPredictors.mdr")
    @Mapping(target = "egfr18Tumor", source = "geneticPredictors.egfr18Tumor")
    @Mapping(target = "egfr19Tumor", source = "geneticPredictors.egfr19Tumor")
    @Mapping(target = "egfr19Norm", source = "geneticPredictors.egfr19Norm")
    @Mapping(target = "egfr20Tumor", source = "geneticPredictors.egfr20Tumor")
    @Mapping(target = "egfr20Norm", source = "geneticPredictors.egfr20Norm")
    @Mapping(target = "egfr21Tumor", source = "geneticPredictors.egfr21Tumor")
    @Mapping(target = "egfr21Norm", source = "geneticPredictors.egfr21Norm")
    @Mapping(target = "egfr21Blood", source = "geneticPredictors.egfr21Blood")
    @Mapping(target = "tgf509", source = "geneticPredictors.tgf509")
    @Mapping(target = "tgf25Codon", source = "geneticPredictors.tgf25Codon")
    @Mapping(target = "tgfr206", source = "geneticPredictors.tgfr206")
    @Mapping(target = "kdr1719", source = "geneticPredictors.kdr1719")
    @Mapping(target = "kdr906", source = "geneticPredictors.kdr906")
    @Mapping(target = "sult1", source = "geneticPredictors.sult1")
    @Mapping(target = "mmp92660", source = "geneticPredictors.mmp92660")
    @Mapping(target = "mmp91562", source = "geneticPredictors.mmp91562")
    @Mapping(target = "mmp2735", source = "geneticPredictors.mmp2735")
    @Mapping(target = "mmp21575", source = "geneticPredictors.mmp21575")
    @Mapping(target = "kras2exTumor", source = "geneticPredictors.kras2exTumor")
    @Mapping(target = "pik3ca9ex", source = "geneticPredictors.pik3ca9ex")
    @Mapping(target = "pik3ca20ex", source = "geneticPredictors.pik3ca20ex")
    @Mapping(target = "pten", source = "geneticPredictors.pten")
    @Mapping(target = "dnmt149", source = "geneticPredictors.dnmt149")
    @Mapping(target = "dnmt579", source = "geneticPredictors.dnmt579")
    PatientClassifyModel toModel(Patient patient);
}
