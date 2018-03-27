package com.example.isa.service;

import com.example.isa.Model.Rekviziti.KorisceniRekvizit;

import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */
public interface KorisceniRekvizitService {

    List<KorisceniRekvizit> findAll();

    KorisceniRekvizit findOne(Long id);

    KorisceniRekvizit save(KorisceniRekvizit propUsed);

    void delete(Long id);

}
