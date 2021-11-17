package br.edu.utfpr.pb.tcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired private JavaMailSender mailSender;

    @RequestMapping(path = "/email-send/{email}/{status}", method = RequestMethod.GET)
    @ResponseBody
    public String sendMail(@PathVariable String email, @PathVariable String nrPedido, @PathVariable String status){
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo( email );
            helper.setSubject( "Status do Pedido E-Cakes: " + nrPedido);
            helper.setText("<p>Olá Cliente! Seu Pedido</p>" + "Número: " + nrPedido
                            + ". Se encontra no seguinte status: " +status, true);
            mailSender.send(mail);

            return "OK" + email + status;
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar e-mail";
        }
    }
}