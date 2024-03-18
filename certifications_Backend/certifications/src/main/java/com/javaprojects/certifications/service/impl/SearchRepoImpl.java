package com.javaprojects.certifications.service.impl;

import com.javaprojects.certifications.model.User;
import com.javaprojects.certifications.service.SearchRepo;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SearchRepoImpl implements SearchRepo {

    private final MongoClient mongoClient;
    private final MongoConverter converter;

    @Autowired
    public SearchRepoImpl( MongoClient mongoClient, MongoConverter converter) {
        this.mongoClient = mongoClient;
        this.converter = converter;
        
    }

    @Override
    public List<User> findByText(String text) {
        final List<User> users = new ArrayList<>();

        MongoDatabase database = mongoClient.getDatabase("certifications");
        MongoCollection<Document> collection = database.getCollection("users");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                                new Document("certifications",
                                                new Document("$elemMatch",
                                                new Document("certificationName", text)))),
                                                new Document("$sort",
                                                new Document("experience", 1L))));

        result.forEach(doc->users.add(converter.read(User.class,doc)));
        return users;
    }
}
