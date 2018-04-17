package com.example.isa.service;

import com.example.isa.Model.Rekviziti.NoviRekvizit;

import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */
public interface NoviRekvizitService {

    List<NoviRekvizit> findAll();

    NoviRekvizit findOne(Long id);

    NoviRekvizit save(NoviRekvizit noviRekvizit);

    void delete(Long id);

    List<NoviRekvizit> findByRegistrovaniKorisnikIsNull();

   // NoviRekvizit findByRegistrovani

}
