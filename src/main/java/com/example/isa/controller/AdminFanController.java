package com.example.isa.controller;

import com.example.isa.Model.Korisnici.AdminFanModel;
import com.example.isa.Model.Korisnici.Korisnik;
import com.example.isa.Model.Rekviziti.KorisceniRekvizit;
import com.example.isa.Model.Rekviziti.NoviRekvizit;
import com.example.isa.Model.Rekviziti.TipKoriscenogRekvizita;
import com.example.isa.Model.Ustanova;
import com.example.isa.service.*;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Smekac on 2/1/2018.
 */
@RestController
public class AdminFanController {

    @Autowired
    private AdminFanService adminFanService;

    @Autowired
    private NoviRekvizitService noviRekvizitService;

    @Autowired
    private KorisceniRekvizitService korisceniRekvizitService;

    @Autowired
    private UstanovaService ustanovaService;

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

    public ResponseEntity<AdminFanModel> kreirajAdminaFana(@Validated @RequestBody AdminFanModel fanAdmin) {
        AdminFanModel kreiraniAdmin = adminFanService.save(fanAdmin);
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

    // Dodaje tematske rekviziteee !!!!!!!!
    @RequestMapping(
           value = "/adminfanPravi/{id}",     // /{id}  id ustanove
           method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )                                                                                             //  @PathVariable("id") Long id
    public ResponseEntity<NoviRekvizit> dodajTematskiRekvizit(@RequestBody NoviRekvizit noviRekvizit,@PathVariable("id") Long id){
        // NoviRekvizit noviRekvizit1 = noviRekvizitService.save(noviRekvizit);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);
        Korisnik admin = (Korisnik) session.getAttribute("korisnik");
    //    AdminFanModel datiAdmin = adminFanService.findOne(id);
   //     RegPosetilacModel registrovani = regPosetilacService.findOne(id);
        Ustanova ustanova = ustanovaService.findOne(id);

        noviRekvizit.setUstanova(ustanova);
        noviRekvizit.setAdminFan((AdminFanModel) admin);

        noviRekvizit.setDatumKreiranja(new Date());

        NoviRekvizit noviRekvizit1 = noviRekvizitService.save(noviRekvizit);

        return new ResponseEntity<NoviRekvizit>(noviRekvizit1,HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/adminfanPravi/{id}",     // /{id}  id ustanove
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )                                                                                             //  @PathVariable("id") Long id
    public ResponseEntity<NoviRekvizit> promeniTematskiRekvizit(@RequestBody NoviRekvizit noviRekvizit,@PathVariable("id") Long id){
        // NoviRekvizit noviRekvizit1 = noviRekvizitService.save(noviRekvizit);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);
        Korisnik admin = (Korisnik) session.getAttribute("korisnik");
        //    AdminFanModel datiAdmin = adminFanService.findOne(id);
        //     RegPosetilacModel registrovani = regPosetilacService.findOne(id);
        Ustanova ustanova = ustanovaService.findOne(id);

        noviRekvizit.setUstanova(ustanova);
        noviRekvizit.setAdminFan((AdminFanModel) admin);

        noviRekvizit.setDatumKreiranja(new Date());

        NoviRekvizit noviRekvizit1 = noviRekvizitService.save(noviRekvizit);

        return new ResponseEntity<NoviRekvizit>(noviRekvizit1,HttpStatus.CREATED);
    }
    // findByActiveUntilGreaterThanAndAcceptedBidNullAndStatusEquals

    @RequestMapping(
            value = "/AdminVidi",     // /{id}  LISTA SVIH koji nisu zavrseni i kojima je status na cekanju + dodatno ogranicenje da nemaju ponuda
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<KorisceniRekvizit>> adminVidi(){
        List<KorisceniRekvizit> korisceniRekvizitII = korisceniRekvizitService.findByActiveUntilGreaterThanAndAcceptedBidNullAndStatusEquals(new Date(), TipKoriscenogRekvizita.NACEKANJU);
            //  KorisceniRekvizit korisceniRekvizit22 = noviRekvizitService.save(korisceniRekvizit);

        return new ResponseEntity<>(korisceniRekvizitII, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/prihvata/{id}",     // /{id}  LISTA SVIH koji nisu zavrseni i kojima je status na cekanju + dodatno ogranicenje da nemaju ponuda
            method = RequestMethod.GET,
          //  consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<KorisceniRekvizit> prihvata(@PathVariable("id") Long id){
       //  List<KorisceniRekvizit> korisceniRekvizitII = korisceniRekvizitService.findByActiveUntilGreaterThanAndAcceptedBidNullAndStatusEquals(new Date(), TipKoriscenogRekvizita.NACEKANJU);
        //  KorisceniRekvizit korisceniRekvizit22 = noviRekvizitService.save(korisceniRekvizit);
        KorisceniRekvizit korisceniRekvizit = korisceniRekvizitService.findOne(id);

        // U zavisnosti koji je administrator preuzeo
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);
        Korisnik admin = (Korisnik) session.getAttribute("korisnik");

        korisceniRekvizit.setAdminFan((AdminFanModel) admin);

        korisceniRekvizit.setStatus(TipKoriscenogRekvizita.PRIHVACEN);
        korisceniRekvizitService.save(korisceniRekvizit);
        return new ResponseEntity<>(korisceniRekvizit, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/odbija/{id}",     // /{id}  LISTA SVIH koji nisu zavrseni i kojima je status na cekanju + dodatno ogranicenje da nemaju ponuda
            method = RequestMethod.GET,
         //   consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<KorisceniRekvizit> odbija(@PathVariable("id") Long id){
        //  List<KorisceniRekvizit> korisceniRekvizitII = korisceniRekvizitService.findByActiveUntilGreaterThanAndAcceptedBidNullAndStatusEquals(new Date(), TipKoriscenogRekvizita.NACEKANJU);
        //  KorisceniRekvizit korisceniRekvizit22 = noviRekvizitService.save(korisceniRekvizit);
        KorisceniRekvizit korisceniRekvizit = korisceniRekvizitService.findOne(id);
        korisceniRekvizit.setStatus(TipKoriscenogRekvizita.ODBIJEN);
        korisceniRekvizitService.save(korisceniRekvizit);
        return new ResponseEntity<>(korisceniRekvizit, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/promenaAdmina",     // da promeni svoje podatke !!!!
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdminFanModel> azurira( @RequestBody AdminFanModel adminFanModel) {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);
        Korisnik ref = (Korisnik) session.getAttribute("korisnik");


        // AdminFanModel kreiraniAdmin = adminFanService.findByPassword(adminFanModel.getPassword());                                           // adminFanService.save(adminFanModel);
        AdminFanModel kreiraniAdmin = adminFanService.findOne(ref.getId());     // Sigurnijee !!!

        kreiraniAdmin.setEmail(adminFanModel.getEmail());
        kreiraniAdmin.setGrad(adminFanModel.getGrad());
        kreiraniAdmin.setIme(adminFanModel.getIme());
        kreiraniAdmin.setNumber(adminFanModel.getNumber());
        kreiraniAdmin.setUsername(adminFanModel.getUsername());
        kreiraniAdmin.setPrezime(adminFanModel.getPrezime());
        // kreiraniAdmin.setTipKorisnika("ADMINFAN");
        kreiraniAdmin.setPassword(adminFanModel.getPassword());


        adminFanService.save(kreiraniAdmin);

        return new ResponseEntity<>(kreiraniAdmin,HttpStatus.CREATED);
    }


    @RequestMapping(
            value = "/promenaSifre",     // da promeni svoje podatke !!!!
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdminFanModel> promenaSifre( @RequestBody AdminFanModel korisnik ) {
//        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpSession session= attr.getRequest().getSession(true);
//        Korisnik ref = (Korisnik) session.getAttribute("korisnik");

        AdminFanModel adi = adminFanService.findOne(korisnik.getId());

        adi.setPassword(korisnik.getPassword());

        adminFanService.save(adi);
        return new ResponseEntity<>( adi ,HttpStatus.OK);
    }


//
//    @RequestMapping(
//            value = "/azurira",     // da promeni svoje podatke !!!!
//            method = RequestMethod.PUT,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public ResponseEntity<AdminFanModel> promenaAdmina( @RequestBody  adminFanModel) {
//        AdminFanModel kreiraniAdmin = adminFanService.save(adminFanModel);
//
//        return new ResponseEntity<>(kreiraniAdmin,HttpStatus.OK);
//    }


}