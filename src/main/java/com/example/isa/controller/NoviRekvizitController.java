package com.example.isa.controller;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Rekviziti.NoviRekvizit;
import com.example.isa.Model.Ustanova;
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
    RegPosetilacModel User = regPosetilacService.findByUsername(REG.getUsername());     // Menjati sve sto je vezano za reg posetioca, posto treba user sa rolama
    newProp.setUstanova(show);
    // newProp.setAdminFan(); //valjda taj korisnik tj. rekvizit ima svog admin-fana ......
    NoviRekvizit savedNewProp = noviRekvizitService.save(newProp);
    return new ResponseEntity<>(savedNewProp, HttpStatus.CREATED);
}
    //==========  !!!!!!!!!!!!!!!!!!!!!!!!!


    @RequestMapping(
            value = "/reserve/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<NoviRekvizit> reservationNewProp(@PathVariable("id") Long id){
        NoviRekvizit datiRekvizit = noviRekvizitService.findOne(id);
        //
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);


        RegPosetilacModel ref = (RegPosetilacModel) session.getAttribute("korisnik");



       if((datiRekvizit.getRegistrovaniKorisnik() == null)){
            datiRekvizit.setRegistrovaniKorisnik(ref);
        }
        noviRekvizitService.save(datiRekvizit);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoviRekvizit> updatePropNew(@RequestBody NoviRekvizit noviRekvizit,@PathVariable Long id) {
//        NoviRekvizit updatedPropNew = noviRekvizitService.save(noviRekvizit);
//        return new ResponseEntity<>(updatedPropNew, HttpStatus.OK);
        Ustanova show = ustanovaService.findOne(id);
        NoviRekvizit old = noviRekvizitService.findOne(noviRekvizit.getId());
        old.setUstanova(show);
        old.setNaslov(noviRekvizit.getNaslov());
        old.setOpis(noviRekvizit.getOpis());
        old.setImage(noviRekvizit.getImage());
        old.setCena(noviRekvizit.getCena());
        NoviRekvizit updatedNewProp = noviRekvizitService.save(old);
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
