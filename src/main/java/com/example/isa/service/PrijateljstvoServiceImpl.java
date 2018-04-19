package com.example.isa.service;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Prijateljstvo;
import com.example.isa.repository.PrijateljstvoRepository;
import com.example.isa.repository.RegPosetilacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Service
public class PrijateljstvoServiceImpl implements PrijateljstvoService {
    @Autowired
    PrijateljstvoRepository prijateljstvoRepository;
    @Autowired
    RegPosetilacRepository regPosetilacRepository;


    @Override
    public Prijateljstvo findById(Long id) {
        return prijateljstvoRepository.findById(id);
    }

    @Override
    public Prijateljstvo save(Prijateljstvo prijateljstvo) {
        return prijateljstvoRepository.save(prijateljstvo);
    }

    @Override
    public boolean posaljiZahtjev(Long id) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);

        RegPosetilacModel ulogovan = (RegPosetilacModel) session.getAttribute("korisnik");

        RegPosetilacModel primalac = regPosetilacRepository.findOne(id);

        if(ulogovan != null && primalac != null && ulogovan.getId() != primalac.getId()) {
            Prijateljstvo poslatoPrijateljstvo = new Prijateljstvo(ulogovan, primalac, "poslato");
            ulogovan.getPrijatelji().add(poslatoPrijateljstvo);

            Prijateljstvo zahtjev = new Prijateljstvo(primalac,ulogovan,"na cekanju");
            primalac.getPrijatelji().add(zahtjev);

            prijateljstvoRepository.save(poslatoPrijateljstvo);
            prijateljstvoRepository.save(zahtjev);
            regPosetilacRepository.save(primalac);

            session.setAttribute("korisnik", ulogovan);
            return true;
        }
        return false;
    }


    public void obrisi(Long idZabrisanje){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);

        RegPosetilacModel ulogovan = (RegPosetilacModel) session.getAttribute("korisnik");

        RegPosetilacModel obrisiPrijatelja = regPosetilacRepository.findOne(idZabrisanje);

        if (obrisiPrijatelja != null && ulogovan != null){

            for (int i=0; i<ulogovan.getPrijatelji().size(); i++){
                if (ulogovan.getPrijatelji().get(i).getPrijatelj().getId()==obrisiPrijatelja.getId() ){
                    ulogovan.getPrijatelji().remove(i);
                    break;
                }
            }
            for (int i=0;i<obrisiPrijatelja.getPrijatelji().size(); i++){
                if (obrisiPrijatelja.getPrijatelji().get(i).getPrijatelj().getId() == ulogovan.getId()){
                    obrisiPrijatelja.getPrijatelji().remove(i);
                    break;
                }
            }
            regPosetilacRepository.save(ulogovan);
            regPosetilacRepository.save(obrisiPrijatelja);
        }



    }

    public void prihvati(Long id){

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);

        RegPosetilacModel ulogovan = (RegPosetilacModel) session.getAttribute("korisnik");

        RegPosetilacModel prijatelj = regPosetilacRepository.findOne(id);

        if (prijatelj != null){
            if (ulogovan.getPrijatelji().size() > 0){
                for (int i=0;i<ulogovan.getPrijatelji().size(); i++){
                    Prijateljstvo p = ulogovan.getPrijatelji().get(i);
                    if (p.getPrijatelj().getId() == prijatelj.getId() && p.getStatus().equals("na cekanju")){
                        ulogovan.getPrijatelji().get(i).setStatus("prihvaceno");
                        prijateljstvoRepository.save(p);
                    }
                }
            }

            if(prijatelj.getPrijatelji().size()>0){
                for (int i = 0; i<prijatelj.getPrijatelji().size();i++){
                    Prijateljstvo p = prijatelj.getPrijatelji().get(i);
                    if(p.getPrijatelj().getId() == ulogovan.getId() && p.getStatus().equals("poslato")
                            ){
                        prijatelj.getPrijatelji().get(i).setStatus("prihvaceno");
                        prijateljstvoRepository.save(p);
                    }
                }
            }

            regPosetilacRepository.save(ulogovan);
            regPosetilacRepository.save(prijatelj);

        }
    }

}
