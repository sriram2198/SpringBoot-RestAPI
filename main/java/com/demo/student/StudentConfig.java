package com.demo.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentDAO repository) {
		
		return args -> {
			Student sindhu = new Student(
					"sindhu",
					"sindhu@gmail.com",
					LocalDate.of(1988, 12, 24)
			);
			
			Student krittika = new Student(
					"krittika",
					"krittika@gmail.com",
					LocalDate.of(1994, 12, 16)
			);
			
			repository.saveAll(List.of(sindhu, krittika));
		};
	}

}
