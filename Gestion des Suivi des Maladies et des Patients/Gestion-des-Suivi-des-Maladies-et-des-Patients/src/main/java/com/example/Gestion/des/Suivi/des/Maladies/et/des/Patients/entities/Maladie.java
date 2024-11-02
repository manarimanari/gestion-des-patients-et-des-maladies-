package com.example.Gestion.des.Suivi.des.Maladies.et.des.Patients.entities;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;

    @Entity
    @Table(name = "maladie")
    public class Maladie {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @NotBlank(message = "Disease name is mandatory")
        private String name;
        @NotBlank(message = "Disease Description  is mandatory")
        private String description;

        @ManyToMany(mappedBy = "maladies", fetch = FetchType.EAGER) // Ceci permet à `Maladie` de connaître les patients liés
        private Set<Patient> patients;

        public Maladie() {
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Set<Patient> getPatients() {
            return patients;
        }

        public void setPatients(Set<Patient> patients) {
            this.patients = patients;
        }
    }




