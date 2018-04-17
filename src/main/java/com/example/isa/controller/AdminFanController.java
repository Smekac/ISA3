package com.example.isa.controller;

import com.example.isa.Model.Korisnici.AdminFanModel;
import com.example.isa.service.AdminFanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Smekac on 2/1/2018.
 */
@RestController
public class AdminFanController {

    @Autowired
    private AdminFanService adminFanService;

    @RequestMapping(
            value = "/nadjiAdmineFana",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdminFanModel>> getAllAdminFan() {               //TREBA DA MI DA JSON objeka)
        List<AdminFanModel> listaAdminaFanZone = adminFanService.findAll();
        return new ResponseEntity<>(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"

    }

    @RequestMapping(
            value = "/nadjiAdminaFana/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminFanModel> getOneAdminSis(@PathVariable("id") Long id){
        AdminFanModel datiadmin = adminFanService.findOne(id);
        return new ResponseEntity<>(datiadmin, HttpStatus.OK);     // "200 OK"

    }

    @RequestMapping(
            value = "/kreirajAdminaFan",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<AdminFanModel> kreirajAdminaFana(@Validated @RequestBody AdminFanModel adminSis) {
        AdminFanModel kreiraniAdmin = adminFanService.save(adminSis);
        return new ResponseEntity<>(kreiraniAdmin,HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/promeniAdminaFana",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdminFanModel> promeniVrednost(@RequestBody AdminFanModel admin){
        AdminFanModel kreiraniAdmin = adminFanService.save(admin);
        return new ResponseEntity<>(kreiraniAdmin,HttpStatus.OK);

    }


    @RequestMapping(
            value = "/izbrisiAdminaFan/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdminFanModel> izbrisi(@PathVariable("id") Long id){
        adminFanService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
