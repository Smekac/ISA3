package com.example.isa.Model.Rekviziti;

import com.example.isa.Model.Bid;
import com.example.isa.Model.Korisnici.AdminFanModel;
import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.sun.org.apache.regexp.internal.RE;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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

    @Column(name = "accepted_bid", nullable = false)          //privremeno(Trebalo bi jedan na jedan) (@OneToOne?)
    private boolean acceptedBid;

    @ManyToOne(optional = true)
    private AdminFanModel adminFan;

    @ManyToOne(optional = false)
    private RegPosetilacModel registrovaniKorisnik;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "korisceniRekvizit")       //mappedBy = "propUsed"
    private Set<Bid> bids;

    public KorisceniRekvizit(){}


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


    public boolean isAcceptedBid() {
        return acceptedBid;
    }

    public void setAcceptedBid(boolean acceptedBid) {
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

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }
}
