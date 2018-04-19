package com.example.isa.service;

import com.example.isa.Model.Sala;
import com.example.isa.Model.Segment;
import com.example.isa.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SalaServiceImpl implements SalaService {

    @Autowired
    SalaRepository salaRepository;
    @Override
    public List<Segment> findSegments(Long id) {
        Sala sala =  salaRepository.findById(id);
        return  sala.getSegmenti();
    }
}
