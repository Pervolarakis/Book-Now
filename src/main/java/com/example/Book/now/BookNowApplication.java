package com.example.Book.now;

import com.example.Book.now.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class BookNowApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookNowApplication.class, args);
	}

}
