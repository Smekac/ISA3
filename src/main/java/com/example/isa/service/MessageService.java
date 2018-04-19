package com.example.isa.service;

import com.example.isa.Model.Korisnici.RegPosetilacModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private Environment env;
    @Autowired
    private JavaMailSender mailSender;


    // metoda za slanje maila
    public boolean sendEmai(String email, String poruka){

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Poruka");
        mail.setText(poruka);
        mail.setFrom(env.getProperty("spring.mail.username"));

        try {
            mailSender.send(mail);
        }catch (MailException mex){
            System.out.println(mex);
            return false;
        }
        System.out.println("Poslao na " +mail);
        return true;
    }
}
