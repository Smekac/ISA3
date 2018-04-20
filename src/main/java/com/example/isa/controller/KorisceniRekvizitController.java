package com.example.isa.controller;

import com.example.isa.Model.Bid;
import com.example.isa.Model.Korisnici.Korisnik;
import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Rekviziti.KorisceniRekvizit;
import com.example.isa.service.BidService;
import com.example.isa.service.KorisceniRekvizitService;
import com.example.isa.service.KorisnikService;
import com.example.isa.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.isa.Model.Rekviziti.TipKoriscenogRekvizita;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Date;
import java.util.List;


/**
 * Created by Smekac on 2/2/2018.
 */

@RequestMapping(value = "/KorisceniRekvizit")
@RestController
public class KorisceniRekvizitController {

    @Autowired
    private KorisceniRekvizitService korisceniRekvizitService;

    @Autowired
    private BidService bidService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(
            value = "/sve",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KorisceniRekvizit>> getAllRekvizit() {               //TREBA DA MI DA JSON objeka)
        List<KorisceniRekvizit> listaRekvizita = korisceniRekvizitService.findAll();
        return new ResponseEntity<>(listaRekvizita, HttpStatus.OK);     // "200 OK"

    }

    //all validated by admin
    @RequestMapping(
            value = "/check",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KorisceniRekvizit>> getUsedPropsCheck() {
        List<KorisceniRekvizit> usedProps = korisceniRekvizitService.findByAdminFanIsNotNull();
        return new ResponseEntity<>(usedProps, HttpStatus.OK);
    }

    // Samo za one koji ma je status PRIHVACEN i kojima je polje activeUntil > od danasnjeg datumaa !!
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KorisceniRekvizit>> getUsedProps() {
       // List<KorisceniRekvizit> usedProps = korisceniRekvizitService.findByActiveUntilGreaterThanAndStatusEquals(new java.util.Date(), TipKoriscenogRekvizita.PRIHVACEN);
        List<KorisceniRekvizit> usedProps = korisceniRekvizitService.findByActiveUntilGreaterThanAndAcceptedBidNullAndStatusEquals(new java.util.Date(), TipKoriscenogRekvizita.PRIHVACEN);
        //findByActiveUntilGreaterThanAndAcceptedBidNullAndStatus
        return new ResponseEntity<>(usedProps, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KorisceniRekvizit> getUsedProp(@PathVariable("id") Long id) {
        KorisceniRekvizit usedProp = korisceniRekvizitService.findOne(id);
        return new ResponseEntity<>(usedProp, HttpStatus.OK);
    }

        // treba napraviti korisnickee rolee ......

    //used props by user
    @RequestMapping(
            value = "/user/{username}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KorisceniRekvizit>> getUsedPropsByUser(@PathVariable("username") String username){
        // Poslacu objekat sa korisnikom koji je trenutno na sesiji !!!!!

       // List<KorisceniRekvizit> usedProps = korisceniRekvizitService.findByRegistrovaniKorisnik(principal.getName());
        List<KorisceniRekvizit> usedProps = korisceniRekvizitService.findByRegistrovaniKorisnik_Username(username);


        return new ResponseEntity<>(usedProps,HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{Id}/pirihvacena-ponuda/{odobrena}",     // Radiiiiiiii u postmanu !!!!!
            method = RequestMethod.GET)
    public ResponseEntity<Bid> acceptBid(@PathVariable Long Id, @PathVariable Long odobrena){
        KorisceniRekvizit korisceniRekvizit = korisceniRekvizitService.findOne(Id);

        Bid bid = bidService.findOne(odobrena); // pronadje onu koja je odobrena

        // bid.getRegistrovaniKorisnik().getEmail();

        messageService.sendEmai(bid.getRegistrovaniKorisnik().getEmail(),"Vasa litacija je odobrena cestitamo !!! za rekvizit : " + korisceniRekvizit.getNaslov());

         List<Bid> sveLicitacije = korisceniRekvizit.getBids();

         for (int i=0;i < sveLicitacije.size() ; i++) {

             if(bid.getId() == sveLicitacije.get(i).getId()  ) {

                 messageService.sendEmai(bid.getRegistrovaniKorisnik().getEmail(),"Vasa litacija je odobrena cestitamo !!! za rekvizit : " + korisceniRekvizit.getNaslov());

             } else {
                 messageService.sendEmai(sveLicitacije.get(i).getRegistrovaniKorisnik().getEmail(),"Vasa litacija NIJE prihvacena za rekvizit : " + korisceniRekvizit.getNaslov());

             }
         }


        korisceniRekvizit.setAcceptedBid( bid.getId() );     //  .setAcceptedBid( bid.getId() );
        korisceniRekvizitService.save(korisceniRekvizit);
        return new ResponseEntity<>(bid,HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)        // Gde je vrednost trenutna korisnika setovao na frontu..........
    public ResponseEntity<KorisceniRekvizit> createPropUsed(@RequestBody KorisceniRekvizit usedProp) {
        Korisnik korisnik = korisnikService.findByUsername(usedProp.getRegistrovaniKorisnik().getUsername());

        usedProp = korisceniRekvizitService.createUsedProp(korisnik.getUsername(), usedProp);
        return new ResponseEntity<>(usedProp, HttpStatus.CREATED);
    }

    // =======================================================

    @RequestMapping(
            value = "/kreirajKorisceniRekvizit",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<KorisceniRekvizit> kreirajKorisceniRekvizit(@RequestBody KorisceniRekvizit korisceniRekvizit) {
        korisceniRekvizit.setDatumKreiranja(new Date());
        // korisceniRekvizit.setAcceptedBid();
        korisceniRekvizit.setStatus(TipKoriscenogRekvizita.NACEKANJU); // po difoltuu
        KorisceniRekvizit korisceniRekvizit22 = korisceniRekvizitService.save(korisceniRekvizit);
        return new ResponseEntity<>(korisceniRekvizit22,HttpStatus.CREATED);
    }

    // ========================================================

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KorisceniRekvizit> updatePropUsed(@RequestBody  KorisceniRekvizit usedProp) {
        KorisceniRekvizit updatedUsedProp = korisceniRekvizitService.save(usedProp);
        return new ResponseEntity<>(updatedUsedProp, HttpStatus.OK);
    }



    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<KorisceniRekvizit> deletePropUsed(@PathVariable("id") Long id) {
        korisceniRekvizitService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ====================================================================================
//    @RequestMapping(
//            value = "/nadjiKorisceniRekvizit/{id}",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<KorisceniRekvizit> getOneAdminSis(@PathVariable("id") Long id){
//        KorisceniRekvizit datiadmin = korisceniRekvizitService.findOne(id);
//        return new ResponseEntity<>(datiadmin, HttpStatus.OK);     // "200 OK"
//
//    }
//

    @RequestMapping(
            value = "/nijeZavrsenaZaKorisnika/{username}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KorisceniRekvizit>> korisnikKojiJeObjavioADajeAktivna(@PathVariable("username") String username){
        // Uzeti korisnika sa sesije !!!!
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);
        RegPosetilacModel ref = (RegPosetilacModel) session.getAttribute("korisnik");
                          // ref.getUsername() == Nul , Zasto iako sam se ulogovao na sesiju !!!!!

        List<KorisceniRekvizit> samoOniZadovoljeni = korisceniRekvizitService.findByRegistrovaniKorisnik_UsernameAndActiveUntilGreaterThanAndAcceptedBidNullAndStatusNot(username,new java.util.Date(), TipKoriscenogRekvizita.ODBIJEN);
        return new ResponseEntity<>(samoOniZadovoljeni, HttpStatus.OK);
    }


    //all exept finished
    @RequestMapping(
            value = "/not-finished",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KorisceniRekvizit>> getExceptFinished(){
        List<KorisceniRekvizit> usedProps = korisceniRekvizitService.findByActiveUntilGreaterThanAndAcceptedBidNullAndStatusNot(new java.util.Date(), TipKoriscenogRekvizita.ODBIJEN);
        return new ResponseEntity<>(usedProps, HttpStatus.OK);
    }

//
//    @RequestMapping(
//            value = "/promeniKorisceniRekvizit",
//            method = RequestMethod.PUT,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public ResponseEntity<KorisceniRekvizit> promeniVrednost(@RequestBody KorisceniRekvizit admin){
//        KorisceniRekvizit kreiraniAdmin = korisceniRekvizitService.save(admin);
//        return new ResponseEntity<>(kreiraniAdmin,HttpStatus.OK);
//
//    }
//
//
//    @RequestMapping(
//            value = "/izbrisiKorisceniRekvizit/{id}",
//            method = RequestMethod.DELETE,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public ResponseEntity<KorisceniRekvizit> izbrisi(@PathVariable("id") Long id){
//        korisceniRekvizitService.delete(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

}
