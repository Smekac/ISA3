package com.example.isa.repository;


import com.example.isa.Model.Bid;
import com.example.isa.Model.Korisnici.Korisnik;
import com.example.isa.Model.Rekviziti.KorisceniRekvizit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Smekac
 */
public interface BidRepository extends JpaRepository<Bid,Long> {
    List<Bid> findAll();

    Bid findOne(Long id);

    Bid save(Bid bid);

    void delete(Long id);

    Bid findByRegistrovaniKorisnikAndKorisceniRekvizit(Korisnik korisnik,KorisceniRekvizit korisceniRekvizit);

    List<Bid> findByKorisceniRekvizit(KorisceniRekvizit korisceniRekvizit);



}
