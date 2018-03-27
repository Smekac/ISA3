package com.example.isa.Model.Korisnici;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Smekac on 1/31/2018.
 */
@Entity
@DiscriminatorValue("ADMINUSTANOVE")
public class AdminUstanoveModel extends Korisnik implements Serializable {

    public AdminUstanoveModel(){}

}
