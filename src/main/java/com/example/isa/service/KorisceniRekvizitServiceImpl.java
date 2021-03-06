package com.example.isa.service;

import com.example.isa.Model.Bid;
import com.example.isa.Model.Korisnici.AdminFanModel;
import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Rekviziti.KorisceniRekvizit;
import com.example.isa.Model.Rekviziti.TipKoriscenogRekvizita;
import com.example.isa.repository.KorisceniRekvizitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Smekac on 1/31/2018.
 */

@Service
//@Transactional(readOnly = true)
public class KorisceniRekvizitServiceImpl implements KorisceniRekvizitService {

    @Autowired
    private KorisceniRekvizitRepository korisceniRekvizitRepository;

    @Autowired
    private KorisnikService korisnikService;

    @Override
    public List<KorisceniRekvizit> findAll() {
        return korisceniRekvizitRepository.findAll();
    }

    @Override
    public KorisceniRekvizit findOne(Long id) {
        return korisceniRekvizitRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public KorisceniRekvizit save(KorisceniRekvizit korisceniRekvizit) {
        return korisceniRekvizitRepository.save(korisceniRekvizit);
    }

    @Override
    public void delete(Long id) {
        korisceniRekvizitRepository.delete(id);
    }

    @Override
    public List<KorisceniRekvizit> findByAdminFanIsNotNull() {
        return korisceniRekvizitRepository.findByAdminFanIsNotNull();
    }

    @Override
    public List<KorisceniRekvizit> findByActiveUntilGreaterThanAndStatusEquals(Date date, TipKoriscenogRekvizita tipKoriscenogRekvizita) {
        return korisceniRekvizitRepository.findByActiveUntilGreaterThanAndStatusEquals(date,tipKoriscenogRekvizita);
    }

    @Override
    public List<KorisceniRekvizit> findByRegistrovaniKorisnik_Username(String username) {
        return korisceniRekvizitRepository.findByRegistrovaniKorisnik_Username(username);
    }

//    @Override
//    public List<KorisceniRekvizit> findByRegistrovaniKorisnik() {
//        return korisceniRekvizitRepository.findByRegistrovaniKorisnik();
//    }

    @Override
    public KorisceniRekvizit createUsedProp(String username, KorisceniRekvizit usedProp) {
        usedProp.setStatus(TipKoriscenogRekvizita.NACEKANJU);

        // usedProp.setRegistrovaniKorisnik(korisnikService.findByUsername(username));
        usedProp.setDatumKreiranja(new java.util.Date());
        usedProp.setBids( new ArrayList<Bid>());
        return save(usedProp);
    }

    @Override
    public List<KorisceniRekvizit> findByActiveUntilGreaterThanAndAcceptedBidNullAndStatusNot(Date data, TipKoriscenogRekvizita tipKoriscenogRekvizita) {
        return korisceniRekvizitRepository.findByActiveUntilGreaterThanAndAcceptedBidNullAndStatusNot(data,tipKoriscenogRekvizita);
    }

    @Override
    public List<KorisceniRekvizit> findByRegistrovaniKorisnik_UsernameAndActiveUntilGreaterThanAndAcceptedBidNullAndStatusNot(String username, Date data, TipKoriscenogRekvizita tipKoriscenogRekvizita) {
        return korisceniRekvizitRepository.findByRegistrovaniKorisnik_UsernameAndActiveUntilGreaterThanAndAcceptedBidNullAndStatusNot(username,data,tipKoriscenogRekvizita);
    }

    @Override
    public List<KorisceniRekvizit> findByActiveUntilGreaterThanAndAcceptedBidNullAndStatusEquals(Date data, TipKoriscenogRekvizita tipKoriscenogRekvizita) {
        return korisceniRekvizitRepository.findByActiveUntilGreaterThanAndAcceptedBidNullAndStatusEquals(data,tipKoriscenogRekvizita);
    }

    @Override
    public KorisceniRekvizit potvrdi(KorisceniRekvizit korisceniRekvizit, AdminFanModel adminFan) {
        if (!(korisceniRekvizit.getStatus().equals(TipKoriscenogRekvizita.NACEKANJU ))  ) {
            return null;
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        korisceniRekvizit.setAdminFan(adminFan);
        korisceniRekvizit.setStatus (TipKoriscenogRekvizita.PRIHVACEN);
        KorisceniRekvizit savedUsedProp = save(korisceniRekvizit);
        return savedUsedProp;
    }

    @Override
    public KorisceniRekvizit odbij(KorisceniRekvizit korisceniRekvizit, AdminFanModel adminFan) {
        if (!(korisceniRekvizit.getStatus().equals(TipKoriscenogRekvizita.NACEKANJU ))  ) {
            return null;
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        korisceniRekvizit.setAdminFan(adminFan);
        korisceniRekvizit.setStatus (TipKoriscenogRekvizita.ODBIJEN);

        KorisceniRekvizit savedUsedProp = save(korisceniRekvizit);
        return savedUsedProp;
    }

//    @Override
//    public List<KorisceniRekvizit> findByRegistrovaniKorisnikAndActiveUntilGreaterThanAndAcceptedBidNullAndStatusNot(RegPosetilacModel regPosetilacModel, Date data, TipKoriscenogRekvizita tipKoriscenogRekvizita) {
//        return korisceniRekvizitRepository.findByRegistrovaniKorisnikAndActiveUntilGreaterThanAndAcceptedBidNullAndStatusNot(regPosetilacModel,data,tipKoriscenogRekvizita);
//    }

//    @Override
//    public KorisceniRekvizit createUsedProp(String username, KorisceniRekvizit usedProp) {
//        usedProp.setStatus(TipKoriscenogRekvizita.NACEKANJU);
//        usedProp.setRegistrovaniKorisnik(korisnikService.findByUsername(username));
//        usedProp.setDatumKreiranja(new java.util.Date());
//        usedProp.setBids( new ArrayList<Bid>());
//        return save(usedProp);
//    }


}
