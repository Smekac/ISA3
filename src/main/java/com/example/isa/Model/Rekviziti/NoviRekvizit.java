package com.example.isa.Model.Rekviziti;

import com.example.isa.Model.Korisnici.AdminFanModel;
import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Ustanova;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Smekac on 1/31/2018.
 */

@Entity
public class NoviRekvizit extends Rekvizit implements Serializable{


    @Column(nullable = false)
    private float cena;

    @ManyToOne(optional = false)
    private AdminFanModel adminFan;

    @ManyToOne(optional = true)
    private RegPosetilacModel registrovaniKorisnik;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Ustanova ustanova;

    public NoviRekvizit(){}

    public NoviRekvizit(float cena, AdminFanModel adminFan, RegPosetilacModel registrovaniKorisnik, Ustanova ustanova) {
        this.cena = cena;
        this.adminFan = adminFan;
        this.registrovaniKorisnik = registrovaniKorisnik;
        this.ustanova = ustanova;
    }

    public NoviRekvizit(String naslov, String opis, Date datumKreiranja, float cena, AdminFanModel adminFan, RegPosetilacModel registrovaniKorisnik, Ustanova ustanova) {
        super(naslov, opis, datumKreiranja);
        this.cena = cena;
        this.adminFan = adminFan;
        this.registrovaniKorisnik = registrovaniKorisnik;
        this.ustanova = ustanova;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public AdminFanModel getAdminFan() {
        return adminFan;
    }

    public void setAdminFan(AdminFanModel adminFan) {
        this.adminFan = adminFan;
    }

    public RegPosetilacModel getRegistrovaniKorisnik() {
        return registrovaniKorisnik;
    }

    public void setRegistrovaniKorisnik(RegPosetilacModel registrovaniKorisnik) {
        this.registrovaniKorisnik = registrovaniKorisnik;
    }

    public Ustanova getUstanova() {
        return ustanova;
    }

    public void setUstanova(Ustanova ustanova) {
        this.ustanova = ustanova;
    }
}