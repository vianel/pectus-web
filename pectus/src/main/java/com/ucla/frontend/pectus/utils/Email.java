package com.ucla.frontend.pectus.utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carlos
 */

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.zkoss.zk.ui.util.Clients;

import java.util.ArrayList;
import java.util.List;

public class Email {

    String usuarioCorreo;
    String password;

    String rutaArchivo;
    String nombreArchivo;

    String destinatario;
    String asunto;
    String mensaje;
    Properties props = new Properties();

    public Email() throws IOException {
        //com.conamerica.backend.conf.business.enums  PropiedadClave revisar 
       
            

            props.put("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "carlos.rodriguez.ucla@gmail.com@gmail.com");
            props.setProperty("mail.smtp.auth", "true");
            this.password = "GB3542807gb";
            this.usuarioCorreo = "carlos.rodriguez.ucla@gmail.com";
        
    }

    public Email(String usuarioCorreo, String password, String rutaArchivo, String nombreArchivo, String destinatario, String asunto, String mensaje) {
        this.usuarioCorreo = usuarioCorreo;
        this.password = password;
        this.rutaArchivo = rutaArchivo;
        this.nombreArchivo = nombreArchivo;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    public Email(String usuarioCorre, String password, String destinatario, String mensaje) {
        this(usuarioCorre, password, "", "", destinatario, "", mensaje);
    }

    public Email(String usuarioCorre, String password, String destinatario, String asunto, String mensaje) {
        this(usuarioCorre, password, "", "", destinatario, asunto, mensaje);
    }

    public Email(String destinatario, String asunto, String mensaje) {
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.usuarioCorreo = "carlos.rodriguez.ucla@gmail.com";
        this.password = "GB3542807gb";
        this.rutaArchivo = "";
        this.nombreArchivo = "";
    }

    public void llenarCabecera(String destinatario, String asunto, String mensaje) {
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.rutaArchivo = "";
        this.nombreArchivo = "";
    }

    public boolean sendMail() {
        try {

            Session session = Session.getDefaultInstance(props, null);
            BodyPart texto = new MimeBodyPart();
            texto.setText(mensaje);

            BodyPart adjunto = new MimeBodyPart();
            if (!rutaArchivo.equals("")) {
                adjunto.setDataHandler(
                        new DataHandler(new FileDataSource(rutaArchivo)));
                adjunto.setFileName(nombreArchivo);
            }

            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            if (!rutaArchivo.equals("")) {
                multiParte.addBodyPart(adjunto);
            }

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuarioCorreo));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setContent(multiParte);

            Transport t = session.getTransport("smtp");
            t.connect(usuarioCorreo, password);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            return true;
        } catch (Exception e) {
           // e.printStackTrace();
     
            
            return false;
        }
    }
}
