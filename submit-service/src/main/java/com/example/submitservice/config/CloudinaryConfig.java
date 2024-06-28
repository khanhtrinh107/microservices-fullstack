package com.example.submitservice.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary c = new Cloudinary(ObjectUtils.asMap(
                "cloud_name" , "dskoc6e8s",
                "api_key","468395454874438",
                "api_secret","skMKfLgg1g1NsVyVAUEnU8Haeu8",
                "secure",true
        ));

        return c;
    }
}
