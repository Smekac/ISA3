package com.example.isa.service;

import com.example.isa.Model.Rekviziti.NoviRekvizit;
import com.example.isa.repository.NoviRekvizitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */

@Service
public class NoviRekvizitServiceImpl implements  NoviRekvizitService{

    @Autowired
    private NoviRekvizitRepository noviRekvizitReepozitori;

    @Override
    public List<NoviRekvizit> findAll() {
        return noviRekvizitReepozitori.findAll();
    }

    @Override
    public NoviRekvizit findOne(Long id) {
        return noviRekvizitReepozitori.findOne(id);
    }

    @Override
    public NoviRekvizit save(NoviRekvizit noviRekvizit) {
        return noviRekvizitReepozitori.save(noviRekvizit);
    }

    @Override
    public void delete(Long id) {
        noviRekvizitReepozitori.delete(id);
    }

    @Override
    public List<NoviRekvizit> findByRegistrovaniKorisnikIsNull() {
        return noviRekvizitReepozitori.findByRegistrovaniKorisnikIsNull();
    }


}
