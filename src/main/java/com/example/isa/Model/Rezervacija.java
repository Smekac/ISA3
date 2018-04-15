package com.example.isa.Model;

import com.example.isa.Model.Korisnici.RegPosetilacModel;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rezervacija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Projekcija projekcija;

    @OneToMany(fetch = FetchType.LAZY )
    private List<Sjediste> sjedista;


    private RegPosetilacModel posetilac;

    @OneToMany(fetch = FetchType.LAZY)
    private List<RegPosetilacModel> pozvani;


    public Rezervacija(){
        super();
        sjedista = new ArrayList<Sjediste>();
        pozvani = new ArrayList<RegPosetilacModel>();
    }

    public Rezervacija(Projekcija projekcija, List<Sjediste> sjedista,
                       RegPosetilacModel posetilac, List<RegPosetilacModel> pozvani) {
        this.projekcija = projekcija;
        this.sjedista = sjedista;
        this.posetilac = posetilac;
        this.pozvani = pozvani;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Projekcija getProjekcija() {
        return projekcija;
    }

    public void setProjekcija(Projekcija projekcija) {
        this.projekcija = projekcija;
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
