package com.globant.internal.oncocureassist;

import com.globant.internal.oncocureassist.domain.model.ClassifierModel;
import com.globant.internal.oncocureassist.service.ClassifierService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class OncocureassistApplication implements ApplicationRunner {

    private final ClassifierService classifierService;


    OncocureassistApplication(ClassifierService classifierService) {
        this.classifierService = classifierService;
    }


    public static void main(String[] args) {
        SpringApplication.run(OncocureassistApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) {
        List<ClassifierModel> classifiers = classifierService.findAll();
        if (classifiers.isEmpty()) {
            Integer version = 1;
            classifierService.create(version);
        }
    }
}

