package com.example.isa.service;

import com.example.isa.Model.Korisnici.Korisnik;
import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.Model.Prijateljstvo;
import com.example.isa.repository.RegPosetilacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 * Updated by Jovana
 */

@Service
public class RegPosetilacServiceImpl implements RegPosetilacService {

    @Autowired
    private RegPosetilacRepository regPosetilacRepository;

    @Override
    public List<RegPosetilacModel> findAll() {
        return regPosetilacRepository.findAll();
    }

    @Override
    public RegPosetilacModel findOne(Long id) {
        return regPosetilacRepository.findOne(id);
    }

    @Override
    public RegPosetilacModel save(RegPosetilacModel regPosetilac) {
        return  regPosetilacRepository.save(regPosetilac);

    }

    @Override
    public void delete(Long id) {
        regPosetilacRepository.delete(id);
    }





    @Override
    public void confirmEmailAdress(Long id) {

        RegPosetilacModel kor = regPosetilacRepository.findOne(id);
        kor.setAccepted(true);
        regPosetilacRepository.save(kor);

    }

    @Override
    public RegPosetilacModel findByUsername(String username) {
        return regPosetilacRepository.findByUsername(username);
    }

    @Override
    public List<Prijateljstvo> findPrijatelje() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);

        RegPosetilacModel ulogovan = (RegPosetilacModel) session.getAttribute("korisnik");
        if(ulogovan != null)
        return ulogovan.getPrijatelji();

        return null;
    }

    @Override
    public RegPosetilacModel update(RegPosetilacModel regPosetilacModel) {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);
        RegPosetilacModel kor = (RegPosetilacModel) session.getAttribute("korisnik");

        if(kor != null) {

            kor.setEmail(regPosetilacModel.getEmail());
            kor.setGrad(regPosetilacModel.getGrad());
            kor.setIme(regPosetilacModel.getIme());
            kor.setNumber(regPosetilacModel.getNumber());
            kor.setUsername(regPosetilacModel.getUsername());
            kor.setPrezime(regPosetilacModel.getPrezime());
            kor.setPassword(regPosetilacModel.getPassword());


            return regPosetilacRepository.save(kor);
        }
        return null;
    }

}
