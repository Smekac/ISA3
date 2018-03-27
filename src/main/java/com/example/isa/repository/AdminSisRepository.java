package com.example.isa.repository;

import com.example.isa.Model.Korisnici.AdminSisModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */
public interface AdminSisRepository extends JpaRepository<AdminSisModel,Long> {

    AdminSisModel findOne(Long id);

    List<AdminSisModel> findAll();

    AdminSisModel save(AdminSisModel admin);

    void delete(Long id);

}
