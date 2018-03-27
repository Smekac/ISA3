package com.example.isa.repository;

import com.example.isa.Model.Rekviziti.KorisceniRekvizit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */
public interface KorisceniRekvizitRepository extends JpaRepository<KorisceniRekvizit,Long> {

    KorisceniRekvizit findOne(Long id);

    List<KorisceniRekvizit> findAll();

    KorisceniRekvizit save(KorisceniRekvizit korisceniRekvizit);

    void delete(Long id);

}
