package com.example.isa.service;

import com.example.isa.Model.Korisnici.AdminSisModel;

import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */
public interface AdminSisService {

    List<AdminSisModel> findAll();

    AdminSisModel findOne(Long id);

    AdminSisModel save(AdminSisModel adminSys);

    void delete(Long id);

}

