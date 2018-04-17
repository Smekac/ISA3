package com.example.isa.service;

import com.example.isa.Model.DatumiProjekcije;
import com.example.isa.Model.Projekcija;

import java.util.List;

public interface ProjekcijaService {
    Projekcija findOne(Long id);
    List<DatumiProjekcije> findDatumiProjekcije(Long id);
}

