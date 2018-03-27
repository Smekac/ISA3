package com.example.isa.repository;

import com.example.isa.Model.Rekviziti.NoviRekvizit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */
public interface NoviRekvizitRepository extends JpaRepository<NoviRekvizit,Long> {
    NoviRekvizit findOne(Long id);

    List<NoviRekvizit> findAll();

    NoviRekvizit save(NoviRekvizit noviRekvizit);

    void delete(Long id);
}
