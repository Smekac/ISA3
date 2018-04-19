package com.example.isa.service;

import com.example.isa.Model.Korisnici.Korisnik;

import java.util.List;

public interface KorisnikService {

    Korisnik findByEmailAndPassword(String email, String password);

    Korisnik findByUsername(String username);

    List<Korisnik> findAll();


}
