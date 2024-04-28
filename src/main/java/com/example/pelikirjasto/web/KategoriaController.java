package com.example.pelikirjasto.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.pelikirjasto.domain.Kategoria;
import com.example.pelikirjasto.domain.KategoriaRepository;

import jakarta.validation.Valid;

@Controller
public class KategoriaController {
    @Autowired
    private KategoriaRepository kategoriaRepository;

    @RequestMapping(value = { "/kategorialista" })
    public String kategoriaLista(Model model) {
        model.addAttribute("kategoriat", kategoriaRepository.findAll());
        return "kategorialista";
    }

    @RequestMapping(value = "/addkategoria")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addKategoria(Model model) {
        model.addAttribute("kategoria", new Kategoria());
        return "addkategoria";
    }

    @RequestMapping(value = "/savekategoria", method = RequestMethod.POST)
    public String saveKategoria(@Valid Kategoria kategoria, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addkategoria";
        }
        kategoriaRepository.save(kategoria);
        return "redirect:kategorialista";
    }

    @RequestMapping(value = "/deletekategoria/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteKategoria(@PathVariable("id") Long kategoriaid, Model model) {
        kategoriaRepository.deleteById(kategoriaid);
        return "redirect:../kategorialista";
    }

    @RequestMapping(value = "/editkategoria/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editKategoria(@PathVariable("id") Long kategoriaid, Model model) {
        model.addAttribute("kategoria", kategoriaRepository.findById(kategoriaid));
        return "editkategoria";
    }
}
