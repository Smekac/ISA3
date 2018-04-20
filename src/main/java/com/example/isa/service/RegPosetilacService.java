package com.example.isa.service;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Prijateljstvo;

import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */
public interface RegPosetilacService {

    List<RegPosetilacModel> findAll();

    RegPosetilacModel findOne(Long id);

    RegPosetilacModel save(RegPosetilacModel regPosetilac);

    void delete(Long id);

    void confirmEmailAdress(Long id);

    RegPosetilacModel findByUsername(String username);

    List<Prijateljstvo> findPrijatelje();

    RegPosetilacModel update(RegPosetilacModel regPosetilacModel);
}
