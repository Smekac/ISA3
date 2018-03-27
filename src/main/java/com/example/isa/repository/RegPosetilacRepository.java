package com.example.isa.repository;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */
public interface RegPosetilacRepository extends JpaRepository<RegPosetilacModel,Long> {

    RegPosetilacModel findOne(Long id);

    List<RegPosetilacModel> findAll();

    RegPosetilacModel save(RegPosetilacModel regUser);

    void delete(Long id);
}
