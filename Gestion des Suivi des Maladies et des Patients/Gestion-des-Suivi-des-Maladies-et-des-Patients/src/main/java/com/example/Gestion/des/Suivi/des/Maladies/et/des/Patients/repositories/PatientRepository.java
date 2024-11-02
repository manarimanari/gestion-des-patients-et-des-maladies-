package com.example.Gestion.des.Suivi.des.Maladies.et.des.Patients.repositories;
import com.example.Gestion.des.Suivi.des.Maladies.et.des.Patients.entities.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface PatientRepository extends CrudRepository<Patient, Long> {
    }




