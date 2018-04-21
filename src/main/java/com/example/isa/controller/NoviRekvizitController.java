package com.example.isa.controller;

import com.example.isa.Model.Korisnici.AdminFanModel;
import com.example.isa.Model.Korisnici.Korisnik;
import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Rekviziti.NoviRekvizit;
import com.example.isa.Model.Ustanova;
import com.example.isa.service.MessageService;
import com.example.isa.service.NoviRekvizitService;
import com.example.isa.service.RegPosetilacService;
import com.example.isa.service.UstanovaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;

import static java.sql.JDBCType.NULL;

/**
 * Created by Smekac on 2/2/2018.
 */

@RequestMapping(value = "/noviRekvizit")
@RestController
public class NoviRekvizitController {

    @Autowired
    private NoviRekvizitService noviRekvizitService;

    @Autowired
    private RegPosetilacService regPosetilacService;

    @Autowired
    private UstanovaService ustanovaService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(
            value = "/sve",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NoviRekvizit>> getPropNews() {
        List<NoviRekvizit> PropNews = noviRekvizitService.findAll();
        return new ResponseEntity<>(PropNews, HttpStatus.OK);
    }

    //vraca one koji nisu rezervisani tj. nisu zauzetii
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NoviRekvizit>> getNewProps() {
        List<NoviRekvizit> newProps = (List<NoviRekvizit>) noviRekvizitService.findByRegistrovaniKorisnikIsNull();
        return new ResponseEntity<>(newProps, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoviRekvizit> getNewProp(@PathVariable Long id){
        NoviRekvizit newProp = noviRekvizitService.findOne(id);
        return new ResponseEntity<>(newProp, HttpStatus.OK);
    }
//====
//id filma/predstave
@RequestMapping(
        value = "/{id}",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<NoviRekvizit> createNewProp(RegPosetilacModel REG, @RequestBody NoviRekvizit newProp, @PathVariable("id") Long id) {
    Ustanova show = ustanovaService.findOne(id);
    // RegPosetilacModel User = regPosetilacService.findByUsername(REG.getUsername());     // Menjati sve sto je vezano za reg posetioca, posto treba user sa rolama
    newProp.setUstanova(show);
    // newProp.setAdminFan(); //valjda taj korisnik tj. rekvizit ima svog admin-fana ......
    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    HttpSession session= attr.getRequest().getSession(true);
    Korisnik ref = (Korisnik) session.getAttribute("korisnik");

    newProp.setAdminFan((AdminFanModel) ref);

    NoviRekvizit savedNewProp = noviRekvizitService.save(newProp);
    return new ResponseEntity<>(savedNewProp, HttpStatus.CREATED);
}
    //==========  !!!!!!!!!!!!!!!!!!!!!!!!!

    @RequestMapping(
            value = "/rezervisi/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<NoviRekvizit> rezervisiNoviRekvizit(@PathVariable("id") Long id){
        NoviRekvizit datiRekvizit = noviRekvizitService.findOne(id);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);
        RegPosetilacModel ref = (RegPosetilacModel) session.getAttribute("korisnik");
        ref.setBodovi(ref.getBodovi() + 2l);                                                // po dva boda za rezervaciju

        try {

            NoviRekvizit snimljen = noviRekvizitService.proveri(ref.getUsername(), datiRekvizit);

//       if((datiRekvizit.getRegistrovaniKorisnik() == null)){
//            datiRekvizit.setRegistrovaniKorisnik(ref);
//            messageService.sendEmai(datiRekvizit.getRegistrovaniKorisnik().getEmail(),"Rezervisaliste uspjesno ovaj rekvizit");
//       }
            if (snimljen == null) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            //noviRekvizitService.save(datiRekvizit);
            return new ResponseEntity<>(snimljen, HttpStatus.CREATED);

        }catch (Exception e) {

            return new ResponseEntity<>( HttpStatus.MULTIPLE_CHOICES);      // 300
        }

    }


//    @RequestMapping(
//            value = "/createNoviRekvizit",
//            method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<NoviRekvizit> createPropNew(@RequestBody NoviRekvizit PropNew) {
//        NoviRekvizit savedPropNew = noviRekvizitService.save(PropNew);
//        return new ResponseEntity<>(savedPropNew, HttpStatus.CREATED);
//    }

    @RequestMapping(
            value = "/{id}" ,       // /brojRekvizita/{broj}
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoviRekvizit> updatePropNew(@RequestBody NoviRekvizit noviRekvizit,@PathVariable("id") Long id) {

        NoviRekvizit menjni = noviRekvizitService.findOne(noviRekvizit.getId());   // noviRekvizit.getId()

      System.out.print(noviRekvizit.getNaslov() + " ________ " + noviRekvizit.getOpis());
        Ustanova show = ustanovaService.findOne(id);
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);
        Korisnik ref = (Korisnik) session.getAttribute("korisnik");


        menjni.setAdminFan((AdminFanModel) ref);
        menjni.setDatumKreiranja(new Date());
        menjni.setUstanova(show);
        menjni.setNaslov(noviRekvizit.getNaslov());
        menjni.setOpis(noviRekvizit.getOpis());
        menjni.setImage(noviRekvizit.getImage());
        menjni.setCena(noviRekvizit.getCena());
        NoviRekvizit updatedNewProp = noviRekvizitService.save(menjni);
        return new ResponseEntity<>(updatedNewProp, HttpStatus.CREATED);

    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<NoviRekvizit> deletePropNew(@PathVariable("id") Long id) {
        noviRekvizitService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
