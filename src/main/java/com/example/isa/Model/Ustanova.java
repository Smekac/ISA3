package com.example.isa.Model;

import com.example.isa.Model.Rekviziti.NoviRekvizit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Smekac on 1/31/2018.
 */

@Entity
@Table(name = "Ustanova")
public class Ustanova {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private TipUstanove type;

    @Column(unique = true)
    private String addres;

    @Column
    private String description;

    @Column
    private double rating;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ustanova")
    private List<Sala> sale;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ustanova")
    private List<Projekcija> projekcije;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TipUstanove getType() {
        return type;
    }

    public void setType(TipUstanove type) {
        this.type = type;
    }

    public Ustanova(){
        super();
        sale =new  ArrayList<Sala>();
        projekcije = new ArrayList<>();
    }

    public List<Projekcija> getProjekcije() {
        return projekcije;
    }

    public void setProjekcije(List<Projekcija> projekcije) {
        this.projekcije = projekcije;
    }

    public Ustanova(String name, TipUstanove type, String addres, String description, double rating, List<Sala> sale) {
        this.name = name;
        this.type = type;
        this.addres = addres;
        this.description = description;
        this.rating = rating;
        this.sale = sale;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Sala> getSale() {
        return sale;
    }

    public void setSale(List<Sala> sale) {
        this.sale = sale;
    }
}
