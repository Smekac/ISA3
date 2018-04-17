package com.example.isa.repository;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Rekviziti.KorisceniRekvizit;
import com.example.isa.Model.Rekviziti.TipKoriscenogRekvizita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */
public interface KorisceniRekvizitRepository extends JpaRepository<KorisceniRekvizit,Long> {

    KorisceniRekvizit findOne(Long id);

    List<KorisceniRekvizit> findAll();

    KorisceniRekvizit save(KorisceniRekvizit korisceniRekvizit);

    void delete(Long id);

    List<KorisceniRekvizit> findByAdminFanIsNotNull();

    List<KorisceniRekvizit> findByActiveUntilGreaterThanAndStatusEquals(Date date, TipKoriscenogRekvizita korisceniRekvizit);
                            // po imenu 'skrpljen upit xD'
    List<KorisceniRekvizit> findByRegistrovaniKorisnik_Username(String username);

   // findByActiveUntilGreaterThanAndAcceptedBidNullAndStatusNot
    List<KorisceniRekvizit> findByActiveUntilGreaterThanAndAcceptedBidNullAndStatusNot(Date data, TipKoriscenogRekvizita tipKoriscenogRekvizita );

    List<KorisceniRekvizit> findByRegistrovaniKorisnik_UsernameAndActiveUntilGreaterThanAndAcceptedBidNullAndStatusNot(String username, Date data, TipKoriscenogRekvizita tipKoriscenogRekvizita );

}

