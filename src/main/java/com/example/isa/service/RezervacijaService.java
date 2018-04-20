package com.example.isa.service;

import com.example.isa.Model.Rezervacija;

public interface RezervacijaService {
    Rezervacija save(Rezervacija rez);

    Rezervacija findOne(Long id);

    void otkaziPoziv(Long idRezervacije,Long idKorisnika);

    void prihvatiPoziv(Long idRezervacije,Long idKorisnika);

    boolean otkaziRezervaciju(Long id);

}
