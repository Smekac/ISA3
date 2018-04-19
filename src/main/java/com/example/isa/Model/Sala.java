package com.example.isa.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sala implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String naziv;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ustanova", nullable = false)
    private Ustanova ustanova;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sala")
    private List<Segment> segmenti;


    @ManyToMany(fetch =FetchType.LAZY ,mappedBy = "sale")
    @JsonIgnore
    private List<DatumiProjekcije> datumiprojekcije;

    Sala(){
        super();
        segmenti = new ArrayList<Segment>();
        datumiprojekcije = new ArrayList<DatumiProjekcije>();
    }

    public Sala(String naziv, Ustanova ustanova, List<Segment> segmenti, List<Projekcija> projekcije) {
        this.naziv = naziv;
        this.ustanova = ustanova;
        this.segmenti = segmenti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Ustanova getUstanova() {
        return ustanova;
    }

    public void setUstanova(Ustanova ustanova) {
        this.ustanova = ustanova;
    }

    public List<Segment> getSegmenti() {
        return segmenti;
    }

    public void setSegmenti(List<Segment> segmenti) {
        this.segmenti = segmenti;
    }

    public List<DatumiProjekcije> getDatumiprojekcije() {
        return datumiprojekcije;
    }

    public void setDatumiprojekcije(List<DatumiProjekcije> datumiprojekcije) {
        this.datumiprojekcije = datumiprojekcije;
    }
}
