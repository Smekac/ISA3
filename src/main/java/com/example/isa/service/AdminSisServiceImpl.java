package com.example.isa.service;

import com.example.isa.Model.Korisnici.AdminSisModel;
import com.example.isa.repository.AdminSisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */
@Service
public class AdminSisServiceImpl  implements  AdminSisService{

    @Autowired
    private AdminSisRepository adminSysRepository;

    @Override
    public List<AdminSisModel> findAll() {
        return adminSysRepository.findAll();
    }

    @Override
    public AdminSisModel findOne(Long id) {
        return adminSysRepository.findOne(id);
    }

    @Override
    public AdminSisModel save(AdminSisModel adminSys) {
        return adminSysRepository.save(adminSys);
    }

    @Override
    public void delete(Long id) {
        adminSysRepository.delete(id);
    }

}
