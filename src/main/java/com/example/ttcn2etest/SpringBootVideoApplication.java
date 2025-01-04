package com.example.ttcn2etest;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootVideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootVideoApplication.class,args);
    }

    @Bean
    public Cloudinary cloudinary(){
        Cloudinary c = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dzbuoxe9a",
                "api_key", "364595734239524",
                "api_secret", "eZpPwJNmIHBct8H4FTmvlKGHLq8",
                "secure",true
        ));
        return c;
    }

}
