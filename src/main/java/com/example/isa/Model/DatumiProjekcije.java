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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "projekcija", nullable = false)
    private Projekcija projekcija;

    @ManyToMany
    @JoinTable(name= "sale_datumiprojekcije", joinColumns= @JoinColumn(name="datumprojekcije_id"),
            inverseJoinColumns = @JoinColumn(name ="sale_id" ))
    private List<Sala> sale;

    public DatumiProjekcije(){
        super();
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

    public List<Sala> getSale() {
        return sale;
    }

    public void setSale(List<Sala> sale) {
        this.sale = sale;
    }
}
