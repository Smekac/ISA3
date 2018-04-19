package com.example.isa.controller;

import com.example.isa.Model.Segment;
import com.example.isa.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalaController {
    @Autowired
    SalaService salaService;

    @RequestMapping(method = RequestMethod.GET,
        value = "/sala/{id}/segmenti",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Segment>> getSegments(@PathVariable("id")Long id){
       return new ResponseEntity<List<Segment>>(salaService.findSegments(id), HttpStatus.OK);

    }
}
