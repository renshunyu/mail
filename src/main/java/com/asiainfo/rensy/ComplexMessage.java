package com.asiainfo.rensy;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lining on 2018/5/26.
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
        imagesMap.put("logo","D:\\asf_logo_wide.gif");
        imagesMap.put("image","D:\\哈士奇.jpg");

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
        try {
            message.setText("Hello");
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