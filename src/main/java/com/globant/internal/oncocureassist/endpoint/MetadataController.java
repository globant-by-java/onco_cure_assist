package com.globant.internal.oncocureassist.endpoint;

import com.globant.internal.oncocureassist.domain.metadata.GlobalMetadata;
import com.globant.internal.oncocureassist.service.MetadataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/metadata")
public class MetadataController {

    private final MetadataService metadataService;


    public MetadataController(MetadataService metadataService) {
        this.metadataService = metadataService;
    }


    @GetMapping
    public GlobalMetadata get() {
        return metadataService.provideMetadata();
    }
}
