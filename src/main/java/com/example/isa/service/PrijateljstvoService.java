package com.example.isa.service;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Prijateljstvo;

public interface PrijateljstvoService {

    Prijateljstvo findById(Long id);

    Prijateljstvo save(Prijateljstvo prijateljstvo);

    boolean posaljiZahtjev(Long id);

    void obrisi(Long id);

    void prihvati(Long id);
}
