package com.example.isa.repository;

import com.example.isa.Model.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
    Rezervacija save(Rezervacija rezervacija);

    Rezervacija findOne(Long aLong);
}
