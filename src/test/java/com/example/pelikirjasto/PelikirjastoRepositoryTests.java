package com.example.pelikirjasto;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import com.example.pelikirjasto.domain.*;

@SpringBootTest(classes = PelikirjastoApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class PelikirjastoRepositoryTests {
    @Autowired
    private PeliRepository peliRepository;

    @Autowired
    private KategoriaRepository kategoriaRepository;

    @Test
    public void findByTitleShouldReturnPeli() {
        List<Peli> pelit = peliRepository.findByNimi("Helldivers 2");
        assertThat(pelit).hasSize(1);
        assertThat(pelit.get(0).getArvio()).isEqualTo(5);
    }

    @Test
    public void createNewPeli() {
        try {

            SimpleDateFormat fdate = new SimpleDateFormat("dd.MM.yyyy");
            Kategoria category = new Kategoria("Tasohyppely");
            kategoriaRepository.save(category);
            Peli peli = new Peli("Mario", 3, "53", fdate.parse("10.10.2023"), category);
            peliRepository.save(peli);
            assertThat(peli.getId()).isNotNull();
        } catch (Exception e) {

        }

    }

    @Test
    public void deleteNewPeli() {
        List<Peli> pelit = peliRepository.findByNimi("Mario");
        Peli peli = pelit.get(0);
        peliRepository.delete(peli);
        List<Peli> newPelit = peliRepository.findByNimi("Mario");
        assertThat(newPelit).hasSize(0);
    }
}
