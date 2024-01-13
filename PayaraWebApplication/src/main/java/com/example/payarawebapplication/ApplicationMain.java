package com.example.payarawebapplication;

import com.example.payarawebapplication.resouce.UserResource;
import com.example.payarawebapplication.service.UserService;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/main")
public class ApplicationMain extends Application {
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(UserResource.class);
        classes.add(UserService.class);
        return classes;
    }
}