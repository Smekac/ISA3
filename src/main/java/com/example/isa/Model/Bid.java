package com.example.isa.Model;


import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Rekviziti.KorisceniRekvizit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Smekac on 27-Jan-18
 */
@Entity
public class Bid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer cena;

    @Column(nullable = false)
    private boolean accepted;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)   //Proveriti !
    private Date dateCreated;

    @ManyToOne(optional = false)
    private RegPosetilacModel registrovaniKorisnik;

    @ManyToOne(optional = false)
    @JsonIgnore
    private KorisceniRekvizit korisceniRekvizit;


    public Bid() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return cena;
    }

    public void setPrice(Integer price) {
        this.cena = price;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public RegPosetilacModel getRegistrovaniKorisnik() {
        return registrovaniKorisnik;
    }

    public void setRegistrovaniKorisnik(RegPosetilacModel registrovaniKorisnik) {
        this.registrovaniKorisnik = registrovaniKorisnik;
    }

    public KorisceniRekvizit getKorisceniRekvizit() {
        return korisceniRekvizit;
    }

    public void setKorisceniRekvizit(KorisceniRekvizit korisceniRekvizit) {
        this.korisceniRekvizit = korisceniRekvizit;
    }
}
