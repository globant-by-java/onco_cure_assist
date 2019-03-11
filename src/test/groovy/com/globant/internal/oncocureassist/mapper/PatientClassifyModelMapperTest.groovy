package com.globant.internal.oncocureassist.mapper

import com.globant.internal.oncocureassist.repository.entity.Patient
import com.globant.internal.oncocureassist.util.SampleDataProvider
import spock.lang.Specification

class PatientClassifyModelMapperTest extends Specification {

    def mapper = new PatientClassifyModelMapperImpl()


    def 'verify that mapper convert entity to model'() {
        given: 'create patient request'
            def patient = SampleDataProvider.createPatient() as Patient

            def expected = [
                    gender                        : 1, ageClass: 3,
                    surgeryApplied                : true, chemotherapyApplied: false,
                    hemotherapyApplied            : false, radiationTherapyApplied: true,
                    tumourSize                    : '1', bronchialCarcinoma: false,
                    lungCarcinoma                 : true, peribroncialCarcinoma: false,
                    interlobar                    : false, subcarinal: true,
                    lowerParatracheal             : false, upperParatracheal: true,
                    oncoAnamesys                  : false, stage: '2',
                    complains                     : false,
                    grade                         : 3, histologyDiagnosis: 5,
                    smoking                       : true, t: '2a',
                    n                             : '1', m: '0',
                    copd                          : false, tuberculomas: true,
                    chd                           : true, lcd: false,
                    inflammatoryDigestiveProcesses: true, hepatitis: false,
                    cirrhosis                     : true, pancreatitis: false,
                    musculoskeletalDiseases       : true, inflammatoryKidneysBladderDiseases: false,
                    prostateBenignDiseases        : true,
                    veinsDiseases                 : false, bloodVesselsDiseases: true,
                    rhythmDisturbances            : false, thyroidGlandBenignDiseases: true,
                    nervousDiseases               : false,
                    strokes                       : true, rheumaticDiseases: false,
                    anemia                        : true, vegf634: 'G/C',
                    vegf2578                      : 'A/A', vegf936: 'C/T',
                    egf                           : 'A/A', gstt: '1',
                    gstm                          : '0', gstp: 'G/G',
                    natkpn                        : 'C/T', nattag: 'G/G',
                    natbam                        : 'G/A', acetylationType: '1',
                    cyp1a2                        : 'C/C', cyp2d6: 'G/G',
                    mdr                           : 'T/T', egfr18Tumor: 'c.2203A/A',
                    egfr19Tumor                   : '2235_2249del', egfr19Norm: 'no',
                    egfr20Tumor                   : 'c.2361A/A', egfr20Norm: 'c.2361G/A',
                    egfr21Tumor                   : 'L858R', egfr21Norm: '2508C/T',
                    egfr21Blood                   : '2508C/T', tgf509: 'C/C',
                    tgf25Codon                    : 'G/C', tgfr206: 'C/T',
                    kdr1719                       : 'T/A', kdr906: 'T/T',
                    sult1                         : 'GG', mmp92660: 'G/G',
                    mmp91562                      : 'C/C', mmp2735: 'C/C',
                    mmp21575                      : 'G/G', kras2exTumor: 'c.35G/A',
                    pik3ca9ex                     : 'no', pik3ca20ex: 'no',
                    pten                          : 'no', dnmt149: 'T/T',
                    dnmt579                       : 'G/T', classId: 0
            ]


        when: 'send patient to mapper'
            def model = mapper.toModel(patient)

        then: 'model will return with all fields'
            noExceptionThrown()
            def actualModel = new HashMap<>(model.properties)
            actualModel.remove('class')
            actualModel.each { actual ->
                assert expected[actual.key] == actual.value
            }
    }


    def 'verify that mapper convert entity to model when all fields null'() {
        given: 'create empty patient request'
            def patient = SampleDataProvider.createEmptyPatient() as Patient

        when: 'send patient to mapper'
            def model = mapper.toModel(patient)

        then: 'model will return with all fields'
            noExceptionThrown()
            def actualModel = new HashMap<>(model.properties)
            actualModel.remove('class')
            actualModel.each { actual ->
                assert actual.value == null
            }
    }
}
