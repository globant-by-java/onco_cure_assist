package com.globant.internal.oncocureassist.config;

import com.globant.internal.oncocureassist.classifier.DataClassifier;
import com.globant.internal.oncocureassist.classifier.DataClassifierCreator;
import com.globant.internal.oncocureassist.classifier.PatientDataClassifier;
import com.globant.internal.oncocureassist.classifier.PatientDataClassifierCreator;
import com.globant.internal.oncocureassist.repository.FileRepository;
import com.globant.internal.oncocureassist.repository.WekaFileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;

import java.io.File;
import java.nio.charset.Charset;

@Configuration
class WekaConfiguration {

    private final Resource wekaProperties;
    private final Resource wekaSql;
    private final DataSourceProperties dataSourceProperties;


    WekaConfiguration(@Value("classpath:weka/wekaDatabase.props") Resource wekaProperties,
                      @Value("classpath:weka/weka.sql") Resource wekaSql,
                      DataSourceProperties dataSourceProperties) {
        this.wekaProperties = wekaProperties;
        this.wekaSql = wekaSql;
        this.dataSourceProperties = dataSourceProperties;
    }


    @Bean
    DataClassifier patientDataClassifier() {
        return new PatientDataClassifier(fileRepository());
    }


    @Bean
    DataClassifierCreator patientDataClassifierCreator() throws Exception {
        return new PatientDataClassifierCreator(j48(), patientDataQuery(), numericToNominalFilter(), fileRepository());
    }


    @Bean
    InstanceQuery patientDataQuery() throws Exception {
        InstanceQuery query = new InstanceQuery();
        query.setCustomPropsFile(wekaProperties.getFile());
        query.setDatabaseURL(dataSourceProperties.getUrl());
        query.setUsername(dataSourceProperties.getUsername());
        query.setPassword(dataSourceProperties.getPassword());
        query.setQuery(StreamUtils.copyToString(wekaSql.getInputStream(), Charset.defaultCharset()));

        return query;
    }


    @Bean
    Filter numericToNominalFilter() throws Exception {
        String[] options = new String[2];
        options[0] = "-R";
        options[1] = "first-last";

        NumericToNominal filter = new NumericToNominal();
        filter.setOptions(options);

        return filter;
    }


    @Bean
    Classifier j48() {
        return new J48();
    }


    @Bean
    FileRepository fileRepository() {
        return new WekaFileRepository(wekaDir());
    }


    @Bean
    File wekaDir() {
        File dir = new File(System.getProperty("user.home") + File.separator + "weka");
        dir.mkdirs();

        return dir;
    }
}
