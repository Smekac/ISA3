package com.example.isa.repository;

import com.example.isa.Model.DatumiProjekcije;
import com.example.isa.Model.Projekcija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatumiProjekcijeRepository extends JpaRepository<DatumiProjekcije,Long> {

    List<DatumiProjekcije> findByProjekcije(Projekcija projekcija);
}
