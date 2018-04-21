package com.example.isa.service;

import com.example.isa.Model.Korisnici.AdminFanModel;
import com.example.isa.Model.Rekviziti.KorisceniRekvizit;
import com.example.isa.Model.Rekviziti.TipKoriscenogRekvizita;
import com.example.isa.repository.AdminFanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.transaction.annotation.Propagation;

/**
 * Created by Smekac on 2/1/2018.
 */
@Service
public class AdminFanServiceImpl implements AdminFanService {

    @Autowired                                      //Bude null ako ne stavimo ovu anotaciju....
    private AdminFanRepository adminFanRepository;

    @Autowired
    private KorisceniRekvizitService korisceniRekvizitService;

    @Override
    public List<AdminFanModel> findAll() {
        return adminFanRepository.findAll();
    }

    @Override
    public AdminFanModel findOne(Long id) {
        return adminFanRepository.findOne(id);
    }

    @Override
    public AdminFanModel save(AdminFanModel admin) {
        return adminFanRepository.save(admin);
    }

    @Override
    public void delete(Long id) {
        adminFanRepository.delete(id);
    }

}
