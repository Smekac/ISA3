package com.example.isa.service;

import com.example.isa.Model.Rezervacija;
import com.example.isa.repository.RezervacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RezervacijaServiceImpl implements RezervacijaService{

    @Autowired
    RezervacijaRepository rezervacijaRepository;

    @Override
    public Rezervacija kreirajRezervaciju(Rezervacija rezervacija) {
       Rezervacija saved = rezervacijaRepository.save(rezervacija);
       return saved;
    }
}
