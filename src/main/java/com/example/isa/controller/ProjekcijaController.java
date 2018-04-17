package com.example.isa.controller;

import com.example.isa.Model.DatumiProjekcije;
import com.example.isa.Model.Projekcija;
import com.example.isa.repository.DatumiProjekcijeRepository;
import com.example.isa.service.ProjekcijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/projekcija")
@RestController
public class ProjekcijaController {
    @Autowired
    ProjekcijaService projekcijaService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Projekcija> getProjekcija(@PathVariable Long id){
        Projekcija projekcija =  projekcijaService.findOne(id);
        return new ResponseEntity<Projekcija>(projekcija, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/datumi",method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DatumiProjekcije>> getDatumiProjekcija(@PathVariable Long id){
       List<DatumiProjekcije> datumi = projekcijaService.findDatumiProjekcije(id);
       return new ResponseEntity<>(datumi, HttpStatus.OK);
    }

}
