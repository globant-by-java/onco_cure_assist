package com.globant.internal.oncocureassist.util


import java.time.LocalDate

class SampleDataProvider {


    private static final DEFAULT_PATIENT = [fullName         : 'fullName',
                                            gender           : 1,
                                            birthDate        : LocalDate.now().minusYears(20),
                                            contactDate      : LocalDate.now(),
                                            alive            : true,
                                            survivalMonth    : 3,
                                            employed         : false,
                                            professionCode   : 4,
                                            address          : 'address',
                                            phone            : 'phone',
                                            cardNumber       : 'cardNumber',
                                            classId          : 1,
                                            age              : 20,
                                            ageClass         : 2,
                                            deleted          : false,
                                            treatment        : [:],
                                            diagnostics      : [:],
                                            geneticPredictors: [:]]


    private static final DEFAULT_TREATMENT = [surgeryApplied         : true,
                                              chemotherapyApplied    : false,
                                              radiationTherapyApplied: true,
                                              surgeryCode            : 5,
                                              surgeonName            : 'surgeonName',
                                              surgeryDate            : LocalDate.now().plusDays(5),
                                              firstLineCourse        : 6,
                                              secondLineCourse       : 7,
                                              thirdLineCourse        : 8
    ]


    private static final DEFAULT_DIAGNOSTICS = [tnm                               : 'tnm',
                                                primaryTumour                     : 1,
                                                regionalLymphNodes                : 2,
                                                distantMetastasis                 : 3,
                                                tumourSize                        : 4L,
                                                side                              : 5,
                                                bronchialCarcinoma                : false,
                                                lungCarcinoma                     : true,
                                                peribroncialCarcinoma             : false,
                                                interlobar                        : false,
                                                subcarinal                        : true,
                                                lowerParatracheal                 : false,
                                                upperParatracheal                 : true,
                                                sameLungMetastasis                : false,
                                                symptoms                          : true,
                                                vlc                               : 1.0,
                                                tvc                               : 2.0,
                                                tiffNumber                        : 3.0,
                                                volumeForceExp                    : 4.0,
                                                heartRate                         : 40,
                                                heartBlock                        : false,
                                                extrasistols                      : true,
                                                somatic                           : false,
                                                bac                               : true,
                                                oncoAnamesys                      : false,
                                                stage                             : 'stage',
                                                complains                         : false,
                                                grade                             : 3,
                                                histologyDiagnosis                : 5,
                                                smoking                           : true,
                                                hystologyCode                     : 'hystologyCode',
                                                t                                 : 't',
                                                m                                 : 'm',
                                                n                                 : 'n',
                                                copd                              : false,
                                                tuberculomas                      : true,
                                                chd                               : true,
                                                lcd                               : false,
                                                inflammatoryDigestiveProcesses    : true,
                                                hepatitis                         : false,
                                                cirrhosis                         : true,
                                                pancreatitis                      : false,
                                                musculoskeletalDiseases           : true,
                                                inflammatoryKidneysBladderDiseases: false,
                                                prostateBenignDiseases            : true,
                                                veinsDiseases                     : false,
                                                bloodVesselsDiseases              : true,
                                                rhythmDisturbances                : false,
                                                thyroidGlandBenignDiseases        : true,
                                                nervousDiseases                   : false,
                                                strokes                           : true,
                                                rheumaticDiseases                 : false,
                                                anemia                            : true
    ]


    private static def DEFAULT_GENETIC_PREDICTORS = [vegf634        : 'vegf634',
                                                     vegf2578       : 'vegf2578',
                                                     vegf936        : 'vegf936',
                                                     egf            : 'egf',
                                                     gstt           : 'gstt',
                                                     gstm           : 'gstm',
                                                     gstp           : 'gstp',
                                                     natkpn         : 'natkpn',
                                                     nattag         : 'nattag',
                                                     natbam         : 'natbam',
                                                     acetylationType: 'acetylationType',
                                                     cyp1a2         : 'cyp1a2',
                                                     cyp2d6         : 'cyp2d6',
                                                     mdr            : 'mdr',
                                                     egfr18Tumor    : 'egfr18Tumor',
                                                     egfr18Norm     : 'egfr18Norm',
                                                     egfr19Tumor    : 'egfr19Tumor',
                                                     egfr19Norm     : 'egfr19Norm',
                                                     egfr20Tumor    : 'egfr20Tumor',
                                                     egfr20Norm     : 'egfr20Norm',
                                                     egfr21Tumor    : 'egfr21Tumor',
                                                     egfr21norm     : 'egfr21norm',
                                                     egfr21Blood    : 'egfr21Blood',
                                                     tgf509         : 'tgf509',
                                                     tgf25Codon     : 'tgf25Codon',
                                                     tgfr206        : 'tgfr206',
                                                     kdr1719        : 'kdr1719',
                                                     kdr906         : 'kdr906',
                                                     sult1          : 'sult1',
                                                     mmp92660       : 'mmp92660',
                                                     mmp91562       : 'mmp91562',
                                                     mmp2735        : 'mmp2735',
                                                     mmp21575       : 'mmp21575',
                                                     kras2exTumor   : 'kras2exTumor',
                                                     kras2exNorm    : 'kras2exNorm',
                                                     pik3ca9ex      : 'pik3ca9ex',
                                                     pik3ca20ex     : 'pik3ca20ex',
                                                     pten           : 'pten',
                                                     dnmt149        : 'dnmt149',
                                                     dnmt579        : 'dnmt579'
    ]


    static Map<String, Object> createPatient(def patientData = [:]) {
        def patient = DEFAULT_PATIENT.clone()
        def treatment = DEFAULT_TREATMENT.clone()
        def diagnostics = DEFAULT_DIAGNOSTICS.clone()
        def geneticPredictors = DEFAULT_GENETIC_PREDICTORS.clone()

        patient.treatment = treatment
        patient.diagnostics = diagnostics
        patient.geneticPredictors = geneticPredictors

        patient += patientData

        return patient
    }


    static Map<String, Object> createEmptyPatient() {
        def patient = DEFAULT_PATIENT.clone()
        patient.each {
            if (it.value != [:]) {
                it.value = null
            }
        }

        return patient
    }
}
