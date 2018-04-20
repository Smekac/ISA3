package com.example.isa.controller;

import com.example.isa.Model.Rezervacija;
import com.example.isa.service.RezervacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value = "/rezervacije")
@RestController
public class RezervacijaController {
    @Autowired
    RezervacijaService rezervacijaService;

    @RequestMapping(value = "/rezervisi",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Rezervacija> kreirajRezeracija(@RequestBody Rezervacija rezervacija){
       if( rezervacijaService.save(rezervacija) != null)
           return new ResponseEntity<>(HttpStatus.CREATED);

       return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Rezervacija> getRezervacija(@PathVariable("id") Long id){
        Rezervacija rezervacija = rezervacijaService.findOne(id);
        return new ResponseEntity<Rezervacija>(rezervacija,HttpStatus.OK);
    }


    @RequestMapping(value = "/otkazi_poziv/{idRezervacije}/{idKorisnika}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity otkaziPoziv(@PathVariable("idRezervacije") Long idRezervacije,@PathVariable("idKorisnika") Long idKorisnika){
       rezervacijaService.otkaziPoziv(idRezervacije,idKorisnika);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/prihvati_poziv/{idRezervacije}/{idKorisnika}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity prihvatiPoziv(@PathVariable("idRezervacije") Long idRezervacije,@PathVariable("idKorisnika") Long idKorisnika){
        rezervacijaService.prihvatiPoziv(idRezervacije,idKorisnika);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/otkazi_rezervaciju/{idRezervacije}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity otkaziRezervaciju(@PathVariable("idRezervacije") Long idRezervacije){
        if(rezervacijaService.otkaziRezervaciju(idRezervacije))
        return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}

