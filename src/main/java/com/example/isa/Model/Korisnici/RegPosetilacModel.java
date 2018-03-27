package com.example.isa.Model.Korisnici;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Smekac on 1/31/2018.
 */
@Entity
@DiscriminatorValue("REGPOSETILAC")
public class RegPosetilacModel extends Korisnik implements Serializable{

    public RegPosetilacModel(){}

}

