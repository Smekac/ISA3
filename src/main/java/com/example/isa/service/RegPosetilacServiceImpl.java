package com.example.isa.service;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.repository.RegPosetilacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */

@Service
public class RegPosetilacServiceImpl implements RegPosetilacService {

    /**
     *
     */
    @Autowired
    private RegPosetilacRepository regPosetilacRepository;

    @Override
    public List<RegPosetilacModel> findAll() {
        return regPosetilacRepository.findAll();                                //Treba menjatii ne treba da vraca null !!!!!!!!!!!!!!!!!!!!!!
    }

    @Override
    public RegPosetilacModel findOne(Long id) {
        return regPosetilacRepository.findOne(id);
    }

    @Override
    public RegPosetilacModel save(RegPosetilacModel regPosetilac) {
        return regPosetilacRepository.save(regPosetilac);                   //Rucnoooo
    }

    @Override
    public void delete(Long id) {
        regPosetilacRepository.delete(id);
    }
}
