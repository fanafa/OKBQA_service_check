package service_check;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service_check.module.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by Sung9 on 2017. 5. 10..
 */


public class main {
    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        while (true) {
            int sec = 1000;
            int min = 60000;

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

            System.out.println("Retry to 30 minutes later.");
            Thread.sleep(30 * min);
        }
    }

    public static void result(HttpURLConnection con, String module) throws IOException, ParseException {
        //StringBuilder sb = new StringBuilder();
        String sb = new String();
        int HttpResult = con.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            String line = null;
            line = br.readLine();
            sb = line;
            br.close();

            if(module.equals("tgm_en") || module.equals("tgm_ko") || module.equals("agm")){
                sb = line.substring(1, sb.length()-1);
            }

            if (sb.length() == 0){
                System.out.println("This output is NULL!!");
                mail_send.mail_send_null(); //
            }
            else {
                System.out.println(con.getResponseCode() + " " + con.getResponseMessage());
                //System.out.println("" + sb.toString()); // JSON 출력
                json_check(sb);// verifying
            }
        } else {
            System.out.println(con.getResponseCode() + " " + con.getResponseMessage());
            mail_send.sendmail(module, sb.toString(), con.getResponseCode() + " " + con.getResponseMessage());
        }
    }
    public static void json_check(String input) throws ParseException {
        JSONObject input_JSON = new JSONObject();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(input);
        input_JSON = (JSONObject)obj;

        System.out.println(input_JSON);

        //if (input.equals("[]")){
        //    System.out.println("It is null!!!!");
        //}
    }
}