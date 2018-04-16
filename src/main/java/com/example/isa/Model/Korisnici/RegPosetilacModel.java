package com.example.isa.Model.Korisnici;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Smekac on 1/31/2018.
 */
@Entity
@DiscriminatorValue("REGPOSETILAC")
public class RegPosetilacModel extends Korisnik implements Serializable{

    // da li je potvrdio mail adresu
    @JsonIgnore
    @JsonProperty
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean accepted;

    public RegPosetilacModel(){
        super();
        this.accepted =false;
    }


    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}

