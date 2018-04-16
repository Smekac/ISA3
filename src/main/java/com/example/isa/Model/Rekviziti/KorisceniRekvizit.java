package com.example.isa.Model.Rekviziti;

import com.example.isa.Model.Bid;
import com.example.isa.Model.Korisnici.AdminFanModel;
import com.example.isa.Model.Korisnici.Korisnik;
import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.org.apache.regexp.internal.RE;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Smekac on 1/31/2018.
 */
@Entity
public class KorisceniRekvizit extends Rekvizit implements Serializable {

    @Column(name = "active_until", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date activeUntil;

    @Enumerated(EnumType.STRING)
    private TipKoriscenogRekvizita status;

    @Column(name = "accepted_bid")          //privremeno(Trebalo bi jedan na jedan) (@OneToOne?)  , nullable = false bila greska
    private Long acceptedBid;
            //boolean

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name="admin_fan_id")
    //@ManyToOne(optional = false)
    @ManyToOne(optional = true)
    private AdminFanModel adminFan;


    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name="registrovani_korisnik_id") // IZ sql.importa kako se kolona zove koju hocu da mi vracaa, ......
    @ManyToOne(optional = true)
    private RegPosetilacModel registrovaniKorisnik;  // RegPosetilacModel

//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "korisceniRekvizit")       //mappedBy = "propUsed"
//    private List<Bid> bids;

    public KorisceniRekvizit(){

    }

    public KorisceniRekvizit(String naslov, String opis, Date datumKreiranja, AdminFanModel adminFan, RegPosetilacModel registrovaniKorisnik) {
        super(naslov, opis, datumKreiranja);
        this.adminFan = adminFan;
        this.registrovaniKorisnik = registrovaniKorisnik;
    }

    public TipKoriscenogRekvizita getStatus() {
        return status;
    }

    public void setStatus(TipKoriscenogRekvizita status) {
        this.status = status;
    }

    public Date getActiveUntil() {
        return activeUntil;
    }

    public void setActiveUntil(Date activeUntil) {
        this.activeUntil = activeUntil;
    }


    public Long isAcceptedBid() {
        return acceptedBid;
    }

    public void setAcceptedBid(Long acceptedBid) {
        this.acceptedBid = acceptedBid;
    }

    public AdminFanModel getAdminFan() {
        return adminFan;
    }

    public void setAdminFan(AdminFanModel adminFan) {
        this.adminFan = adminFan;
    }

    public RegPosetilacModel getRegistrovaniKorisnik() {
        return registrovaniKorisnik;
    }

    public void setRegistrovaniKorisnik(RegPosetilacModel registrovaniKorisnik) {
        this.registrovaniKorisnik = registrovaniKorisnik;
    }

    //    public Korisnik getRegistrovaniKorisnik() {
//        return registrovaniKorisnik;
//    }
//
//    public void setRegistrovaniKorisnik( Korisnik registrovaniKorisnik) {
//        this.registrovaniKorisnik = registrovaniKorisnik;
//    }

//    public List<Bid> getBids() {
//        return bids;
//    }
//
//    public void setBids(List<Bid> bids) {
//        this.bids = bids;
//    }
}
