package com.example.isa.service;

import com.example.isa.Model.Segment;

import java.util.List;

public interface SalaService  {
        List<Segment> findSegments(Long id);
}
