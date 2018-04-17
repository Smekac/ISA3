package com.example.isa.service;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Prijateljstvo;

public interface PrijateljstvoService {

    Prijateljstvo findByPosiljalac(RegPosetilacModel posiljalac);

    Prijateljstvo findByPrimalac(RegPosetilacModel primalac);

    Prijateljstvo findById(Long id);

    Prijateljstvo save(Prijateljstvo prijateljstvo);

    boolean posaljiZahtjev(Long idPos,Long idPrim);
}
