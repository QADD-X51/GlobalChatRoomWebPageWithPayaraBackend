package com.example.payarawebapplication;

import com.example.payarawebapplication.resouce.MessageResource;
import com.example.payarawebapplication.resouce.UserResource;
import com.example.payarawebapplication.service.MessageService;
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
        classes.add(MessageResource.class);
        classes.add(MessageService.class);

        classes.add(CorsFilter.class);
        return classes;
    }
}