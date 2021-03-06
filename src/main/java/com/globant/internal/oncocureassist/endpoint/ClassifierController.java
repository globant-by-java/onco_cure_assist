package com.globant.internal.oncocureassist.endpoint;

import com.globant.internal.oncocureassist.domain.model.ClassifierModel;
import com.globant.internal.oncocureassist.service.ClassifierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/classifiers")
public class ClassifierController {

    private final ClassifierService classifierService;


    public ClassifierController(ClassifierService classifierService) {
        this.classifierService = classifierService;
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void createClassifier() {
        List<ClassifierModel> classifiers = classifierService.findAll();
        int version = classifiers.isEmpty() ? 1 : (classifiers.get(classifiers.size() - 1).getVersion() + 1);
        classifierService.create(version);
    }


    @GetMapping
    public List<ClassifierModel> findAll() {
        return classifierService.findAll();
    }


    @GetMapping("/{version}")
    public ResponseEntity<ClassifierModel> findByVersion(@PathVariable Integer version) {
        return classifierService.findAll().stream()
                .filter(model -> model.getVersion() == version)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
