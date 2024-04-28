package com.example.pelikirjasto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.pelikirjasto.web.PeliController;
import com.example.pelikirjasto.web.KategoriaController;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class PelikirjastoApplicationTests {
	@Autowired
	private PeliController pelicontroller;
	@Autowired
	private KategoriaController kategoriacontroller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(pelicontroller).isNotNull();
		assertThat(kategoriacontroller).isNotNull();
	}

}
