package com.example.isa;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Rekviziti.NoviRekvizit;
import com.example.isa.Model.Ustanova;
import com.example.isa.service.NoviRekvizitService;
import com.example.isa.service.RegPosetilacService;
import com.example.isa.service.UstanovaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.isa.tese1Zavisno.*;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Smekac on 4/21/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NoviRekvizitServiceT {

    @Autowired
    NoviRekvizitService noviRekvizitService;
    @Autowired
    RegPosetilacService regPosetilacService;
    @Autowired
    UstanovaService ustanovaService;


    @Test
    public void testFindAll() {
        assertEquals(2, noviRekvizitService.findAll().size());
    }

    @Test
    public void testFindOne() {
        NoviRekvizit newProp = noviRekvizitService.findOne(NP_ID);
        assertThat(newProp).isNotNull();

        assertThat(newProp.getId()).isEqualTo(NP_ID);
        assertThat(newProp.getCena()).isEqualTo(NP_CENA);
        assertThat(newProp.getNaslov()).isEqualTo(NP_NASLOV);
        assertThat(newProp.getOpis()).isEqualTo(NP_OPIS);
        assertThat(newProp.getImage()).isEqualTo(NP_IMAGE);
    }

    @Test
    public void testSave() {
        NoviRekvizit newProp = new NoviRekvizit();
        newProp.setNaslov("Test prop");
        newProp.setOpis("Description of new prop");
        newProp.setImage("image.image.image");
        newProp.setDatumKreiranja(new java.util.Date());
        newProp.setCena(1450);
      //  newProp.setAdminFan(regPosetilacService.findOne(3L));
        newProp.setUstanova(ustanovaService.findOne(1L));
        newProp.setRegistrovaniKorisnik(regPosetilacService.findOne(5L));

        NoviRekvizit savedNewProp = noviRekvizitService.save(newProp);
        NoviRekvizit loadedNewProp = noviRekvizitService.findOne(savedNewProp.getId());

        assertThat(savedNewProp).isNotNull();
        assertThat(loadedNewProp).isNotNull();
        assertEquals(loadedNewProp.getId(), savedNewProp.getId());
    }

    @Test
    public void testDelete() {
        NoviRekvizit newProp = new NoviRekvizit();
        newProp.setNaslov("Test prop");
        newProp.setOpis("Description of new prop");
        newProp.setImage("image.image.image");
        newProp.setDatumKreiranja(new java.util.Date());
        newProp.setCena(1450);
        // newProp.setAdminFan(userService.findOne(1L));
        newProp.setUstanova(ustanovaService.findOne(1L));
        newProp.setRegistrovaniKorisnik(regPosetilacService.findOne(5L));

        NoviRekvizit savedNewProp = noviRekvizitService.save(newProp);
        noviRekvizitService.delete(savedNewProp.getId());
        NoviRekvizit loadedNewProp = noviRekvizitService.findOne(savedNewProp.getId());
        assertNull(loadedNewProp);

    }
    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testReservation() throws Exception {
        NoviRekvizit newProp1 = noviRekvizitService.findOne(2L);
        NoviRekvizit newProp2 = noviRekvizitService.findOne(2L);

        assertEquals(0,newProp1.getVerzija().intValue());
        assertEquals(0,newProp2.getVerzija().intValue());

        RegPosetilacModel user1 = regPosetilacService.findOne(5L);
        RegPosetilacModel user2 = regPosetilacService.findOne(6L);

        noviRekvizitService.proveri(user1.getUsername(),newProp1);
        noviRekvizitService.proveri(user2.getUsername(), newProp2);

    }
}
