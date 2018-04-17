package com.example.isa.service;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Prijateljstvo;
import com.example.isa.repository.PrijateljstvoRepository;
import com.example.isa.repository.RegPosetilacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrijateljstvoServiceImpl implements PrijateljstvoService {
    @Autowired
    PrijateljstvoRepository prijateljstvoRepository;
    @Autowired
    RegPosetilacRepository regPosetilacRepository;

    @Override
    public Prijateljstvo findByPosiljalac(RegPosetilacModel posiljalac) {
        return prijateljstvoRepository.findByPosiljalac(posiljalac);
    }

    @Override
    public Prijateljstvo findByPrimalac(RegPosetilacModel primalac) {
        return prijateljstvoRepository.findByPrimalac(primalac);
    }

    @Override
    public Prijateljstvo findById(Long id) {
        return prijateljstvoRepository.findById(id);
    }

    @Override
    public Prijateljstvo save(Prijateljstvo prijateljstvo) {
        return prijateljstvoRepository.save(prijateljstvo);
    }

    @Override
    public boolean posaljiZahtjev(Long idPos, Long idPrim) {
        RegPosetilacModel posiljalac = regPosetilacRepository.findOne(idPos);
        RegPosetilacModel primalac = regPosetilacRepository.findOne((idPrim));

        if(posiljalac != null && primalac != null) {
            Prijateljstvo prijateljstvo = new Prijateljstvo(posiljalac, primalac, false);
            prijateljstvoRepository.save(prijateljstvo);
            return true;
        }
        return false;
    }
}
