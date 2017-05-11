package service_check.module;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static service_check.main.result;

/**
 * Created by Sung9 on 2017. 5. 10..
 */

public class tgm {
    public static void tgm_check() throws IOException {
        tgm_en();
        tgm_ko();
    }
    static void tgm_en() throws IOException {
        String url = "http://ws.okbqa.org:1515/templategeneration/rocknrole";
        URL object = new URL(url);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");

        JSONObject json = new JSONObject();
        json.put("string", "Which river flows through Seoul?");
        json.put("language", "en");

        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(json.toString());
        wr.flush();

        //display what returns the POST request
        result(con);
    }
    static void tgm_ko() throws IOException {
        String url = "http://ws.okbqa.org:1666/sentgm";
        URL object = new URL(url);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");

        JSONObject json = new JSONObject();

        json.put("string", "서울에 있는 산은 무엇인가?");
        json.put("language", "ko");

        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(json.toString());
        wr.flush();

        //display what returns the POST request
        result(con);
    }
}
