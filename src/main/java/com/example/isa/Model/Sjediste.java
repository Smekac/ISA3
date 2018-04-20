package com.example.isa.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.naming.directory.SearchResult;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Sjediste implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "segment")
    @JsonIgnore
    private Segment segment;

    @Column(nullable = false)
    private  int red;
    //pozicija u redu
    @Column(nullable = false)
    private  int pozicija;


    Sjediste(){
        super();
    }

    public Sjediste(Segment segment, int red, int pozicija) {
        this.segment = segment;
        this.red = red;
        this.pozicija = pozicija;

    }



    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getPozicija() {
        return pozicija;
    }

    public void setPozicija(int pozicija) {
        this.pozicija = pozicija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

}
