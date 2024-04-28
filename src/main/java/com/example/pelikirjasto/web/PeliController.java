package com.example.pelikirjasto.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import com.example.pelikirjasto.domain.PeliRepository;

import jakarta.validation.Valid;

import com.example.pelikirjasto.domain.KategoriaRepository;
import com.example.pelikirjasto.domain.Peli;

@Controller
public class PeliController {
    @Autowired
    private PeliRepository pelirepository;
    @Autowired
    private KategoriaRepository kategoriarepository;

    @RequestMapping(value = "/login")
    public String peliLogin() {
        return "login";
    }

    @RequestMapping(value = { "/", "/pelikirjasto" })
    public String peliKirjasto(Model model) {
        model.addAttribute("pelit", pelirepository.findAll());
        return "pelikirjasto";
    }

    // @CrossOrigin(origins = "http://localhost:5173")
    @RequestMapping(value = "/pelit/all", method = RequestMethod.GET)
    public @ResponseBody List<Peli> peliKirjastoRest() {
        return (List<Peli>) pelirepository.findAll();
    }

    @RequestMapping(value = "/pelit/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Peli> findPeliRest(@PathVariable("id") Long peliId) {
        return pelirepository.findById(peliId);
    }

    @RequestMapping(value = "/addpeli")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addPeli(Model model) {
        model.addAttribute("peli", new Peli());
        model.addAttribute("kategoriat", kategoriarepository.findAll());
        return "addpeli";
    }

    @RequestMapping(value = "/savepeli", method = RequestMethod.POST)
    public String savePeli(@Valid Peli peli, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("kategoriat", kategoriarepository.findAll());
            if (peli.getId() != null) {
                return "editpeli";
            }
            return "addpeli";
        } else {
            pelirepository.save(peli);
            return "redirect:pelikirjasto";
        }
    }

    @RequestMapping(value = "/deletepeli/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deletePeli(@PathVariable("id") Long peliId, Model model) {
        pelirepository.deleteById(peliId);
        return "redirect:../pelikirjasto";
    }

    @RequestMapping(value = "/editpeli/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editPeli(@PathVariable("id") Long peliId, Model model) {
        model.addAttribute("peli", pelirepository.findById(peliId));
        model.addAttribute("kategoriat", kategoriarepository.findAll());
        return "editpeli";
    }
}
