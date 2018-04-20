package com.example.isa.controller;

import com.example.isa.Model.Korisnici.AdminFanModel;
import com.example.isa.Model.Korisnici.AdminSisModel;
import com.example.isa.Model.Korisnici.AdminUstanoveModel;
import com.example.isa.Model.Korisnici.Korisnik;
import com.example.isa.service.AdminSisService;
import com.example.isa.service.KorisnikService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Smekac on 2/1/2018.
 */

@RequestMapping( value = "/sis")
@RestController
public class AdminSisController {

    @Autowired
    private AdminSisService adminSisService;

    @Autowired
    private KorisnikService korisnikService;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdminSisModel>> getAllAdminSis() {               //TREBA DA MI DA JSON objeka)
        List<AdminSisModel> listaAdminaSistema = adminSisService.findAll();
        return new ResponseEntity<>(listaAdminaSistema, HttpStatus.OK);     // "200 OK"

    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminSisModel> getOneAdminSis(@PathVariable("id") Long id){
        AdminSisModel datiadmin = adminSisService.findOne(id);
        return new ResponseEntity<>(datiadmin, HttpStatus.OK);     // "200 OK"

    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdminSisModel> kreirajAdminaSistema(@RequestBody AdminSisModel adminSis) {
        AdminSisModel kreiraniAdmin = adminSisService.save(adminSis);
        return new ResponseEntity<>(kreiraniAdmin,HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdminSisModel> promeniVrednost(@RequestBody AdminSisModel adminSis){
        AdminSisModel kreiraniAdmin = adminSisService.save(adminSis);
        return new ResponseEntity<>(kreiraniAdmin,HttpStatus.OK);

    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdminSisModel> izbrisi(@PathVariable("id") Long id){
        adminSisService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

        // /kreirajAdminaFan ima u njegovom kontrkontroleru

    @RequestMapping(
            value = "/dodavanje",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdminSisModel> dodajAdminFana(@RequestBody AdminSisModel adminSisModel){
        adminSisService.save( adminSisModel);
        return new ResponseEntity<>(adminSisModel,HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/adminustanove",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdminUstanoveModel> dodajAdminaUstgnove(@RequestBody AdminUstanoveModel adminUstanoveModel){
        //adminSisService.save( adminSisModel);
        korisnikService.save(adminUstanoveModel);

        return new ResponseEntity<>(adminUstanoveModel,HttpStatus.CREATED);
    }




}
