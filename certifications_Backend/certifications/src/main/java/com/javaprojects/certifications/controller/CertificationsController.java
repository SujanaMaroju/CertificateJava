package com.javaprojects.certifications.controller;

import com.javaprojects.certifications.model.Certification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CertificationsController {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CertificationsController( MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/certifications")
    public List<Certification> getCertifications(@RequestParam("collectionName") String collectionName){
        return  mongoTemplate.findAll(Certification.class, collectionName);
    }
}
