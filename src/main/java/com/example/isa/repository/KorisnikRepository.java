package com.example.isa.repository;

import com.example.isa.Model.Korisnici.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KorisnikRepository extends JpaRepository<Korisnik,Long> {

 List<Korisnik> findAll();
 Korisnik findByEmailAndPassword(String email, String password);
}
