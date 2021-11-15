package br.edu.utfpr.pb.tcc.service;

import br.edu.utfpr.pb.tcc.model.Mail;

public interface EmailService {
    void sendEmail(Mail mail);
}
