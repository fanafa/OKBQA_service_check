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
public class agm {
    public static void agm_check() throws IOException {
        String url = "http://ws.okbqa.org:1666/sentgm";
        URL object = new URL(url);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");

        JSONObject cred = new JSONObject();
        //JSONObject auth = new JSONObject();
        //JSONObject parent = new JSONObject();

        cred.put("string", "서울에 있는 산은 무엇인가?");
        cred.put("language", "ko");

        /*
        auth.put("tenantName", "adm");
        auth.put("passwordCredentials", cred.toString());

        parent.put("auth", auth.toString());
        */

        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(cred.toString());
        wr.flush();

        //display what returns the POST request
        result(con);
    }
}
