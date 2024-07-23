package com.ll.testspringrestapihttp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class TestSpringRestapihttpApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSpringRestapihttpApplication.class, args);
    }

}


class Coffee{
    private  String id;
    private String name;

    public Coffee(String id, String name){
        this.id = id;
        this.name = name;
    }

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
