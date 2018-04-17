package com.example.isa.service;

import com.example.isa.Model.Projekcija;
import com.example.isa.Model.Sala;
import com.example.isa.Model.Ustanova;
import com.example.isa.repository.ProjekcijaRepository;
import com.example.isa.repository.SalaRepository;
import com.example.isa.repository.UstanovaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smekac on 4/9/2018.
 */

@Service
public class UstanovaServiceImpl implements UstanovaService {

    @Autowired
    private UstanovaRepository ustanovaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private ProjekcijaRepository projekcijaRepository;

    @Override
    public List<Ustanova> findAll() {
        return ustanovaRepository.findAll();
    }

    @Override
    public Ustanova findOne(Long id) {
        return ustanovaRepository.findOne(id);
    }

    @Override
    public Ustanova save(Ustanova ustanova) {
        return ustanovaRepository.save(ustanova);
    }

    @Override
    public void delete(Long id) {
        ustanovaRepository.delete(id);
    }

    @Override
    public List<Projekcija> findRepertoar(Long idUstanove) {
        List<Projekcija> repertoar = new ArrayList<Projekcija>();

        List<Sala> saleUstanove = salaRepository.findByUstanova(ustanovaRepository.findOne(idUstanove));
        if(saleUstanove != null){
            for( Sala sala : saleUstanove){
               repertoar.addAll(projekcijaRepository.findBySala(sala));
            }
        }

        return repertoar;
    }

}
