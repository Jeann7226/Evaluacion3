package com.manga.mangas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(excludeName = {
	"org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration"
})
public class MangasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangasApplication.class, args);
	}

}
