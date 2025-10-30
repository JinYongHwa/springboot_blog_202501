package kr.ac.mjc.blog01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Blog01Application {

	public static void main(String[] args) {
		SpringApplication.run(Blog01Application.class, args);
	}

}
