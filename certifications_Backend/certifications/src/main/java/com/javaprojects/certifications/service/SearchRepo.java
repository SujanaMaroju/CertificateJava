package com.javaprojects.certifications.service;

import com.javaprojects.certifications.model.User;

import java.util.List;

public interface SearchRepo {

    List<User> findByText(String text);

}