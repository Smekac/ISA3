package com.example.isa.service;

import com.example.isa.Model.Ustanova;
import com.example.isa.repository.UstanovaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Smekac on 4/9/2018.
 */

@Service
public class UstanovaServiceImpl implements UstanovaService {

    @Autowired
    private UstanovaRepository ustanovaRepository;

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

}
