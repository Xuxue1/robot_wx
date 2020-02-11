package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class Main {

    static{
        TimeZone.setDefault(TimeZone.getTimeZone("PRC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

}
