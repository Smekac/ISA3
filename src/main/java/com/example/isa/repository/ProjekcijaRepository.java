package com.example.isa.repository;

import com.example.isa.Model.Projekcija;
import com.example.isa.Model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjekcijaRepository extends JpaRepository<Projekcija, Long> {
    @Override
    List<Projekcija> findAll();

    Projekcija findOne(Long id);

    List<Projekcija> findBySala(Sala sala);
}
