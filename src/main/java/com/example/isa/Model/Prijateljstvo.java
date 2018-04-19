package com.example.isa.Model;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Prijateljstvo implements Serializable {

    @Id
    @GeneratedValue( strategy =GenerationType.AUTO)
    private Long id;


    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="korisnik", referencedColumnName="id" )
    private RegPosetilacModel korisnik;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="prijatelj", referencedColumnName="id")
    private RegPosetilacModel prijatelj;

    @Column(nullable = false)
    private String status;

    public Prijateljstvo(){
        super();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegPosetilacModel getKorisnik() {
        return korisnik;
    }

    public Prijateljstvo(RegPosetilacModel korisnik, RegPosetilacModel prijatelj, String status) {
        this.korisnik = korisnik;
        this.prijatelj = prijatelj;
        this.status = status;
    }

    public void setKorisnik(RegPosetilacModel korisnik) {
        this.korisnik = korisnik;
    }

    public RegPosetilacModel getPrijatelj() {
        return prijatelj;
    }

    public void setPrijatelj(RegPosetilacModel prijatelj) {
        this.prijatelj = prijatelj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
