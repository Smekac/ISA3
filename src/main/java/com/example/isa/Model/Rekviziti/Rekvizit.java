package com.example.isa.Model.Rekviziti;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Smekac on 1/31/2018.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Rekvizit {



    @Id
    @GenericGenerator(name="announcement",strategy = "increment")
    @GeneratedValue(generator = "announcement")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String naslov;

    @Column
    private String opis;

    @Column
    private String image;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumKreiranja;

    public Rekvizit(){}

    public Rekvizit(String naslov, String opis, Date datumKreiranja) {
        this.naslov = naslov;
        this.opis = opis;
        this.datumKreiranja = datumKreiranja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }
}
