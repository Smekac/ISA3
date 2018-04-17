package com.example.isa.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smekac on 4/14/2018.
 */

@Entity
@Table(name = "Projekcija")
public class Projekcija {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String genre;

    @Column
    private String director;

    @Column
    private String image_url;

    @Column(nullable = false)
    private double average_score;

    @Column
    private String description;

    @Column(nullable = false)
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

    @ManyToMany
    @JoinTable(name= "projekcije_datumiprojekcije", joinColumns= @JoinColumn(name="projekcija_id"),
            inverseJoinColumns = @JoinColumn(name ="datumprojekcije_id" ))
    @JsonIgnore
    private List<DatumiProjekcije> datumiProjekcije;

    public  Projekcija(){
    super();
    glumci = new ArrayList<Glumac>();
    datumiProjekcije = new ArrayList<>();
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


    public List<DatumiProjekcije> getDatumiProjekcije() {
        return datumiProjekcije;
    }

    public void setDatumiProjekcije(List<DatumiProjekcije> datumiProjekcije) {
        this.datumiProjekcije = datumiProjekcije;
    }


}
