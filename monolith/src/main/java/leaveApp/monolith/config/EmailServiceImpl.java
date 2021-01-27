/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.config;

import leaveApp.monolith.entities.Employee;
import leaveApp.monolith.entities.User;
import leaveApp.monolith.viewmodels.RequestVM;
import leaveApp.monolith.viewmodels.RequestVMMonolith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class EmailServiceImpl {
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;
    
    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    
//    @Transactional
    public void sendingEmail(RequestVMMonolith request, Employee employee, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(employee.getEmail()); 
        message.setSubject(subject+" Cuti "+request.getLeaveDataName()); 
        message.setText(text);
        javaMailSender.send(message);
    }
    
    public void sendingEmailPassword(User user, Employee employee, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(employee.getEmail()); 
        message.setSubject(subject); 
        message.setText(text+user.getCode());
        javaMailSender.send(message);
    }
}
