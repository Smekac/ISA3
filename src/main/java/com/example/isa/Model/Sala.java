package com.example.isa.Model;

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
    @JoinColumn(name = "ustanova", nullable = false)
    private Ustanova ustanova;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sala")
    private List<Segment> segmenti;

    @OneToMany(fetch =FetchType.LAZY ,mappedBy = "sala")
    private List<Projekcija> projekcije;

    Sala(){
        super();
        segmenti = new ArrayList<Segment>();
        projekcije = new ArrayList<Projekcija>();
    }

    public Sala(String naziv, Ustanova ustanova, List<Segment> segmenti, List<Projekcija> projekcije) {
        this.naziv = naziv;
        this.ustanova = ustanova;
        this.segmenti = segmenti;
        this.projekcije = projekcije;
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

    public List<Projekcija> getProjekcije() {
        return projekcije;
    }

    public void setProjekcije(List<Projekcija> projekcije) {
        this.projekcije = projekcije;
    }

}
