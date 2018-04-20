package com.example.isa.Model;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rezervacija")
public class Rezervacija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private DatumiProjekcije datumiProjekcije;

    @OneToMany(fetch = FetchType.LAZY )
    private List<Sjediste> sjedista;


    @ManyToOne(optional = false)
    @JsonIgnoreProperties(value = {"username","password", "email", "grad","number", "tipKorisnika", "accepted"})
    private RegPosetilacModel posetilac;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"username","password", "email", "grad","number", "tipKorisnika", "accepted"})
    private List<RegPosetilacModel> pozvani;


    public Rezervacija(){
        super();
        sjedista = new ArrayList<Sjediste>();
        pozvani = new ArrayList<RegPosetilacModel>();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DatumiProjekcije getDatumiProjekcije() {
        return datumiProjekcije;
    }

    public void setDatumiProjekcije(DatumiProjekcije datumiProjekcije) {
        this.datumiProjekcije = datumiProjekcije;
    }

    public List<Sjediste> getSjedista() {
        return sjedista;
    }

    public void setSjedista(List<Sjediste> sjedista) {
        this.sjedista = sjedista;
    }

    public RegPosetilacModel getPosetilac() {
        return posetilac;
    }

    public void setPosetilac(RegPosetilacModel posetilac) {
        this.posetilac = posetilac;
    }

    public List<RegPosetilacModel> getPozvani() {
        return pozvani;
    }

    public void setPozvani(List<RegPosetilacModel> pozvani) {
        this.pozvani = pozvani;
    }
}
