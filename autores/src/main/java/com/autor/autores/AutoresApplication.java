package com.autor.autores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(excludeName = {
	"org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration"
})
public class AutoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoresApplication.class, args);
	}

}
