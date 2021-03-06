package com.example.isa.controller;

import com.example.isa.Model.Bid;
import com.example.isa.Model.Korisnici.Korisnik;
import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Rekviziti.KorisceniRekvizit;
import com.example.isa.service.BidService;
import com.example.isa.service.KorisceniRekvizitService;
import com.example.isa.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Date;
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
    public ResponseEntity<List<Bid>> getBid() {
        List<Bid> bids = bidService.findAll();
        return new ResponseEntity<>(bids, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bid> vratiPonudu(@PathVariable("id") Long id) {
        Bid bid = bidService.findOne(id);
        return new ResponseEntity<>(bid, HttpStatus.OK);
    }

    @RequestMapping(                        // Radi !!!!!
            value = "/used-prop/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bid>> vratiPonudeZadatiRekvizit(@PathVariable("id") Long id) {
        KorisceniRekvizit korisceniRekvizit = korisceniRekvizitService.findOne(id);
        List<Bid> bids = bidService.findByKorisceniRekvizit(korisceniRekvizit);
        return new ResponseEntity<>(bids, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bid> kreirajPonudu(@RequestBody Bid bid, @PathVariable("id") Long id) {
//        Korisnik korisnik = korisnikService.findByUsername(bid.getRegistrovaniKorisnik().getUsername());
//        System.out.print(korisnik + " ________ " +   bid.getRegistrovaniKorisnik() );
//        RegPosetilacModel regPosetilac = new RegPosetilacModel();
//        if(korisnik.getTipKorisnika().equals("REGPOSETILAC")) {
//            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//            HttpSession session = attr.getRequest().getSession(true);
//          regPosetilac = (RegPosetilacModel) session.getAttribute("korisnik");
//         korisnik = regPosetilac;
//         System.out.print(korisnik + " ________ " +  regPosetilac );
//        }
//        System.out.print(korisnik + " ________ " +   bid.getRegistrovaniKorisnik() );
//
//        KorisceniRekvizit korisceniRekvizit = korisceniRekvizitService.findOne(id);
//        Bid old = bidService.findByRegistrovaniKorisnikAndKorisceniRekvizit(regPosetilac , korisceniRekvizit);
//        if (old != null)
//            bid.setId(old.getId());
//        bid.setDateCreated(new java.util.Date());
//      //  bid.setRegistrovaniKorisnik(korisnik);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        RegPosetilacModel regPosetilac = (RegPosetilacModel) session.getAttribute("korisnik");

        KorisceniRekvizit korisceniRekvizit = korisceniRekvizitService.findOne(id); // Akokorisceni rekvizit ima

        Bid old = bidService.findByRegistrovaniKorisnikAndKorisceniRekvizit(regPosetilac, korisceniRekvizit);

        if (old != null) {
            bid.setId(old.getId());
        }
            bid.setKorisceniRekvizit(korisceniRekvizit);
            bid.setDateCreated(new Date());
            Bid savedBid = bidService.save(bid);

        return new ResponseEntity<>(savedBid, HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bid> osveziPonudu(@RequestBody Bid bid) {
        Bid updatedBid = bidService.save(bid);
        return new ResponseEntity<>(updatedBid, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Bid> izbrisiPonudu(@PathVariable("id") Long id) {
        bidService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
