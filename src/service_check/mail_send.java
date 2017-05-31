package service_check;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;

public class mail_send {
    public static void sendmail(String module, String result, String error) throws IOException {

        //github upload를 위한 password file처리
        File file = new File("/Users/Sung9/Dev/pw.txt");
        FileReader file_r = new FileReader("/Users/Sung9/Dev/pw.txt");
        BufferedReader br = new BufferedReader(file_r);
        String pw = br.readLine();

        String username = "swrc.okbqa";
        String password = pw;

        Properties props = new Properties();
        /*props.put("mail.smtp.user",username);
        props.put("mail.smtp.password", password);*/
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.EnableSSL.enable","true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }});
        System.out.println("Superviser verifying...");
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("swrc.okbqa@gmail.com"));//
            message.setSubject("OKBQA 서비스 에러");

            switch (module){
                case "cm" :
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("fanafa0@gmail.com"));
                    message.setText("당신의 OKBQA 모듈 \"CM\" 에서 문제가 발생했습니다. "
                            + "\n\n모듈의 작동 상태를 확인 바랍니다. "
                            + " \n\n Error : " + error );//내용
                    break;
                case "tgm_en" :
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("fanafa0@gmail.com"));
                    message.setText("당신의 OKBQA 모듈 \"영어 TGM\" 에서 문제가 발생했습니다. "
                            + "\n\n모듈의 작동 상태를 확인 바랍니다. "
                            + " \n\n Error : " + error );//내용
                    break;
                case "tgm_ko" :
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("fanafa0@gmail.com"));
                    message.setText("당신의 OKBQA 모듈 \"한국어 TGM\" 에서 문제가 발생했습니다. "
                            + "\n\n모듈의 작동 상태를 확인 바랍니다. "
                            + " \n\n Error : " + error );//내용
                    break;
                case "dm_en" :
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("fanafa0@gmail.com"));
                    message.setText("당신의 OKBQA 모듈 \"영어 DM\" 에서 문제가 발생했습니다. "
                            + "\n\n모듈의 작동 상태를 확인 바랍니다. "
                            + " \n\n Error : " + error );//내용
                    break;
                case "dm_ko" :
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("fanafa0@gmail.com"));
                    message.setText("당신의 OKBQA 모듈 \"한국어 DM\" 에서 문제가 발생했습니다. "
                            + "\n\n모듈의 작동 상태를 확인 바랍니다. "
                            + " \n\n Error : " + error );//내용
                    break;
                case "qgm" :
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("fanafa0@gmail.com"));
                    message.setText("당신의 OKBQA 모듈 \"QGM\" 에서 문제가 발생했습니다. "
                            + "\n\n모듈의 작동 상태를 확인 바랍니다. "
                            + " \n\n Error : " + error );//내용
                    break;
                case "agm" :
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("fanafa0@gmail.com"));
                    message.setText("당신의 OKBQA 모듈 \"AGM\" 에서 문제가 발생했습니다. "
                            + "\n\n모듈의 작동 상태를 확인 바랍니다. "
                            + " \n\n Error : " + error );//내용
                    break;
            }

            //message.setContent("내용","text/html; charset=utf-8");//글내용을 html타입 charset설정
            System.out.println("Sending...");
            Transport.send(message);
            System.out.println("Send mail completed.");
            file_r.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}