package com.example.isa.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Segment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private TipSegmenta tipSegmenta;


    @OneToMany(mappedBy = "segment")
    private List<Sjediste> sjedista;


    @ManyToOne
    @JoinColumn(name = "sala")
    private Sala sala;

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }



    Segment(){
        super();
        sjedista = new ArrayList<Sjediste>();
    }

    public Segment(TipSegmenta tipSegmenta, List<Sjediste> sjedista, Sala sala) {
        this.tipSegmenta = tipSegmenta;
        this.sjedista = sjedista;
        this.sala = sala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipSegmenta getTipSegmenta() {
        return tipSegmenta;
    }

    public void setTipSegmenta(TipSegmenta tipSegmenta) {
        this.tipSegmenta = tipSegmenta;
    }



    public List<Sjediste> getSjedista() {
        return sjedista;
    }

    public void setSjedista(List<Sjediste> sjedista) {
        this.sjedista = sjedista;
    }
}
