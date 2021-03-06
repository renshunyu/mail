package com.asiainfo.rensy;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author rensy
 * @date 2018/5/26
 */
public class ComplexMessage {
    private static final String NICKNAME = "李宁";
    private static final String USERNAME = "152****1370@163.com";
    private static final String PASSWORD = "******";
    private static final String PROTOCOL = "smtp";
    private static final String HOST = "smtp.163.com";
    private static final String PORT = "25";

    public static void main(String[] args) {

        String to = "******9997@qq.com;******9267@163.com";
        String subject = "HTML+内嵌图片+附件邮件主题";
        String body = "<h4>HTML+附件+内嵌图片的邮件测试！！！</h4></br><a href=http://www.apache.org>" + "点击跳转</a>" +
                "<h4>LOGO图标</h4></hr><img src=\"cid:logo\">" +
                "<h4>哈士奇</h4></hr><img src=\"cid:image\">";
        String attach = "D:\\TXT\\CMD命令.txt;D:\\TXT\\常用MYSQL语句.txt";

        Map<String,String> imagesMap = new HashMap<String,String>();
        imagesMap.put("logo","E:\\idea\\mail\\logo.gif");
        imagesMap.put("image","E:\\idea\\mail\\Jellyfish.jpg");

        Properties props = new Properties();
        props.put("mail.smtp.host","mail.asiainfo-sec.com");
        props.put("mail.smtp.user","rensy@asiainfo-sec.com");
        props.put("mail.smtp.password","3edc@WSX");
        System.out.print("3edc@WSX");
        props.put("mail.smtp.auth", "true");
        Session session=Session.getInstance(props,new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("rensy@asiainfo-sec.com", "3edc@WSX");
            }
        });
        Session[] sessions={session};

        Message  message = new MimeMessage(session);

        Address address =  new InternetAddress();
        ((InternetAddress) address).setAddress("rensy@asiainfo-sec.com");

        try {
            message.setFrom(address);
            message.addRecipient(Message.RecipientType.TO ,address);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
//        try {
//
//            message.setContent(body,"text/html;charset=UTF-8");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
        //
        // This HTML mail have to 2 part, the BODY and the embedded image
        //
        MimeMultipart multipart = new MimeMultipart("related");

        // first part  (the html)
        BodyPart messageBodyPart = new MimeBodyPart();
        String htmlText = "<h4>HTML+附件+内嵌图片的邮件测试！！！</h4></br><a href=http://www.apache.org>" + "点击跳转</a>" +
                "<h4>LOGO图标</h4></hr><img src=\"cid:logo\">" +
                "<h4>哈士奇</h4></hr><img src=\"cid:image\">";
        try {
            messageBodyPart.setContent(htmlText, "text/html;charset=UTF-8");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // add it
        try {
            multipart.addBodyPart(messageBodyPart);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // second part (the image)
        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(".\\logo.gif");
        //DataSource fds2 = new FileDataSource("F:\\idea\\mail\\1334828415340.jpg");
        try {
            messageBodyPart.setDataHandler(new DataHandler(fds));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            messageBodyPart.setHeader("Content-ID","<logo>");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // add it
        try {
            multipart.addBodyPart(messageBodyPart);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // th part (the image)
        messageBodyPart = new MimeBodyPart();
        DataSource fds2 = new FileDataSource(".\\1334828415340.jpg");
        try {
            messageBodyPart.setDataHandler(new DataHandler(fds2));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            messageBodyPart.setHeader("Content-ID","<image>");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // add it
        try {
            multipart.addBodyPart(messageBodyPart);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // put everything together
        try {
            message.setContent(multipart);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }



}