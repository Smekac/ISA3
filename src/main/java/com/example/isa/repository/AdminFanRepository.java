package com.example.isa.repository;

import com.example.isa.Model.Korisnici.AdminFanModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */
public interface AdminFanRepository extends JpaRepository<AdminFanModel, Long>{

    AdminFanModel findOne(Long id);

    List<AdminFanModel> findAll();

    AdminFanModel save(AdminFanModel admin);

    void delete(Long id);

}
