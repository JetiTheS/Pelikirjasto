package com.example.pelikirjasto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.pelikirjasto.domain.*;

@SpringBootApplication
public class PelikirjastoApplication {
	private static final Logger log = LoggerFactory.getLogger(PelikirjastoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PelikirjastoApplication.class, args);
	}

	@Bean
	public CommandLineRunner peliDemo(PeliRepository pelirepository, KategoriaRepository kategoriarepository,
			PeliUserRepository peliUserRepository) {
		return (args) -> {

			log.info("tallennetaan muutama kategoria");
			Kategoria kategoria1 = new Kategoria("Verkkoyhteispeli");
			Kategoria kategoria2 = new Kategoria("Kauhu");
			Kategoria kategoria3 = new Kategoria("Fantasia");
			Kategoria kategoria4 = new Kategoria("Kyberpunk");

			kategoriarepository.save(kategoria1);
			kategoriarepository.save(kategoria2);
			kategoriarepository.save(kategoria3);
			kategoriarepository.save(kategoria4);

			log.info("tallennetaan pari peliä");

			SimpleDateFormat fdate = new SimpleDateFormat("dd.MM.yyyy");

			pelirepository.save(new Peli("Helldivers 2", 5, "30", fdate.parse("15.02.2024"), kategoria1));
			pelirepository.save(new Peli("Cyberpunk 2077", 4, "60", fdate.parse("10.10.2022"), kategoria4));
			pelirepository.save(new Peli("The Witcher 3", 4, "15", fdate.parse("15.10.2023"), kategoria3));
			pelirepository.save(new Peli("The Finals", 3, "Ilmainen", fdate.parse("10.02.2024"), kategoria1));

			log.info("Haetaan kaikki pelit");
			for (Peli peli : pelirepository.findAll()) {
				log.info(peli.toString());
			}

			PeliUser user = new PeliUser("admin", "$2a$10$d05yBVyIS6rEYrVQiZFrw.rIzF5ylwR018N6IhusEFZWYPx.s1VxC",
					"ADMIN");

			peliUserRepository.save(user);

		};

	}
}