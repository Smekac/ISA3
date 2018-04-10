package com.example.isa.service;

import com.example.isa.Model.Korisnici.RegPosetilacModel;

import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */
public interface RegPosetilacService {

    List<RegPosetilacModel> findAll();

    RegPosetilacModel findOne(Long id);

    RegPosetilacModel save(RegPosetilacModel regPosetilac);

    void delete(Long id);

    boolean sendEmai(RegPosetilacModel regPosetilac);

    void confirmEmailAdress(Long id);
}
