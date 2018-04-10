package com.example.isa.controller;

import com.example.isa.DTO.Credentials;
import com.example.isa.DTO.KorisnikDTO;
import com.example.isa.Model.Korisnici.Korisnik;
import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@RestController
public class KorisnikController {

    @Autowired
    KorisnikService korisnikService;
    
    @RequestMapping(
            value = "/logIn",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody Credentials credentials){
        Korisnik korisnik = korisnikService.findByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
        if(korisnik == null)
            return new ResponseEntity<String> ("Neispravna mail adresa ili lozinka", HttpStatus.BAD_REQUEST);

       if(korisnik.getTipKorisnika().equals("REGPOSETILAC"))
       {
           RegPosetilacModel regPos = (RegPosetilacModel) korisnik;
           if(!regPos.isAccepted()){
               return new ResponseEntity<String>("Potvrdite email adresu", HttpStatus.BAD_REQUEST);
           }
       }
        ServletRequestAttributes attr = (ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);

        session.setAttribute("korisnik", korisnik);


        return  new ResponseEntity<String>( "Logovanje je uspjesno", HttpStatus.OK);

    }


}
