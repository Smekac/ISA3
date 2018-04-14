package com.example.isa.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Smekac on 4/14/2018.
 */

@Entity
@Table(name = "Film")
public class Film {
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

    public Film(){}

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
