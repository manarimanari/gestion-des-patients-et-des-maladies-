package com.example.Gestion.des.Suivi.des.Maladies.et.des.Patients.repositories;
import com.example.Gestion.des.Suivi.des.Maladies.et.des.Patients.entities.Maladie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface MaladieRepository extends CrudRepository<Maladie, Long> {

    }





