package com.javaprojects.certifications.repos;

import com.javaprojects.certifications.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {

        User findByEmail(String email);

}
