package com.example.isa.service;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import com.example.isa.repository.RegPosetilacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Smekac on 1/31/2018.
 * Updated by Jovana
 */

@Service
public class RegPosetilacServiceImpl implements RegPosetilacService {

    @Autowired
    private RegPosetilacRepository regPosetilacRepository;
    @Autowired
    private Environment env;
    @Autowired
    private JavaMailSender mailSender;

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


    // metoda za slanje maila za potvrdu
    @Override
    public boolean sendEmai(RegPosetilacModel regPosetilac){

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(regPosetilac.getEmail());

        mail.setSubject("Potvrda mail adrese");

        mail.setText("http://localhost:8080/potvrdaMaila/"+regPosetilac.getId());


        mail.setFrom(env.getProperty("spring.mail.username"));


        try {
            mailSender.send(mail);
        }catch (MailException mex){
            System.out.println(mex);
            return false;
        }
        System.out.println("Poslao na " + regPosetilac.getEmail());
        return true;
    }
}
