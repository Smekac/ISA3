package com.example.isa.service;

import com.example.isa.Model.Korisnici.AdminFanModel;
import com.example.isa.repository.AdminFanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Smekac on 2/1/2018.
 */
@Service
public class AdminFanServiceImpl implements AdminFanService {

    @Autowired                                      //Bude null ako ne stavimo ovu anotaciju....
    private AdminFanRepository adminFanRepository;

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
