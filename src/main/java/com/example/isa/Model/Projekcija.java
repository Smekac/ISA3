package com.example.isa.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Smekac on 4/14/2018.
 */

@Entity
@Table(name = "Projekcija")
public class Projekcija {
    // Atributi moraju malim slovima po konvenciji Hibernejta crkliiii !!!!!!!

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String genre;

    @Column
    private String director;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false)
    private String image_url;

    @Column(nullable = false, unique = true)
    private double average_score;

    @Column
    private String description;

    @Column
    private double price;

    @Column
    private int trajanje;


    @ManyToOne
    @JoinColumn(name = "sala")
    @JsonIgnore
    private Sala sala;

    @ManyToMany
    @JoinTable(name="projekcija_glumci", joinColumns=@JoinColumn(name="projekcija_id"),
            inverseJoinColumns=@JoinColumn(name="glumac_id"))
    @JsonIgnore
    private List<Glumac> glumci;

    public  Projekcija(){
    super();
    glumci = new ArrayList<Glumac>();
}

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public double getAverage_score() {
        return average_score;
    }

    public void setAverage_score(double average_score) {
        this.average_score = average_score;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }


    public List<Glumac> getGlumci() {
        return glumci;
    }

    public void setGlumci(List<Glumac> glumci) {
        this.glumci = glumci;
    }

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String imageUrl) {
        this.image_url = imageUrl;
    }

    public double getAverageScore() {
        return average_score;
    }

    public void setAverageScore(double averageScore) {
        this.average_score = averageScore;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Projekcija(String name, String genre, String director, Date date, String image_url, double average_score, String description, double price, int trajanje, Sala sala, List<Glumac> glumci) {
        this.name = name;
        this.genre = genre;
        this.director = director;
        this.date = date;
        this.image_url = image_url;
        this.average_score = average_score;
        this.description = description;
        this.price = price;
        this.trajanje = trajanje;
        this.sala = sala;
        this.glumci = glumci;
    }
}
