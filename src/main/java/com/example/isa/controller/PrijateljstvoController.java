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

@RestController
public class PrijateljstvoController {
    @Autowired
    PrijateljstvoService prijateljstvoService;


    @RequestMapping(method = RequestMethod.GET,value = "/zahtjev/{idPos}/{idPrim}")
    ResponseEntity sendRequest(@PathVariable("idPos") Long idPos, @PathVariable("idPrim")Long idPrim){

       if( prijateljstvoService.posaljiZahtjev(idPos,idPrim))
        return new ResponseEntity(HttpStatus.OK);
       else
           return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
    }
}
