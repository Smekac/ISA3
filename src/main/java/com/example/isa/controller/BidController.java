package com.example.isa.controller;

import com.example.isa.Model.Bid;
import com.example.isa.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Smekac on 2/2/2018.
 */
@RestController
public class BidController {

    @Autowired
    private BidService bidService;

    @RequestMapping(
            value = "/getBids",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bid>> getBids() {
        List<Bid> bids = bidService.findAll();
        return new ResponseEntity<>(bids, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/createBid",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bid> createBid(@RequestBody Bid bid) {
        Bid savedBid = bidService.save(bid);
        return new ResponseEntity<>(savedBid, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/updateBid",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bid> updateBid(@RequestBody Bid bid) {
        Bid updatedBid = bidService.save(bid);
        return new ResponseEntity<>(updatedBid, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/deleteBid/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bid> deleteBid(@PathVariable("id") Long id) {
        bidService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
