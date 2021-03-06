package com.example.isa.Model.Korisnici;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Smekac on 1/31/2018.
 * Modified: Jovana
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Korisnik implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true,nullable = false)
    private Long id;

    @NotBlank
    @Column(nullable = false,unique = true)
    private String username;

    @JsonProperty
    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false)
    private String ime;

    @NotBlank
    @Column(nullable = false)
    private String prezime;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String grad;

    @Size(min = 6)
    @Column(nullable = false)
    private String number;

    @Column(nullable = true)
    private Long bodovi;            // Brojimo broj akcija ..

    @Column(name="type", nullable=false, updatable=false, insertable=false)
    private String tipKorisnika;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private TipSkala tipskale;

    public Korisnik(){}

    public Korisnik(String username, String password, String ime, String prezime, String email, String grad, String number) {
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.grad = grad;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(String tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    public Long getBodovi() {
        return bodovi;
    }

    public void setBodovi(Long bodovi) {
        this.bodovi = bodovi;
    }

    public TipSkala getTipskale() {
        return tipskale;
    }

    public void setTipskale(TipSkala tipskale) {
        this.tipskale = tipskale;
    }
}
