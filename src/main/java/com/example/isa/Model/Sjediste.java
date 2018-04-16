package com.example.isa.Model;

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
    private Segment segment;

    @Column
    private  int red;
    //pozicija u redu
    @Column
    private  int pozicija;


    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean rezervisano;

    Sjediste(){
        super();
    }

    public Sjediste(Segment segment, int red, int pozicija, boolean rezervisano) {
        this.segment = segment;
        this.red = red;
        this.pozicija = pozicija;
        this.rezervisano = rezervisano;
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

    public boolean isRezervisano() {
        return rezervisano;
    }

    public void setRezervisano(boolean rezervisano) {
        this.rezervisano = rezervisano;
    }
}
