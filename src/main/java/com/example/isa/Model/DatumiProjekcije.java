package com.example.isa.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class DatumiProjekcije implements Serializable {

    @Id
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datum;

    @Column(nullable = false)
    private Time termin;

    @Column(nullable = false)
    private double cijena;

    @ManyToMany(mappedBy = "datumiProjekcije")
    @JsonIgnore
    private List<Projekcija> projekcije;

    public DatumiProjekcije(){
        super();
        projekcije = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Projekcija> getProjekcije() {
        return projekcije;
    }

    public void setProjekcije(List<Projekcija> projekcije) {
        this.projekcije = projekcije;
    }

    public Date getDatum() {

        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Time getTermin() {
        return termin;
    }

    public void setTermin(Time termin) {
        this.termin = termin;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }
}
