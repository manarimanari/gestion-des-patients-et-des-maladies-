package com.example.Gestion.des.Suivi.des.Maladies.et.des.Patients.controllers;

import com.example.Gestion.des.Suivi.des.Maladies.et.des.Patients.entities.Maladie;
import com.example.Gestion.des.Suivi.des.Maladies.et.des.Patients.repositories.MaladieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/maladies")
public class MaladieController {

    @Autowired
    private MaladieRepository maladieRepository;

    // Affiche le formulaire d'ajout de maladie
    @GetMapping("/new")
    public String showAddMaladieForm(Model model) {
        model.addAttribute("maladie", new Maladie());
        return "add-maladie";
    }

    // Ajoute une nouvelle maladie
    @PostMapping("/add")
    public String addMaladie(@Valid @ModelAttribute Maladie maladie, BindingResult result) {
        if (result.hasErrors()) {
            return "add-maladie";
        }
        maladieRepository.save(maladie);
        return "redirect:/maladies/liste";
    }


    // Affiche la liste des maladies avec les patients associés
    @GetMapping("/liste")
    public String listMaladiesAvecPatients(Model model) {
        Iterable<Maladie> maladies = maladieRepository.findAll();
        model.addAttribute("maladies", maladies);
        return "maladie-list"; // Ou "maladies-avec-patients" selon le nom de votre fichier
    }


    // Affiche le formulaire de mise à jour d'une maladie
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Maladie maladie = maladieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Maladie Id:" + id));
        model.addAttribute("maladie", maladie);
        return "update-maladie";
    }

    // Met à jour une maladie
    @PostMapping("/update/{id}")
    public String updateMaladie(@PathVariable("id") long id, @Valid Maladie maladie, BindingResult result) {
        if (result.hasErrors()) {
            maladie.setId(id);
            return "update-maladie";
        }
        maladieRepository.save(maladie);
        return "redirect:/maladies/liste";
    }

    // Supprime une maladie
    @GetMapping("/delete/{id}")
    public String deleteMaladie(@PathVariable("id") long id) {
        Maladie maladie = maladieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Maladie Id:" + id));
        maladieRepository.delete(maladie);
        return "redirect:/maladies/liste";
    }



}
