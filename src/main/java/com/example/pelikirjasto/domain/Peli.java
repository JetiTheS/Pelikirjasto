package com.example.pelikirjasto.domain;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Peli {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Anna joku nimi")
    private String nimi;
    @PositiveOrZero(message = "Numero kiitos")
    private int arvio;
    @NotBlank(message = "Jos ilmainen, kirjaa ilmainen")
    private String hinta;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ostopaiva = new Date();
    private String formatoituostopaiva;

    @ManyToOne
    @JoinColumn(name = "kategoriaid")
    private Kategoria kategoria;

    public Peli() {
    }

    public Peli(String nimi, int arvio, String hinta, Date ostopaiva, Kategoria kategoria) {
        this.nimi = nimi;
        this.arvio = arvio;
        this.hinta = hinta;
        this.ostopaiva = ostopaiva;
        this.kategoria = kategoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getArvio() {
        return arvio;
    }

    public void setArvio(int arvio) {
        this.arvio = arvio;
    }

    public String getHinta() {
        return hinta;
    }

    public void setHinta(String hinta) {
        this.hinta = hinta;
    }

    public Date getOstopaiva() {

        return ostopaiva;
    }

    public void setOstopaiva(Date ostopaiva) {
        this.ostopaiva = ostopaiva;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

    public String getFormatoituostopaiva() {
        SimpleDateFormat fdate = new SimpleDateFormat("dd.MM.yyyy");
        return fdate.format(ostopaiva);
    }

    @Override
    public String toString() {
        if (this.kategoria != null)
            return "Peli [id=" + id + ", nimi=" + nimi + ", arvio=" + arvio + ", hinta=" + hinta + ", ostopaiva="
                    + ostopaiva + ", kategoria=" + this.getKategoria() + "]";
        else
            return "Peli [id=" + id + ", nimi=" + nimi + ", arvio=" + arvio + ", hinta=" + hinta + ", ostopaiva="
                    + ostopaiva + "]";
    }

}
