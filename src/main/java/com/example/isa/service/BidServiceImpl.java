package com.example.isa.service;


import com.example.isa.Model.Bid;
import com.example.isa.Model.Korisnici.Korisnik;
import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Rekviziti.KorisceniRekvizit;
import com.example.isa.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Smekac. on 28-Jan-16
 */
@Service
public class BidServiceImpl implements BidService {
    @Autowired
    private BidRepository bidRepository;


    @Override
    public List<Bid> findAll() {
        return bidRepository.findAll();
    }

    @Override
    public Bid findOne(Long id) {
        return bidRepository.findOne(id);
    }

    @Override
    public Bid save(Bid bid) {
        return bidRepository.save(bid);
    }

    @Override
    public void delete(Long id) {
        bidRepository.delete(id);
    }

    @Override
    public Bid findByRegistrovaniKorisnikAndKorisceniRekvizit(RegPosetilacModel regPosetilacModel, KorisceniRekvizit korisceniRekvizit) {
        return bidRepository.findByRegistrovaniKorisnikAndKorisceniRekvizit(regPosetilacModel,korisceniRekvizit);
    }

    @Override
    public List<Bid> findByKorisceniRekvizit(KorisceniRekvizit korisceniRekvizit) {
        return bidRepository.findByKorisceniRekvizit(korisceniRekvizit);
    }

}
