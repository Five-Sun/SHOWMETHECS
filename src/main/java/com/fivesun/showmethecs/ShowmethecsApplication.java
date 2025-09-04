package com.fivesun.showmethecs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ShowmethecsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowmethecsApplication.class, args);
	}

}
