package com.example.isa.service;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Rezervacija;
import com.example.isa.Model.Sjediste;
import com.example.isa.repository.RezervacijaRepository;
import com.example.isa.repository.SjedisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
public class RezervacijaServiceImpl implements RezervacijaService{

    @Autowired
    RezervacijaRepository rezervacijaRepository;
    @Autowired
    MessageService messageService;
    @Autowired
    SjedisteRepository sjedisteRepository;

    @Override
    public Rezervacija save(Rezervacija rez) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);

        RegPosetilacModel ulogovan = (RegPosetilacModel) session.getAttribute("korisnik");
        if(ulogovan == null)
            return  null;
        //da li je sjediste rezervisano za tu projekciju
        for(Sjediste sjediste : rez.getSjedista()) {
            if (rezervacijaRepository.findByDatumiProjekcijeAndSjedista(rez.getDatumiProjekcije(), sjediste) != null)
                return null;
        }
        rez.setPosetilac(ulogovan);
        if(rez.getPozvani().size() + 1 != rez.getSjedista().size()){
            return null;
        }
        ulogovan.setBodovi(ulogovan.getBodovi() +1l);

        Rezervacija saved = rezervacijaRepository.save(rez);

      String poruka = "";
       for(RegPosetilacModel pozvan :saved.getPozvani()){
            poruka = "Dobili ste poziv na projekciju. Da otkazete kliknite na  http://localhost:8080/rezervacije/otkazi_poziv/"+saved.getId()+"/"+pozvan.getId();
           messageService.sendEmai(pozvan.getEmail(),poruka);
       }

       String info = "Vasa rezervacija za "+ saved.getDatumiProjekcije().getProjekcija()
               + " " + saved.getDatumiProjekcije().getDatum().toString()
               + " " +saved.getDatumiProjekcije().getTermin().toString()
                +" je uspjeno obavljenja";
        messageService.sendEmai(ulogovan.getEmail(),info);
       return saved;
    }

    @Override
    public Rezervacija findOne(Long id) {
        return  rezervacijaRepository.findOne(id);
    }

    @Override
    public void otkaziPoziv(Long idRezervacije, Long idKorisnika) {
        Rezervacija rezervacija = rezervacijaRepository.findOne(idRezervacije);
        for(RegPosetilacModel pozvan : rezervacija.getPozvani()){
            if(pozvan.getId() == idKorisnika){
                rezervacija.getPozvani().remove(pozvan);
                rezervacija.getSjedista().remove(0);
            }
        }

        rezervacijaRepository.save(rezervacija);
    }

    @Override
    public void prihvatiPoziv(Long idRezervacije, Long idKorisnika) {
        Rezervacija rezervacija = rezervacijaRepository.findOne(idRezervacije);

        if(rezervacija != null) {
            boolean flag = true;

            while (flag) {
                for (RegPosetilacModel pozvan : rezervacija.getPozvani()) {
                    if (pozvan.getId() == idKorisnika) {
                        for (Sjediste sjediste : rezervacija.getSjedista()) {
                           // sjediste.setPoziv(true);
                            flag = false;
                        }
                    }
                }

                rezervacijaRepository.save(rezervacija);
            }
        }
    }

    @Override
    public boolean otkaziRezervaciju(Long id) {
        Rezervacija rezervacija = rezervacijaRepository.findOne(id);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);
        RegPosetilacModel ulogovan = (RegPosetilacModel) session.getAttribute("korisnik");

        if(rezervacija == null || ulogovan == null || rezervacija.getPosetilac().getId() != ulogovan.getId())
            return  false;

        java.sql.Date danasnjiDatum =  java.sql.Date.valueOf(LocalDate.now());
        if(rezervacija.getDatumiProjekcije().getDatum().before(danasnjiDatum))
            return false;

        LocalTime vrijemeProjekcije = rezervacija.getDatumiProjekcije().getTermin().toLocalTime();

        if( ChronoUnit.MINUTES.between(vrijemeProjekcije, LocalTime.now())>30){
            rezervacijaRepository.delete(rezervacija);

            return true;
        }
        return false;
    }
}
