package com.example.Gestion.des.Suivi.des.Maladies.et.des.Patients.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;


    @Entity
    @Table(name = "patient")
    public class Patient {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @NotBlank(message = "Name is mandatory")
        private String name;
        @NotBlank(message = "Age is mandatory")
        private int age;

        @NotBlank(message = "Medical history is mandatory")
        private String antecedentsmedicaux;
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "patient_maladie", // Nom de la table de jointure
                joinColumns = @JoinColumn(name = "patient_id"), // Colonne de jointure côté `Patient`
                inverseJoinColumns = @JoinColumn(name = "maladie_id") // Colonne de jointure côté `Maladie`
        )
        private Set<Maladie> maladies;

        public Patient() {
            super();
        }
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public  String getName() {
            return name;
        }

        public void setName( String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public  String getAntecedentsmedicaux() {
            return antecedentsmedicaux;
        }

        public void setAntecedentsmedicaux( String antecedentsmedicaux) {
            this.antecedentsmedicaux = antecedentsmedicaux;
        }
        public Set<Maladie> getMaladies() {
            return maladies;
        }

        public void setMaladies(Set<Maladie> maladies) {
            this.maladies = maladies;
        }
    }



