package com.example.isa.service;

import com.example.isa.Model.Korisnici.Korisnik;
import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisnikServiceImpl implements KorisnikService {

    @Autowired
    KorisnikRepository korisnikRepository;

    @Override
    public Korisnik findByEmailAndPassword(String email, String password) {

        return korisnikRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public Korisnik findByUsername(String username) {
        return korisnikRepository.findByUsername(username);
    }

    @Override
    public List<Korisnik> findAll() {
        return korisnikRepository.findAll();
    }

    @Override
    public Korisnik save(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }


}
