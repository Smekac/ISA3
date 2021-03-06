package com.example.isa.repository;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Prijateljstvo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrijateljstvoRepository extends JpaRepository<Prijateljstvo,Long> {

    Prijateljstvo findById(Long id);

    Prijateljstvo save(Prijateljstvo prijateljstvo);
}
