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
    public Rezervacija save(Rezervacija rez) {
       Rezervacija saved = rezervacijaRepository.save(rez);
       return saved;
    }

    @Override
    public Rezervacija findOne(Long id) {
        return  rezervacijaRepository.findOne(id);
    }
}
