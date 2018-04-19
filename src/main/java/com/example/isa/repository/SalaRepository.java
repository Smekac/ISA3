package com.example.isa.repository;

import com.example.isa.Model.Sala;
import com.example.isa.Model.Segment;
import com.example.isa.Model.Ustanova;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaRepository extends JpaRepository<Sala,Long> {

    List<Sala> findByUstanova(Ustanova ustanova);

    Sala findById(Long id);

}
