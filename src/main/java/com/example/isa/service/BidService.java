package com.example.isa.service;

import com.example.isa.Model.Bid;
import com.example.isa.Model.Korisnici.Korisnik;
import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Rekviziti.KorisceniRekvizit;

import java.util.List;

/**
 * Created by Smekac. on 29-Jan-18
 */
public interface BidService {
    List<Bid> findAll();

    Bid findOne(Long id);

    Bid save(Bid bid);

    void delete(Long id);

    Bid findByRegistrovaniKorisnikAndKorisceniRekvizit(RegPosetilacModel korisnik, KorisceniRekvizit korisceniRekvizit);

    List<Bid> findByKorisceniRekvizit(KorisceniRekvizit korisceniRekvizit);
}
