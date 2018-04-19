package com.example.isa.controller;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.service.PrijateljstvoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/prijateljstvo")
@RestController
public class PrijateljstvoController {
    @Autowired
    PrijateljstvoService prijateljstvoService;


    @RequestMapping(method = RequestMethod.GET,value = "/zahtjev/{id}")
    ResponseEntity posaljiZahtjev(@PathVariable("id") Long id){

       if( prijateljstvoService.posaljiZahtjev(id))
        return new ResponseEntity(HttpStatus.OK);
       else
           return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/obrisi/{id}")
    ResponseEntity obrisi(@PathVariable("id") Long id){
         prijateljstvoService.obrisi(id);
         return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET,value = "/prihvati/{id}")
    ResponseEntity potvrdi(@PathVariable("id") Long id){
        prijateljstvoService.prihvati(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
