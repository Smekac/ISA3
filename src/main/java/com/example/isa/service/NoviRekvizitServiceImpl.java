package com.example.isa.service;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Rekviziti.NoviRekvizit;
import com.example.isa.repository.NoviRekvizitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 */


@Service
@Transactional(readOnly = true)
public class NoviRekvizitServiceImpl implements  NoviRekvizitService{

    @Autowired
    private NoviRekvizitRepository noviRekvizitReepozitori;

    @Autowired
    private RegPosetilacService regPosetilacService;

    @Override
    public List<NoviRekvizit> findAll() {
        return noviRekvizitReepozitori.findAll();
    }

    @Override
    public NoviRekvizit findOne(Long id) {
        return noviRekvizitReepozitori.findOne(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)        // Dozvolicemo to da se menja !!!
    public NoviRekvizit save(NoviRekvizit noviRekvizit) {
        return noviRekvizitReepozitori.save(noviRekvizit);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        noviRekvizitReepozitori.delete(id);
    }

    @Override
    public List<NoviRekvizit> findByRegistrovaniKorisnikIsNull() {
        return noviRekvizitReepozitori.findByRegistrovaniKorisnikIsNull();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public NoviRekvizit proveri(String username, NoviRekvizit rekvizit) throws Exception {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);
        RegPosetilacModel ref = (RegPosetilacModel) session.getAttribute("korisnik");

        if (rekvizit.getRegistrovaniKorisnik() != null) {
            return null;
        }

       RegPosetilacModel regPosetilacModel= regPosetilacService.findByUsername(username);


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        regPosetilacModel.setBodovi(regPosetilacModel.getBodovi() + 2l);
        regPosetilacService.save(regPosetilacModel);
        rekvizit.setRegistrovaniKorisnik(regPosetilacModel);
        return save(rekvizit);
    }

//    @Override
//    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
//     public NoviRekvizit proveri(String  username, NoviRekvizit rekvizit ){
//
//        if (rekvizit.getRegistrovaniKorisnik() != null) {
//            return null;
//        }
//
//        RegPosetilacModel regPosetilacModel= regPosetilacService.findByUsername(username);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//        regPosetilacService.save(regPosetilacModel);
//        rekvizit.setRegistrovaniKorisnik(regPosetilacModel);
//        return save(rekvizit);
//    }

}