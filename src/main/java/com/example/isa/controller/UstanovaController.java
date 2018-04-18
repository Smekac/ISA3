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
            value = "/sve",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ustanova>> getUstanove() {
        List<Ustanova> ustanove = null;
//        if(proba.equals("BIOSKOP")){
//           ustanove = ustanovaService.findByType(TipUstanove.BIOSKOP);
//        }else{
//            ustanove = ustanovaService.findByType(TipUstanove.POZORISTE);
//
//        }
         ustanove = ustanovaService.findAll();
        return new ResponseEntity<>(ustanove, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}/repertoar",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Projekcija>> getReperotoar(@PathVariable("id")Long idUstanove){
       List<Projekcija> repertoar = ustanovaService.findRepertoar(idUstanove);
        return new ResponseEntity<List<Projekcija>>(repertoar, HttpStatus.OK);
    }



}
