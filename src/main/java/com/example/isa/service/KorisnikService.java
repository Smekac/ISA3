package com.example.isa.service;

import com.example.isa.Model.Korisnici.Korisnik;

public interface KorisnikService {

    Korisnik findByEmailAndPassword(String email, String password);
}
