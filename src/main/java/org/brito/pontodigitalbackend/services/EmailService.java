package org.brito.pontodigitalbackend.services;

import jakarta.mail.MessagingException;

public interface EmailService {

    void enviarEmail(String emailDestino, String assunto, String corpo) throws MessagingException;


}
