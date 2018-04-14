package com.example.isa.Model;

import com.example.isa.Model.Rekviziti.NoviRekvizit;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Smekac on 1/31/2018.
 */

@Entity
@Table(name = "Ustanova")
public class Ustanova {

    public Ustanova(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private TipUstanove type;

    @Column
    private String addres;

    @Column
    private String description;

    @Column
    private double rating;


//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "ustanova")        //Vlasnik ustanova mapira iz Novi Rekvizit
//    private Set<NoviRekvizit> rekvizits;

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

//    public Set<NoviRekvizit> getRekvizits() {
//        return rekvizits;
//    }
//
//    public void setRekvizits(Set<NoviRekvizit> rekvizits) {
//        this.rekvizits = rekvizits;
//    }

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
}
