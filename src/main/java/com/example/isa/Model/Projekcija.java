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
@Table(name = "Film")
public class Projekcija {
    // Atributi moraju malim slovima po konvenciji Hibernejta crkliiii !!!!!!!

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String actors;

    @JoinColumn(name="ustanova_id")
    @ManyToOne(optional = false)
    private Ustanova ustanova;

    @Column
    private String genre;

    @Column
    private String director;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column
    private String duration;

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

    @Column
    private Date pocetak;


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
    public Projekcija(String name, String actors, Ustanova ustanova, String genre,
                      String director, Date date, String duration, String image_url,
                      double average_score, String description, double price, int trajanje,
                      Date pocetak, Sala sala, List<Glumac> glumci) {
        this.name = name;
        this.actors = actors;
        this.ustanova = ustanova;
        this.genre = genre;
        this.director = director;
        this.date = date;
        this.duration = duration;
        this.image_url = image_url;
        this.average_score = average_score;
        this.description = description;
        this.price = price;
        this.trajanje = trajanje;
        this.pocetak = pocetak;
        this.sala = sala;
        this.glumci = glumci;
    }

    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
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

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public Ustanova getUstanova() {
        return ustanova;
    }

    public void setUstanova(Ustanova ustanova) {
        this.ustanova = ustanova;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

}