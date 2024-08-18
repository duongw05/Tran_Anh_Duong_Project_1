/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.util;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import java.util.Properties;
import javax.swing.JOptionPane;



/**
 *
 * @author tungt
 */

import java.util.Properties;
import javax.swing.JOptionPane;

public class XEmail {

    public void sendEmail(String emailToAddress, String textMessage) {
        try {   
             final String from = "phantuananh181205@gmail.com";
             final String pass = "iwmt bzrd mzdy bozn";
            Properties p = new Properties();
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            p.put("mail.smtp.port", "587");
            
            String user = "phantuananh181205@gmail.com";
            String pas = "iwmt bzrd mzdy bozn"; // Đảm bảo rằng mật khẩu ứng dụng này là chính xác.

            Session s = Session.getInstance(p,
                    new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pas);
                }
            });
            Message msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(user));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailToAddress,false));
            msg.setSubject("Thông tin mật khẩu");
            msg.setContent("Mật Khẩu Đăng Nhập Hệ Thống Là : " + textMessage, "text/html; charset=utf-8");

            Transport.send(msg);
            JOptionPane.showMessageDialog(null, "Gửi Thành Công Đến Email " + emailToAddress);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, String.valueOf(e));
            System.out.println(e);
        }
    }
}

