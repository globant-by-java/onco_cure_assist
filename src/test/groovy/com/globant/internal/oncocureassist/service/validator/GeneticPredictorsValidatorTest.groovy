package com.globant.internal.oncocureassist.service.validator

import com.globant.internal.oncocureassist.repository.entity.GeneticPredictors
import com.globant.internal.oncocureassist.util.SampleDataProvider
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.Validator

class GeneticPredictorsValidatorTest extends Specification {

    def jsrValidator = Mock(Validator)
    def messageSource = Mock(MessageSource)
    def validator = new GeneticPredictorsValidator(jsrValidator, messageSource)


    @Unroll()
    def "validate genetic predictors when #fieldName = #fieldValue"() {
        given:
            def geneticPredictors = SampleDataProvider.createPatient().geneticPredictors as GeneticPredictors
            geneticPredictors[fieldName] = fieldValue

        when:
            def errors = validator.validate(geneticPredictors)

        then:
            errorMessageCount * messageSource.getMessage('genetic.predictions.field.validation.error', _, LocaleContextHolder.getLocale()) >> 'error'
            0 * _
            noExceptionThrown()
            errors.size() == errorMessageCount
            if (errorMessageCount == 1) {
                assert !errors[0].code
                assert errors[0].object == 'GeneticPredictors'
                assert errors[0].field == fieldName
                assert errors[0].description == 'error'
            }

        where:
            fieldValue           | fieldName         || errorMessageCount
            null                 | 'acetylationType' || 0
            '1'                  | 'acetylationType' || 0
            '0'                  | 'acetylationType' || 0
            'fake'               | 'acetylationType' || 1

            null                 | 'vegf634'         || 0
            'G/G'                | 'vegf634'         || 0
            'G/C'                | 'vegf634'         || 0
            'C/C'                | 'vegf634'         || 0
            'fake'               | 'vegf634'         || 1

            null                 | 'vegf2578'        || 0
            'C/C'                | 'vegf2578'        || 0
            'A/C'                | 'vegf2578'        || 0
            'A/A'                | 'vegf2578'        || 0
            'fake'               | 'vegf2578'        || 1

            null                 | 'vegf936'         || 0
            'C/C'                | 'vegf936'         || 0
            'C/T'                | 'vegf936'         || 0
            'T/T'                | 'vegf936'         || 0
            'fake'               | 'vegf936'         || 1

            null                 | 'egf'             || 0
            'A/A'                | 'egf'             || 0
            'G/A'                | 'egf'             || 0
            'G/G'                | 'egf'             || 0
            'fake'               | 'egf'             || 1

            null                 | 'gstp'            || 0
            'A/A'                | 'gstp'            || 0
            'G/A'                | 'gstp'            || 0
            'G/G'                | 'gstp'            || 0
            'fake'               | 'gstp'            || 1

            null                 | 'natkpn'          || 0
            'C/C'                | 'natkpn'          || 0
            'C/T'                | 'natkpn'          || 0
            'T/T'                | 'natkpn'          || 0
            'fake'               | 'natkpn'          || 1

            null                 | 'nattag'          || 0
            'A/A'                | 'nattag'          || 0
            'G/A'                | 'nattag'          || 0
            'G/G'                | 'nattag'          || 0
            'fake'               | 'nattag'          || 1

            null                 | 'natbam'          || 0
            'A/A'                | 'natbam'          || 0
            'G/A'                | 'natbam'          || 0
            'G/G'                | 'natbam'          || 0
            'fake'               | 'natbam'          || 1

            null                 | 'cyp1a2'          || 0
            'C/C'                | 'cyp1a2'          || 0
            'C/A'                | 'cyp1a2'          || 0
            'A/A'                | 'cyp1a2'          || 0
            'fake'               | 'cyp1a2'          || 1

            null                 | 'cyp2d6'          || 0
            'A/A'                | 'cyp2d6'          || 0
            'G/A'                | 'cyp2d6'          || 0
            'G/G'                | 'cyp2d6'          || 0
            'fake'               | 'cyp2d6'          || 1

            null                 | 'mdr'             || 0
            'C/C'                | 'mdr'             || 0
            'C/T'                | 'mdr'             || 0
            'T/T'                | 'mdr'             || 0
            'fake'               | 'mdr'             || 1

            null                 | 'tgf509'          || 0
            'C/C'                | 'tgf509'          || 0
            'C/T'                | 'tgf509'          || 0
            'T/T'                | 'tgf509'          || 0
            'fake'               | 'tgf509'          || 1

            null                 | 'tgf25Codon'      || 0
            'G/G'                | 'tgf25Codon'      || 0
            'G/C'                | 'tgf25Codon'      || 0
            'C/C'                | 'tgf25Codon'      || 0
            'fake'               | 'tgf25Codon'      || 1

            null                 | 'tgfr206'         || 0
            'C/C'                | 'tgfr206'         || 0
            'C/T'                | 'tgfr206'         || 0
            'T/T'                | 'tgfr206'         || 0
            'fake'               | 'tgfr206'         || 1

            null                 | 'kdr1719'         || 0
            'T/T'                | 'kdr1719'         || 0
            'T/A'                | 'kdr1719'         || 0
            'A/A'                | 'kdr1719'         || 0
            'fake'               | 'kdr1719'         || 1

            null                 | 'kdr906'          || 0
            'C/C'                | 'kdr906'          || 0
            'T/C'                | 'kdr906'          || 0
            'T/T'                | 'kdr906'          || 0
            'fake'               | 'kdr906'          || 1

            null                 | 'mmp92660'        || 0
            'A/A'                | 'mmp92660'        || 0
            'G/A'                | 'mmp92660'        || 0
            'G/G'                | 'mmp92660'        || 0
            'fake'               | 'mmp92660'        || 1

            null                 | 'mmp91562'        || 0
            'A/A'                | 'mmp91562'        || 0
            'G/A'                | 'mmp91562'        || 0
            'G/G'                | 'mmp91562'        || 0
            'fake'               | 'mmp91562'        || 1

            null                 | 'mmp2735'         || 0
            'C/C'                | 'mmp2735'         || 0
            'C/T'                | 'mmp2735'         || 0
            'T/T'                | 'mmp2735'         || 0
            'fake'               | 'mmp2735'         || 1

            null                 | 'mmp21575'        || 0
            'A/A'                | 'mmp21575'        || 0
            'G/A'                | 'mmp21575'        || 0
            'G/G'                | 'mmp21575'        || 0
            'fake'               | 'mmp21575'        || 1

            null                 | 'dnmt149'         || 0
            'C/C'                | 'dnmt149'         || 0
            'C/T'                | 'dnmt149'         || 0
            'T/T'                | 'dnmt149'         || 0
            'fake'               | 'dnmt149'         || 1

            null                 | 'dnmt579'         || 0
            'G/G'                | 'dnmt579'         || 0
            'G/T'                | 'dnmt579'         || 0
            'T/T'                | 'dnmt579'         || 0
            'fake'               | 'dnmt579'         || 1

            null                 | 'sult1'           || 0
            'GG'                 | 'sult1'           || 0
            'GA'                 | 'sult1'           || 0
            'fake'               | 'sult1'           || 1

            null                 | 'gstt'            || 0
            '1'                  | 'gstt'            || 0
            '0'                  | 'gstt'            || 0
            'fake'               | 'gstt'            || 1

            null                 | 'gstm'            || 0
            '1'                  | 'gstm'            || 0
            '0'                  | 'gstm'            || 0
            'fake'               | 'gstm'            || 1

            null                 | 'egfr18Tumor'     || 0
            'no'                 | 'egfr18Tumor'     || 0
            'c.2203G/A'          | 'egfr18Tumor'     || 0
            'c.2203G/G'          | 'egfr18Tumor'     || 0
            'c.2203A/A'          | 'egfr18Tumor'     || 0
            'fake'               | 'egfr18Tumor'     || 1

            null                 | 'egfr18Norm'      || 0
            'no'                 | 'egfr18Norm'      || 0
            'fake'               | 'egfr18Norm'      || 1

            null                 | 'egfr19Tumor'     || 0
            'no'                 | 'egfr19Tumor'     || 0
            '2235_2249del'       | 'egfr19Tumor'     || 0
            '2236_2250del'       | 'egfr19Tumor'     || 0
            '2240_2257del'       | 'egfr19Tumor'     || 0
            'fake'               | 'egfr19Tumor'     || 1

            null                 | 'egfr19Norm'      || 0
            'no'                 | 'egfr19Norm'      || 0
            'fake'               | 'egfr19Norm'      || 1

            null                 | 'egfr20Tumor'     || 0
            'no'                 | 'egfr20Tumor'     || 0
            'c.2361A/A'          | 'egfr20Tumor'     || 0
            'c.2361G/G'          | 'egfr20Tumor'     || 0
            'c.2361G/A'          | 'egfr20Tumor'     || 0
            'p.A763_Y764insFQEA' | 'egfr20Tumor'     || 0
            'fake'               | 'egfr20Tumor'     || 1

            null                 | 'egfr20Norm'      || 0
            'no'                 | 'egfr20Norm'      || 0
            'c.2361G/A'          | 'egfr20Norm'      || 0
            'fake'               | 'egfr20Norm'      || 1

            null                 | 'egfr21Tumor'     || 0
            'no'                 | 'egfr21Tumor'     || 0
            '2508C/T'            | 'egfr21Tumor'     || 0
            'L858R'              | 'egfr21Tumor'     || 0
            'fake'               | 'egfr21Tumor'     || 1

            null                 | 'egfr21norm'      || 0
            'no'                 | 'egfr21norm'      || 0
            '2508C/T'            | 'egfr21norm'      || 0
            'fake'               | 'egfr21norm'      || 1

            null                 | 'egfr21Blood'     || 0
            'no'                 | 'egfr21Blood'     || 0
            '2508C/T'            | 'egfr21Blood'     || 0
            'fake'               | 'egfr21Blood'     || 1

            null                 | 'kras2exTumor'    || 0
            'no'                 | 'kras2exTumor'    || 0
            'c.35G/A'            | 'kras2exTumor'    || 0
            'c.35G/A'            | 'kras2exTumor'    || 0
            'c.34G/T'            | 'kras2exTumor'    || 0
            'fake'               | 'kras2exTumor'    || 1

            null                 | 'kras2exNorm'     || 0
            'no'                 | 'kras2exNorm'     || 0
            'fake'               | 'kras2exNorm'     || 1

            null                 | 'pik3ca9ex'       || 0
            'no'                 | 'pik3ca9ex'       || 0
            'fake'               | 'pik3ca9ex'       || 1

            null                 | 'pik3ca20ex'      || 0
            'no'                 | 'pik3ca20ex'      || 0
            'fake'               | 'pik3ca20ex'      || 1

            null                 | 'pten'            || 0
            'no'                 | 'pten'            || 0
            'fake'               | 'pten'            || 1
    }
}
