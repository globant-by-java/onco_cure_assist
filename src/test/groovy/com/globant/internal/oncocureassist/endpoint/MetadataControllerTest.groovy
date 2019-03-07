package com.globant.internal.oncocureassist.endpoint

import com.globant.internal.oncocureassist.AbstractIntegrationTest
import org.springframework.http.HttpStatus

class MetadataControllerTest extends AbstractIntegrationTest {

    def 'verify that we can retrieve all metadata'() {
        when:
            def response = getMetadata()

        then:
            noExceptionThrown()
            response.statusCode == HttpStatus.OK
            response.body.size() == 2
            response.body.patientMetadata.size() == 2
            response.body.geneticPredictorsMetadata.size() == 2

            response.body.patientMetadata.genders.size() == 2
            response.body.patientMetadata.genders.code == ['1', '0']
            response.body.patientMetadata.genders.name == ['MALE', 'FEMALE']

            response.body.patientMetadata.ageClasses.size() == 9
            response.body.patientMetadata.ageClasses.code == ['0', '1', '2', '3', '4', '5', '6', '7', '8']
            response.body.patientMetadata.ageClasses.name == ['[0-10)', '[10-20)', '[20-30)', '[30-40)', '[40-50)', '[50-60)', '[60-70)', '[70-80)', '[80-unbounded']

            response.body.geneticPredictorsMetadata.acetylationTypes.size() == 3
            response.body.geneticPredictorsMetadata.acetylationTypes.code == [null, '1', '0']
            response.body.geneticPredictorsMetadata.acetylationTypes.name == ['', 'FAST', 'SLOW']

            response.body.geneticPredictorsMetadata.genotypes.size() == 39
            def genotypeMetadata = response.body.geneticPredictorsMetadata.genotypes
            genotypeMetadata.GSTM == [[code: null, name: ''], [code: '1', name: '1'], [code: '0', name: '0']]
            genotypeMetadata.MMP_2735 == [[code: null, name: ''], [code: 'C/C', name: 'C/C'], [code: 'C/T', name: 'C/T'], [code: 'T/T', name: 'T/T']]
            genotypeMetadata.VEGF_634 == [[code: null, name: ''], [code: 'G/G', name: 'G/G'], [code: 'G/C', name: 'G/C'], [code: 'C/C', name: 'C/C']]
            genotypeMetadata.PTEN == [[code: null, name: ''], [code: 'NO', name: 'no']]
            genotypeMetadata.GSTP == [[code: null, name: ''], [code: 'A/A', name: 'A/A'], [code: 'G/A', name: 'G/A'], [code: 'G/G', name: 'G/G']]
            genotypeMetadata.VEGF_936 == [[code: null, name: ''], [code: 'C/C', name: 'C/C'], [code: 'C/T', name: 'C/T'], [code: 'T/T', name: 'T/T']]
            genotypeMetadata.TGF_509 == [[code: null, name: ''], [code: 'C/C', name: 'C/C'], [code: 'C/T', name: 'C/T'], [code: 'T/T', name: 'T/T']]
            genotypeMetadata.EGFR_18_TUMOR == [[code: null, name: ''], [code: 'NO', name: 'no'], [code: 'C.2203G/A', name: 'c.2203G/A'], [code: 'C.2203G/G', name: 'c.2203G/G'], [code: 'C.2203A/A', name: 'c.2203A/A']]
            genotypeMetadata.GSTT == [[code: null, name: ''], [code: '1', name: '1'], [code: '0', name: '0']]
            genotypeMetadata.DNMT_149 == [[code: null, name: ''], [code: 'C/C', name: 'C/C'], [code: 'C/T', name: 'C/T'], [code: 'T/T', name: 'T/T']]
            genotypeMetadata.KRAS_2_EX_TUMOR == [[code: null, name: ''], [code: 'NO', name: 'no'], [code: 'C.35G/A', name: 'c.35G/A'], [code: 'C.37G/T', name: 'c.37G/T'], [code: 'C.34G/T', name: 'c.34G/T']]
            genotypeMetadata.EGFR_20_NORM == [[code: null, name: ''], [code: 'NO', name: 'no'], [code: 'C.2361G/A', name: 'c.2361G/A']]
            genotypeMetadata.MMP_21575 == [[code: null, name: ''], [code: 'G/G', name: 'G/G'], [code: 'G/A', name: 'G/A'], [code: 'A/A', name: 'A/A']]
            genotypeMetadata.MMP_91562 == [[code: null, name: ''], [code: 'C/C', name: 'C/C'], [code: 'C/T', name: 'C/T']]
            genotypeMetadata.NAT_KPN == [[code: null, name: ''], [code: 'C/C', name: 'C/C'], [code: 'C/T', name: 'C/T'], [code: 'T/T', name: 'T/T']]
            genotypeMetadata.PIK_3_CA_20_EX == [[code: null, name: ''], [code: 'NO', name: 'no']]
            genotypeMetadata.TGF_R_206 == [[code: null, name: ''], [code: 'C/C', name: 'C/C'], [code: 'C/T', name: 'C/T'], [code: 'T/T', name: 'T/T']]
            genotypeMetadata.MDR == [[code: null, name: ''], [code: 'C/C', name: 'C/C'], [code: 'C/T', name: 'C/T'], [code: 'T/T', name: 'T/T']]
            genotypeMetadata.EGFR_18_NORM == [[code: null, name: ''], [code: 'NO', name: 'no']]
            genotypeMetadata.CYP_1_A_2 == [[code: null, name: ''], [code: 'C/C', name: 'C/C'], [code: 'C/A', name: 'C/A'], [code: 'A/A', name: 'A/A']]
            genotypeMetadata.TGF_25_CORDON == [[code: null, name: ''], [code: 'G/G', name: 'G/G'], [code: 'G/C', name: 'G/C'], [code: 'C/C', name: 'C/C']]
            genotypeMetadata.NAT_TAG == [[code: null, name: ''], [code: 'G/G', name: 'G/G'], [code: 'G/A', name: 'G/A'], [code: 'A/A', name: 'A/A']]
            genotypeMetadata.EGFR_19_TUMOR == [[code: null, name: ''], [code: 'NO', name: 'no'], [code: '2235_2249DEL', name: '2235_2249del'], [code: '2236_2250DEL', name: '2236_2250del'], [code: '2240_2257DEL', name: '2240_2257del']]
            genotypeMetadata.CYP_2_D_6 == [[code: null, name: ''], [code: 'G/G', name: 'G/G'], [code: 'G/A', name: 'G/A'], [code: 'A/A', name: 'A/A']]
            genotypeMetadata.EGF == [[code: null, name: ''], [code: 'A/A', name: 'A/A'], [code: 'G/A', name: 'G/A'], [code: 'G/G', name: 'G/G']]
            genotypeMetadata.KDR_1719 == [[code: null, name: ''], [code: 'T/T', name: 'T/T'], [code: 'T/A', name: 'T/A'], [code: 'A/A', name: 'A/A']]
            genotypeMetadata.KRAS_2_EX_NORM == [[code: null, name: ''], [code: 'NO', name: 'no']]
            genotypeMetadata.EGFR_20_TUMOR == [[code: null, name: ''], [code: 'NO', name: 'no'], [code: 'C.2361A/A', name: 'c.2361A/A'], [code: 'C.2361G/G', name: 'c.2361G/G'], [code: 'C.2361G/A', name: 'c.2361G/A'], [code: 'P.A763_Y764INSFQEA', name: 'p.A763_Y764insFQEA']]
            genotypeMetadata.NAT_BAM == [[code: null, name: ''], [code: 'G/G', name: 'G/G'], [code: 'G/A', name: 'G/A'], [code: 'A/A', name: 'A/A']]
            genotypeMetadata.PIK_3_CA_9_EX == [[code: null, name: ''], [code: 'NO', name: 'no']]
            genotypeMetadata.SULT_1 == [[code: null, name: ''], [code: 'GA', name: 'GA'], [code: 'GG', name: 'GG']]
            genotypeMetadata.DNMT_579 == [[code: null, name: ''], [code: 'G/G', name: 'G/G'], [code: 'G/T', name: 'G/T'], [code: 'T/T', name: 'T/T']]
            genotypeMetadata.MMP_92660 == [[code: null, name: ''], [code: 'A/A', name: 'A/A'], [code: 'G/A', name: 'G/A'], [code: 'G/G', name: 'G/G']]
            genotypeMetadata.EGFR_21_BLOOD == [[code: null, name: ''], [code: 'NO', name: 'no'], [code: '2508C/T', name: '2508C/T']]
            genotypeMetadata.KDR_906 == [[code: null, name: ''], [code: 'T/T', name: 'T/T'], [code: 'T/C', name: 'T/C'], [code: 'C/C', name: 'C/C']]
            genotypeMetadata.EGFR_19_NORM == [[code: null, name: ''], [code: 'NO', name: 'no']]
            genotypeMetadata.EGFR_21_NORM == [[code: null, name: ''], [code: 'NO', name: 'no'], [code: '2508C/T', name: '2508C/T']]
            genotypeMetadata.EGFR_21_TUMOR == [[code: null, name: ''], [code: 'NO', name: 'no'], [code: '2508C/T', name: '2508C/T'], [code: 'L858R', name: 'L858R']]
            genotypeMetadata.VEGF_2578 == [[code: null, name: ''], [code: 'C/C', name: 'C/C'], [code: 'A/C', name: 'A/C'], [code: 'A/A', name: 'A/A']]
    }
}
