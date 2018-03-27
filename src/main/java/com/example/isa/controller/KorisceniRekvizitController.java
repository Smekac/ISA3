package com.example.isa.controller;

import com.example.isa.Model.Rekviziti.KorisceniRekvizit;
import com.example.isa.service.KorisceniRekvizitService;
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
public class KorisceniRekvizitController {

    @Autowired
    private KorisceniRekvizitService korisceniRekvizitService;

    @RequestMapping(
            value = "/nadjiKorisceniRekvizit",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KorisceniRekvizit>> getAllRekvizit() {               //TREBA DA MI DA JSON objeka)
        List<KorisceniRekvizit> listaRekvizita = korisceniRekvizitService.findAll();
        return new ResponseEntity<>(listaRekvizita, HttpStatus.OK);     // "200 OK"

    }

    @RequestMapping(
            value = "/nadjiKorisceniRekvizit/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KorisceniRekvizit> getOneAdminSis(@PathVariable("id") Long id){
        KorisceniRekvizit datiadmin = korisceniRekvizitService.findOne(id);
        return new ResponseEntity<>(datiadmin, HttpStatus.OK);     // "200 OK"

    }

    @RequestMapping(
            value = "/kreirajKorisceniRekvizit",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<KorisceniRekvizit> kreirajAdminaFana(@RequestBody KorisceniRekvizit adminSis) {
        KorisceniRekvizit kreiraniAdmin = korisceniRekvizitService.save(adminSis);
        return new ResponseEntity<>(kreiraniAdmin,HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/promeniKorisceniRekvizit",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<KorisceniRekvizit> promeniVrednost(@RequestBody KorisceniRekvizit admin){
        KorisceniRekvizit kreiraniAdmin = korisceniRekvizitService.save(admin);
        return new ResponseEntity<>(kreiraniAdmin,HttpStatus.OK);

    }


    @RequestMapping(
            value = "/izbrisiKorisceniRekvizit/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<KorisceniRekvizit> izbrisi(@PathVariable("id") Long id){
        korisceniRekvizitService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
