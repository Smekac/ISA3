package com.example.isa.Model;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Prijateljstvo implements Serializable {

    @Id
    @GeneratedValue( strategy =GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "posiljalac")
    private RegPosetilacModel posiljalac;

    @ManyToOne
    @JoinColumn(name = "primalac")
    private RegPosetilacModel primalac;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean prihvaceno;

    public Prijateljstvo(){
        super();
    }

    public Prijateljstvo(RegPosetilacModel posiljalac, RegPosetilacModel primalac, boolean prihvaceno) {
        this.posiljalac = posiljalac;
        this.primalac = primalac;
        this.prihvaceno = prihvaceno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegPosetilacModel getPosiljalac() {
        return posiljalac;
    }

    public void setPosiljalac(RegPosetilacModel posiljalac) {
        this.posiljalac = posiljalac;
    }

    public RegPosetilacModel getPrimalac() {
        return primalac;
    }

    public void setPrimalac(RegPosetilacModel primalac) {
        this.primalac = primalac;
    }

    public boolean isPrihvaceno() {
        return prihvaceno;
    }

    public void setPrihvaceno(boolean prihvaceno) {
        this.prihvaceno = prihvaceno;
    }


}
