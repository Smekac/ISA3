package com.example.isa.controller;

import com.example.isa.Model.Rekviziti.NoviRekvizit;
import com.example.isa.service.NoviRekvizitService;
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
public class NoviRekvizitController {

    @Autowired
    private NoviRekvizitService noviRekvizitService;

    @RequestMapping(
            value = "/getNoviRekvizit",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NoviRekvizit>> getPropNews() {
        List<NoviRekvizit> PropNews = noviRekvizitService.findAll();
        return new ResponseEntity<>(PropNews, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/createNoviRekvizit",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoviRekvizit> createPropNew(@RequestBody NoviRekvizit PropNew) {
        NoviRekvizit savedPropNew = noviRekvizitService.save(PropNew);
        return new ResponseEntity<>(savedPropNew, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/updateNoviRekvizit",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoviRekvizit> updatePropNew(@RequestBody NoviRekvizit noviRekvizit) {
        NoviRekvizit updatedPropNew = noviRekvizitService.save(noviRekvizit);
        return new ResponseEntity<>(updatedPropNew, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/deleteNoviRekvizit/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoviRekvizit> deletePropNew(@PathVariable("id") Long id) {
        noviRekvizitService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
