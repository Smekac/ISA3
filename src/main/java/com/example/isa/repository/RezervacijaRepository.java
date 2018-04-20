package com.example.isa.repository;

import com.example.isa.Model.DatumiProjekcije;
import com.example.isa.Model.Rezervacija;
import com.example.isa.Model.Sjediste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
    Rezervacija save(Rezervacija rezervacija);

    Rezervacija findOne(Long aLong);

    @Override
    void delete(Long aLong);

    Rezervacija findByDatumiProjekcijeAndSjedista(DatumiProjekcije datumiProjekcije, Sjediste sjediste);
}
