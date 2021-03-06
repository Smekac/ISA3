package com.example.isa.controller;


import com.example.isa.Model.Korisnici.Korisnik;
import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.service.KorisnikService;
import com.example.isa.DTO.Credentials;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class KorisnikController {

    @Autowired
    KorisnikService korisnikService;

    @RequestMapping(
            value = "/logIn",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> login(@RequestBody Credentials credentials){

        Korisnik korisnik = korisnikService.findByEmailAndPassword(credentials.getEmail(), credentials.getPassword());

        if(korisnik == null)
            return new ResponseEntity<Korisnik> (korisnik, HttpStatus.UNAUTHORIZED);

       if(korisnik.getTipKorisnika().equals("REGPOSETILAC"))
       {
           RegPosetilacModel regPos = (RegPosetilacModel) korisnik;
           if(!regPos.isAccepted())
           {
               korisnik =  null;
               return new ResponseEntity<Korisnik>(korisnik,HttpStatus.UNAUTHORIZED);
           }
       }

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);
        session.setAttribute("korisnik", korisnik);

        return  new ResponseEntity<Korisnik>( korisnik, HttpStatus.OK);
    }

    @RequestMapping(value = "/logOut",
                    method = RequestMethod.GET)
    public ResponseEntity logOut(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);
        session.invalidate();
        return new ResponseEntity(HttpStatus.OK);
    }

   /* @RequestMapping(value = "/getKorisnik",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> getKorisnik(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);

       Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        return new ResponseEntity(korisnik,HttpStatus.OK);
    }*/


    @RequestMapping(
            value = "/svi",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Korisnik>> sviKorisnici(){
        List<Korisnik> korisnici = korisnikService.findAll();

        return new ResponseEntity(HttpStatus.OK);
    }

}
