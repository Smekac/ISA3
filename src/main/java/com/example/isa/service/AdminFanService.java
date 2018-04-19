package com.example.isa.service;

import com.example.isa.Model.Korisnici.AdminFanModel;

import java.util.List;

/**
 * Created by Smekac on 2/1/2018.
 */
public interface AdminFanService {

    List<AdminFanModel> findAll();

    AdminFanModel findOne(Long id);

    AdminFanModel save(AdminFanModel adminSys);

    void delete(Long id);
    AdminFanModel findByEmail( String email);

    AdminFanModel findByPassword( String password );


}
