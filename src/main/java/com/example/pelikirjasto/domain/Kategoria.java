package com.example.pelikirjasto.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;

@Entity
public class Kategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long kategoriaid;
    @NotBlank(message = "Kyllä sillä joku nimi on")
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kategoria")
    private List<Peli> pelit;

    public Kategoria() {
    }

    public Kategoria(String name) {
        super();
        this.name = name;

    }

    public Long getKategoriaid() {
        return kategoriaid;
    }

    public void setKategoriaid(Long kategoriaid) {
        this.kategoriaid = kategoriaid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Peli> getPelit() {
        return pelit;
    }

    public void setPelit(List<Peli> pelit) {
        this.pelit = pelit;
    }

    @Override
    public String toString() {
        return "Category [categoryid=" + kategoriaid + ", name=" + name + "]";
    }

}
