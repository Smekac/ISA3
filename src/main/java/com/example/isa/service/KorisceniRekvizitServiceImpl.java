package com.example.isa.service;

import com.example.isa.Model.Rekviziti.KorisceniRekvizit;
import com.example.isa.repository.KorisceniRekvizitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */

@Service
public class KorisceniRekvizitServiceImpl implements KorisceniRekvizitService {

    @Autowired
    private KorisceniRekvizitRepository korisceniRekvizitRepository;

    @Override
    public List<KorisceniRekvizit> findAll() {
        return korisceniRekvizitRepository.findAll();
    }

    @Override
    public KorisceniRekvizit findOne(Long id) {
        return korisceniRekvizitRepository.findOne(id);
    }

    @Override
    public KorisceniRekvizit save(KorisceniRekvizit korisceniRekvizit) {
        return korisceniRekvizitRepository.save(korisceniRekvizit);
    }

    @Override
    public void delete(Long id) {
        korisceniRekvizitRepository.delete(id);
    }

}
