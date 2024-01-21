package com.example.payarawebapplication;

import com.example.payarawebapplication.resouce.MessageResource;
import com.example.payarawebapplication.resouce.UserResource;
import com.example.payarawebapplication.service.MessageServiceBean;
import com.example.payarawebapplication.service.UserServiceBean;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/main")
public class ApplicationMain extends Application {
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(UserServiceBean.class);
        classes.add(MessageServiceBean.class);
        classes.add(UserResource.class);
        classes.add(MessageResource.class);

        classes.add(CorsFilter.class);
        classes.add(MyExceptionMapper.class);
        return classes;
    }
}