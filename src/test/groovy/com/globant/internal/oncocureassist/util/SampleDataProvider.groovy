package com.globant.internal.oncocureassist.util


import java.time.LocalDate

class SampleDataProvider {


    private static final DEFAULT_PATIENT = [fullName         : 'fullName',
                                            gender           : 1,
                                            birthDate        : LocalDate.now().minusYears(20),
                                            contactDate      : LocalDate.now(),
                                            alive            : true,
                                            survivalMonth    : 0,
                                            employed         : false,
                                            professionCode   : 4,
                                            address          : 'address',
                                            phone            : 'phone',
                                            cardNumber       : 'cardNumber',
                                            classId          : 0,
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
                                              surgeryDate            : LocalDate.now(),
                                              firstLineCourse        : 6,
                                              secondLineCourse       : 7,
                                              thirdLineCourse        : 8
    ]


    private static final DEFAULT_DIAGNOSTICS = [tnm                               : 'T1aN0M0',
                                                primaryTumour                     : 1,
                                                regionalLymphNodes                : 2,
                                                distantMetastasis                 : 3,
                                                tumourSize                        : 4L,
                                                side                              : 2,
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
                                                histologyCode                     : 'histologyCode',
                                                t                                 : '1',
                                                m                                 : '0',
                                                n                                 : '0',
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


    private static def DEFAULT_GENETIC_PREDICTORS = [vegf634        : 'G/C',
                                                     vegf2578       : 'A/A',
                                                     vegf936        : 'C/T',
                                                     egf            : 'A/A',
                                                     gstt           : '1',
                                                     gstm           : '0',
                                                     gstp           : 'G/G',
                                                     natkpn         : 'C/T',
                                                     nattag         : 'G/G',
                                                     natbam         : 'A/A',
                                                     acetylationType: '1',
                                                     cyp1a2         : 'C/C',
                                                     cyp2d6         : 'G/G',
                                                     mdr            : 'T/T',
                                                     egfr18Tumor    : 'c.2203A/A',
                                                     egfr18Norm     : 'no',
                                                     egfr19Tumor    : '2235_2249del',
                                                     egfr19Norm     : 'no',
                                                     egfr20Tumor    : 'c.2361A/A',
                                                     egfr20Norm     : 'c.2361G/A',
                                                     egfr21Tumor    : 'L858R',
                                                     egfr21norm     : '2508C/T',
                                                     egfr21Blood    : '2508C/T',
                                                     tgf509         : 'C/C',
                                                     tgf25Codon     : 'G/C',
                                                     tgfr206        : 'C/T',
                                                     kdr1719        : 'T/A',
                                                     kdr906         : 'T/T',
                                                     sult1          : 'GG',
                                                     mmp92660       : 'G/G',
                                                     mmp91562       : 'G/A',
                                                     mmp2735        : 'C/C',
                                                     mmp21575       : 'G/G',
                                                     kras2exTumor   : 'c.35G/A',
                                                     kras2exNorm    : 'no',
                                                     pik3ca9ex      : 'no',
                                                     pik3ca20ex     : 'no',
                                                     pten           : 'no',
                                                     dnmt149        : 'T/T',
                                                     dnmt579        : 'G/T'
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
