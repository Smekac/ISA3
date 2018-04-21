package com.example.isa.service;

import com.example.isa.Model.Korisnici.AdminFanModel;
import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Rekviziti.KorisceniRekvizit;
import com.example.isa.Model.Rekviziti.TipKoriscenogRekvizita;

import java.util.Date;
import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */
public interface KorisceniRekvizitService {

    List<KorisceniRekvizit> findAll();

    KorisceniRekvizit findOne(Long id);

    KorisceniRekvizit save(KorisceniRekvizit korisceniRekvizit);

    void delete(Long id);

    List<KorisceniRekvizit> findByAdminFanIsNotNull();

    List<KorisceniRekvizit> findByActiveUntilGreaterThanAndStatusEquals(java.util.Date date, TipKoriscenogRekvizita tipKoriscenogRekvizita);

    List<KorisceniRekvizit> findByRegistrovaniKorisnik_Username(String username);

    KorisceniRekvizit createUsedProp(String username, KorisceniRekvizit usedProp);

    List<KorisceniRekvizit> findByActiveUntilGreaterThanAndAcceptedBidNullAndStatusNot(Date data, TipKoriscenogRekvizita tipKoriscenogRekvizita );

   // List<KorisceniRekvizit> findByRegistrovaniKorisnik_AndActiveUntilGreaterThanAndAcceptedBidNullAndStatusNot(RegPosetilacModel regPosetilacModel, Date data, TipKoriscenogRekvizita tipKoriscenogRekvizita );
   List<KorisceniRekvizit> findByRegistrovaniKorisnik_UsernameAndActiveUntilGreaterThanAndAcceptedBidNullAndStatusNot(String username, Date data, TipKoriscenogRekvizita tipKoriscenogRekvizita );


    List<KorisceniRekvizit> findByActiveUntilGreaterThanAndAcceptedBidNullAndStatusEquals(Date data, TipKoriscenogRekvizita tipKoriscenogRekvizita );

    KorisceniRekvizit potvrdi(KorisceniRekvizit korisceniRekvizit, AdminFanModel adminFan);

    KorisceniRekvizit odbij(KorisceniRekvizit korisceniRekvizit, AdminFanModel adminFan);

}
