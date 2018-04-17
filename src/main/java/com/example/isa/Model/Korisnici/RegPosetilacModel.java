package com.example.isa.Model.Korisnici;

import com.example.isa.Model.Prijateljstvo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 * Modifed: Jovana
 */
@Entity
@DiscriminatorValue("REGPOSETILAC")
public class RegPosetilacModel extends Korisnik implements Serializable{

    // da li je potvrdio mail adresu
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean accepted;

    @OneToMany(fetch =FetchType.LAZY ,mappedBy = "posiljalac")
    List<Prijateljstvo> prijatelji;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "primalac")
    List<Prijateljstvo> zahtjevi;

    public RegPosetilacModel(){
        super();
       prijatelji = new ArrayList<>();
       zahtjevi = new ArrayList<>();
    }

    public List<Prijateljstvo> getPrijatelji() {
        return prijatelji;
    }

    public void setPrijatelji(List<Prijateljstvo> prijatelji) {
        this.prijatelji = prijatelji;
    }

    public List<Prijateljstvo> getZahtjevi() {
        return zahtjevi;
    }

    public void setZahtjevi(List<Prijateljstvo> zahtjevi) {
        this.zahtjevi = zahtjevi;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}

