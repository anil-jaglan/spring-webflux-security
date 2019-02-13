package com.reactive.controller;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FileUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    private static final String FILE_DIRECTORY_PATH = "E:\\";

    @PostMapping(value = "/public/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> uploadFile(@RequestPart("file") Flux<Part> parts) {
        LOGGER.info("Passing through file uploading controller...");
        return processFiles(parts);
    }

    private static Mono<String> processFiles(Flux<? extends Part> partsFlux) {
        return partsFlux.collectList().map(FileUploadController::partListDescription);
    }

    private static String partListDescription(List<? extends Part> parts) {
        //@formatter:off
        parts
        .stream()
        .filter(part -> part instanceof FilePart)
        .map(FilePart.class::cast)
        .forEach(file -> file.transferTo(new File(FILE_DIRECTORY_PATH + file.filename())));
        
        return parts
                .stream()
                .map(FileUploadController::partDescription)
                .collect(Collectors.joining(",", "[", "]"));
        //@formatter:on
    }

    private static String partDescription(Part part) {
        return part instanceof FilePart ? part.name() + ":" + ((FilePart) part).filename() : part.name();
    }

}
