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
        System.out.println(pw);

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
            message.setSubject("OKBQA Service Error / OKBQA 서비스 에러");

            switch (module){
                case "cm" :
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("fanafa0@gmail.com"));
                    message.setText("Your OKBQA module \"CM\" has critical error. / 당신의 OKBQA 모듈 \"CM\" 에서 치명적인 에러가 발생했습니다"
                            + "\n\n Please check your module. / 모듈을 확인 바랍니다. \n\n"
                            + " Error : " + error );//내용
                    break;
                case "tgm_en" :
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("fanafa0@gmail.com"));
                    message.setText("Your OKBQA module \"English TGM\" has critical error. / 당신의 OKBQA 모듈 \"영어 TGM\" 에서 치명적인 에러가 발생했습니다"
                            + "\n\n Please check your module. / 모듈을 확인 바랍니다. \n\n"
                            + " Error : " + error );//내용
                    break;
                case "tgm_ko" :
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("fanafa0@gmail.com"));
                    message.setText("Your OKBQA module \"Korean TGM\" has critical error. / 당신의 OKBQA 모듈 \"한국어 TGM\" 에서 치명적인 에러가 발생했습니다"
                            + "\n\n Please check your module. / 모듈을 확인 바랍니다. \n\n"
                            + " Error : " + error );//내용
                    break;
                case "dm_en" :
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("fanafa0@gmail.com"));
                    message.setText("Your OKBQA module \"English DM\" has critical error. / 당신의 OKBQA 모듈 \"영어 DM\" 에서 치명적인 에러가 발생했습니다"
                            + "\n\n Please check your module. / 모듈을 확인 바랍니다. \n\n"
                            + " Error : " + error );//내용
                    break;
                case "dm_ko" :
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("fanafa0@gmail.com"));
                    message.setText("Your OKBQA module \"Korean DM\" has critical error. / 당신의 OKBQA 모듈 \"한국어 DM\" 에서 치명적인 에러가 발생했습니다"
                            + "\n\n Please check your module. / 모듈을 확인 바랍니다. \n\n"
                            + " Error : " + error );//내용
                    break;
                case "qgm" :
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("fanafa0@gmail.com"));
                    message.setText("Your OKBQA module \"QGM\" has critical error. / 당신의 OKBQA 모듈 \"QGM\" 에서 치명적인 에러가 발생했습니다"
                            + "\n\n Please check your module. / 모듈을 확인 바랍니다. \n\n"
                            + " Error : " + error );//내용
                    break;
                case "agm" :
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("fanafa0@gmail.com"));
                    message.setText("Your OKBQA module \"AGM\" has critical error. / 당신의 OKBQA 모듈 \"AGM\" 에서 치명적인 에러가 발생했습니다"
                            + "\n\n Please check your module. / 모듈을 확인 바랍니다. \n\n"
                            + " Error : " + error );//내용
                    break;
            }

            //message.setContent("내용","text/html; charset=utf-8");//글내용을 html타입 charset설정
            System.out.println("Sending...");
            Transport.send(message);
            System.out.println("Send mail completed.");

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

/*
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class mail_send {

    public static void mail_send() {

        Properties p = System.getProperties();
        p.put("mail.smtp.starttls.enable", "true");     // gmail은 무조건 true 고정
        p.put("mail.smtp.host", "smtp.gmail.com");      // smtp 서버 주소
        p.put("mail.smtp.auth", "true");                 // gmail은 무조건 true 고정
        p.put("mail.smtp.port", "587");                 // gmail 포트

        Authenticator auth = new MyAuthentication();

        //session 생성 및  MimeMessage생성
        Session session = Session.getDefaultInstance(p, auth);
        MimeMessage msg = new MimeMessage(session);

        try {
            //편지보낸시간
            msg.setSentDate(new Date());

            InternetAddress from = new InternetAddress();

            from = new InternetAddress("test<test@gmail.com>");

            // 이메일 발신자
            msg.setFrom(from);


            // 이메일 수신자
            InternetAddress to = new InternetAddress("fanafa0@gmail.com");
            msg.setRecipient(Message.RecipientType.TO, to);

            // 이메일 제목
            msg.setSubject("메일 전송 테스트", "UTF-8");

            // 이메일 내용
            msg.setText("내용", "UTF-8");

            // 이메일 헤더
            msg.setHeader("content-Type", "text/html");

            //메일보내기
            javax.mail.Transport.send(msg);

        } catch (AddressException addr_e) {
            addr_e.printStackTrace();
        } catch (MessagingException msg_e) {
            msg_e.printStackTrace();
        }
    }

}


class MyAuthentication extends Authenticator {

    PasswordAuthentication pa;


    public MyAuthentication() {

        String id = "swrc.okbqa@gmail";       // 구글 ID
        String pw = "!Q@W#E$R!";          // 구글 비밀번호

        // ID와 비밀번호를 입력한다.
        pa = new PasswordAuthentication(id, pw);

    }

    // 시스템에서 사용하는 인증정보
    public PasswordAuthentication getPasswordAuthentication() {
        return pa;
    }
}
*/