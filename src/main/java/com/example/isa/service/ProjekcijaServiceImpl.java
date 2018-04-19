package com.example.isa.service;

import com.example.isa.Model.DatumiProjekcije;
import com.example.isa.Model.Projekcija;
import com.example.isa.repository.DatumiProjekcijeRepository;
import com.example.isa.repository.ProjekcijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjekcijaServiceImpl implements ProjekcijaService {
    @Autowired
    ProjekcijaRepository projekcijaRepository;
    @Autowired
    DatumiProjekcijeRepository datumiProjekcijeRepository;

    @Override
    public Projekcija findOne(Long id) {
        return projekcijaRepository.findOne(id);
    }

    @Override
    public List<DatumiProjekcije> findDatumiProjekcije(Long id) {
        Projekcija projekcija =  projekcijaRepository.findOne(id);
        List<DatumiProjekcije> datumi =datumiProjekcijeRepository.findByProjekcija(projekcija);
        return datumi;
    }
}
