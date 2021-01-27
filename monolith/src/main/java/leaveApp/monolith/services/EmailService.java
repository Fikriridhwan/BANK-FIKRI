/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.services;


import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import leaveApp.monolith.entities.Employee;
import leaveApp.monolith.entities.MailRequest;
import leaveApp.monolith.entities.MailResponse;
import leaveApp.monolith.entities.User;
import leaveApp.monolith.viewmodels.RequestVMMonolith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 *
 * @author Laila
 */
@Service
public class EmailService {
    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Configuration config;
    
    public MailResponse sendEmail(Employee employee, String subject, Map<String, Object> model){
        MailResponse response = new MailResponse();
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            //set media type
            MimeMessageHelper helper= new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            //add attachement
//            helper.addAttachment("email.png", new ClassPathResource("email.png"));
            helper.addInline("email", new File("https://www.thewellproject.org/sites/default/files/media/email%20logo.jpg"));

            Template t= config.getTemplate("email-template.ftl");
            String html= FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            helper.setFrom(sender);
            helper.setTo(employee.getEmail());
            helper.setText(html, true);
            helper.setSubject(subject);
            javaMailSender.send(message);
            response.setMessage("mail send to : " + employee.getEmail());
            response.setStatus(Boolean.TRUE);
        } catch (Exception e) {
            response.setMessage("mail sending failure : "+employee.getEmail());
            response.setStatus(Boolean.FALSE);
        }
     return  response;
    }
    
    public MailResponse sendEmailPassword(Employee employee, Map<String, Object> model){
        MailResponse response = new MailResponse();
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            //set media type
            MimeMessageHelper helper= new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            //add attachement
//            helper.addAttachment("email.png", new ClassPathResource("email.png"));
            helper.addInline("email", new File("https://www.thewellproject.org/sites/default/files/media/email%20logo.jpg"));

            Template t= config.getTemplate("email-template.ftl");
            String html= FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            helper.setFrom(sender);
            helper.setTo(employee.getEmail());
            helper.setText(html, true);
            helper.setSubject("Verification change password notification");
            javaMailSender.send(message);
            response.setMessage("mail send to : " + employee.getEmail());
            response.setStatus(Boolean.TRUE);
        } catch (Exception e) {
            response.setMessage("mail sending failure : "+employee.getEmail());
            response.setStatus(Boolean.FALSE);
        }
     return  response;
    }
}
