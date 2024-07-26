package com.ll.testspringrestapihttp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import java.util.UUID;


@SpringBootApplication
@ConfigurationPropertiesScan
public class TestSpringRestapihttpApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSpringRestapihttpApplication.class, args);
    }

}

@Entity
class Coffee{
    @Id
    private String id;
    private String name;

    public Coffee(String id, String name){
        this.id = id;
        this.name = name;
    }

    public Coffee() {}

    public Coffee(String name){
        this(UUID.randomUUID().toString(), name);
    }

    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }

}

