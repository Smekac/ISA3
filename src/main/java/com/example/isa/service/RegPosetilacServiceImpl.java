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

}
