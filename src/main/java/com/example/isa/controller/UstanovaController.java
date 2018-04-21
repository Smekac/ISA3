package com.example.isa.controller;

import com.example.isa.Model.Projekcija;
import com.example.isa.Model.Rekviziti.NoviRekvizit;
import com.example.isa.Model.TipUstanove;
import com.example.isa.Model.Ustanova;
import com.example.isa.service.UstanovaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RequestMapping(value = "/ustanova")
@RestController
public class UstanovaController {

    @Autowired
    UstanovaService ustanovaService;

    @RequestMapping(
            value ="/{tipUstanove}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ustanova>> getUstanove(@PathVariable("tipUstanove")String tipUstanove) {
        List<Ustanova> ustanove = null;
        if(tipUstanove.equalsIgnoreCase("bioskop")){
            ustanove = ustanovaService.findByType(TipUstanove.BIOSKOP);
        }else if(tipUstanove.equalsIgnoreCase("pozoriste")){
            ustanove = ustanovaService.findByType(TipUstanove.POZORISTE);

        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //  ustanove = ustanovaService.findAll();
        return new ResponseEntity<>(ustanove, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}/repertoar",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Projekcija>> getReperotoar(@PathVariable("id")Long idUstanove){
       List<Projekcija> repertoar = ustanovaService.findRepertoar(idUstanove);
        return new ResponseEntity<List<Projekcija>>(repertoar, HttpStatus.OK);
    }



    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ustanova> kreirajUstanovu(@RequestBody Ustanova ustanova) {

        Ustanova ustanove = ustanovaService.save(ustanova);
        return new ResponseEntity<>(ustanove, HttpStatus.OK);
    }


    @RequestMapping(
            value ="/sve",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ustanova>> vratiUstanovu() {
        List<Ustanova> ustanove = null;



        ustanove = ustanovaService.findAll();
        return new ResponseEntity<>(ustanove, HttpStatus.OK);
    }

}
