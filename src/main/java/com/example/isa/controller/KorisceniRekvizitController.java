package com.example.isa.controller;

import com.example.isa.Model.Bid;
import com.example.isa.Model.Rekviziti.KorisceniRekvizit;
import com.example.isa.service.BidService;
import com.example.isa.service.KorisceniRekvizitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.isa.Model.Rekviziti.TipKoriscenogRekvizita;

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

    // Samo oni koji su potvrdjeni
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KorisceniRekvizit>> getUsedProps() {
        List<KorisceniRekvizit> usedProps = korisceniRekvizitService.findByActiveUntilGreaterThanAndStatusEquals(new java.util.Date(), TipKoriscenogRekvizita.PRIHVACEN);
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

//    //used props by user
//    @RequestMapping(
//            value = "/user",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<UsedProp>> getUsedPropsByUser(Principal principal){
//        List<UsedProp> usedProps = usedPropService.findByUsername(principal.getName());
//        return new ResponseEntity<>(usedProps,HttpStatus.OK);
//    }

    @RequestMapping(
            value = "/{usedPropId}/accept-bid/{acceptedBidId}",
            method = RequestMethod.GET)
    public ResponseEntity<Bid> acceptBid(@PathVariable Long usedPropId, @PathVariable Long acceptedBidId){
        KorisceniRekvizit usedProp = korisceniRekvizitService.findOne(usedPropId);
        Bid bid = bidService.findOne(acceptedBidId);
        usedProp.setAcceptedBid( bid.getId() );     //  .setAcceptedBid( bid.getId() );
        korisceniRekvizitService.save(usedProp);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


//    @RequestMapping(
//            method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<KorisceniRekvizit> createPropUsed(Principal principal, @RequestBody UsedProp usedProp) {
//        usedProp = usedPropService.createUsedProp(principal.getName(),usedProp);
//        return new ResponseEntity<>(usedProp, HttpStatus.CREATED);
//    }

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
//    @RequestMapping(
//            value = "/kreirajKorisceniRekvizit",
//            method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public ResponseEntity<KorisceniRekvizit> kreirajAdminaFana(@RequestBody KorisceniRekvizit adminSis) {
//        KorisceniRekvizit kreiraniAdmin = korisceniRekvizitService.save(adminSis);
//        return new ResponseEntity<>(kreiraniAdmin,HttpStatus.CREATED);
//    }
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
