package com.example.Gestion.des.Suivi.des.Maladies.et.des.Patients.controllers;

import com.example.Gestion.des.Suivi.des.Maladies.et.des.Patients.entities.Patient;
import com.example.Gestion.des.Suivi.des.Maladies.et.des.Patients.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/patients") // Toutes les routes liées aux patients
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    // Affiche le formulaire d'ajout d'un nouveau patient
    @GetMapping("/new")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "add-patient";
    }

    // Ajoute un nouveau patient
    @PostMapping("/add")
    public String addPatient(@Valid @ModelAttribute Patient patient, BindingResult result) {
        if (result.hasErrors()) {
            return "add-patient";
        }
        patientRepository.save(patient);
        return "redirect:/patients/liste";
    }


    // Affiche la liste des patients
    @GetMapping("/liste")
    public String listPatients(Model model) {
        Iterable<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        return "patient-list";
    }

    // Affiche les détails d'un patient avec ses maladies associées
    @GetMapping("/{id}/maladies")
    public String showPatientDetails(@PathVariable("id") long id, Model model) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Patient Id: " + id));
        model.addAttribute("patient", patient);
        model.addAttribute("maladies", patient.getMaladies()); // Relation patient -> maladies
        return "detail-patient";
    }

    // Affiche le formulaire de mise à jour d'un patient
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Patient Id:" + id));
        model.addAttribute("patient", patient);
        return "update-patient";
    }

    // Met à jour un patient
    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable("id") long id, @Valid Patient patient, BindingResult result) {
        if (result.hasErrors()) {
            patient.setId(id);
            return "update-patient";
        }
        patientRepository.save(patient);
        return "redirect:/patients/liste";
    }

    // Supprime un patient
    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable("id") long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Patient Id:" + id));
        patientRepository.delete(patient);
        return "redirect:/patients/liste";
    }
}
