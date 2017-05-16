package service_check;

import service_check.module.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by Sung9 on 2017. 5. 10..
 */


public class main {
    public static void main(String[] args) throws IOException, InterruptedException {
        while (true) {

            System.out.println("CM Checking...");
            cm.cm_check();
            System.out.println("TGM Checking...");
            tgm.tgm_check();
            System.out.println("DM Checking...");
            dm.dm_check();
            System.out.println("QGM Checking...");
            qgm.qgm_check();
            System.out.println("AGM Checking...");
            agm.agm_check();

            Thread.sleep(100000);
        }
    }

    //여기서 http response error 발생 시 telegram bot으로 보내주는 상황이면 좋을듯
    public static void result(HttpURLConnection con, String module) throws IOException {
        StringBuilder sb = new StringBuilder();
        int HttpResult = con.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            //System.out.println("" + sb.toString()); //JSON 출력
            System.out.println(con.getResponseCode() + " " + con.getResponseMessage());
        } else {
            System.out.println(con.getResponseCode() + " " + con.getResponseMessage());
            mail_send.sendmail(module, sb.toString(), con.getResponseCode() + " " + con.getResponseMessage());
        }
    }

}