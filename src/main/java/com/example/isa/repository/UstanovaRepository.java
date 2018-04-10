package com.example.isa.repository;

import com.example.isa.Model.Ustanova;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Smekac on 4/9/2018.
 */
public interface UstanovaRepository extends JpaRepository<Ustanova,Long> {

    Ustanova findOne(Long id);

    List<Ustanova> findAll();

    Ustanova save(Ustanova ustanova);

    void delete(Long id);
}
