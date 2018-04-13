package com.example.isa.controller;

import com.example.isa.Model.Bid;
import com.example.isa.Model.Korisnici.Korisnik;
import com.example.isa.Model.Rekviziti.KorisceniRekvizit;
import com.example.isa.service.BidService;
import com.example.isa.service.KorisceniRekvizitService;
import com.example.isa.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Smekac on 2/2/2018.
 */

@RequestMapping(value = "/bids")
@RestController
public class BidController {

    @Autowired
    private BidService bidService;

    @Autowired
    private KorisceniRekvizitService korisceniRekvizitService;

    @Autowired
    private KorisnikService korisnikService;

//    @RequestMapping(
//            value = "/getBids",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Bid>> getBids() {
//        List<Bid> bids = bidService.findAll();
//        return new ResponseEntity<>(bids, HttpStatus.OK);
//    }

//    @RequestMapping(
//            value = "/createBid",
//            method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Bid> createBid(@RequestBody Bid bid) {
//        Bid savedBid = bidService.save(bid);
//        return new ResponseEntity<>(savedBid, HttpStatus.CREATED);
//    }
//
//    @RequestMapping(
//            value = "/updateBid",
//            method = RequestMethod.PUT,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Bid> updateBid(@RequestBody Bid bid) {
//        Bid updatedBid = bidService.save(bid);
//        return new ResponseEntity<>(updatedBid, HttpStatus.OK);
//    }
//
//    @RequestMapping(
//            value = "/deleteBid/{id}",
//            method = RequestMethod.DELETE,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Bid> deleteBid(@PathVariable("id") Long id) {
//        bidService.delete(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }


    // =============================================================================

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bid>> getBids() {
        List<Bid> bids = bidService.findAll();
        return new ResponseEntity<>(bids, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bid> getBid(@PathVariable("id") Long id) {
        Bid bid = bidService.findOne(id);
        return new ResponseEntity<>(bid, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/KorisceniRekvizit/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bid>> getBidByUsedProp(@PathVariable("id") Long id) {
        KorisceniRekvizit korisceniRekvizit = korisceniRekvizitService.findOne(id);
        List<Bid> bids = bidService.findByKorisceniRekvizit(korisceniRekvizit);
        return new ResponseEntity<>(bids, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bid> createBid(Principal principal, @RequestBody Bid bid, @PathVariable("id") Long id) {
        Korisnik registeredUser = korisnikService.findByUsername(principal.getName());
        KorisceniRekvizit korisceniRekvizit = korisceniRekvizitService.findOne(id);
        Bid old = bidService.findByRegistrovaniKorisnikAndKorisceniRekvizit(registeredUser, korisceniRekvizit);
        if (old != null)
            bid.setId(old.getId());
        bid.setDateCreated(new java.util.Date());
        bid.setRegistrovaniKorisnik(registeredUser);
        bid.setKorisceniRekvizit(korisceniRekvizit);
        Bid savedBid = bidService.save(bid);
        return new ResponseEntity<>(savedBid, HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bid> updateBid(@RequestBody Bid bid) {
        Bid updatedBid = bidService.save(bid);
        return new ResponseEntity<>(updatedBid, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bid> deleteBid(@PathVariable("id") Long id) {
        bidService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
