package com.example.isa.service;

import com.example.isa.Model.Ustanova;

import java.util.List;

/**
 * Created by Smekac on 4/9/2018.
 */
public interface UstanovaService {

    List<Ustanova> findAll();

    Ustanova findOne(Long id);

    Ustanova save(Ustanova ustanova);

    void delete(Long id);
}
