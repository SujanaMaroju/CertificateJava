package com.javaprojects.certifications.repos;

import com.javaprojects.certifications.model.Certification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CertificationRepo extends MongoRepository<Certification, String> {

}
