package service_check.module;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static service_check.main.result;

/**
 * Created by Sung9 on 2017. 5. 10..
 {
 "query": "SELECT ?v4 WHERE { ?v4 ?v2 ?v6 ; ?v7 ?v3 . } ",
 "slots": [
 {"p": "is", "s": "v7", "o": "<http://lodqa.org/vocabulary/sort_of>"},
 {"p": "is", "s": "v3", "o": "rdf:Class"},
 {"p": "verbalization", "s": "v3", "o": "rivers"},
 {"p": "is", "s": "v2", "o": "rdf:Property"},
 {"p": "verbalization", "s": "v2", "o": "flow"},
 {"p": "is", "s": "v6", "o": "rdf:Resource|rdfs:Literal"},
 {"p": "verbalization", "s": "v6", "o": "Seoul"}
 ],
 "score": "1.0", "question": "Which rivers flow through Seoul"
 }
 */
public class dm {
    public static void dm_check() throws IOException {
        dm_en();
        //dm_ko();
    }
    static void dm_en() throws IOException {
        String url = "http://110.45.246.131:2357/agdistis/run";
        //http://IP:2357/agdistis/disambiguate
        URL object = new URL(url);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");

        JSONArray slots = new JSONArray();
        JSONObject data = new JSONObject();
        //JSONObject query = new JSONObject();
        JSONObject slot1 = new JSONObject();
        JSONObject slot2 = new JSONObject();
        JSONObject slot3 = new JSONObject();
        JSONObject slot4 = new JSONObject();
        JSONObject slot5 = new JSONObject();
        JSONObject slot6 = new JSONObject();
        JSONObject slot7 = new JSONObject();

        slot1.put("p", "is"); slot1.put("s", "v7"); slot1.put("o", "<http://lodqa.org/vocabulary/sort_of>");
        slot2.put("p", "is"); slot2.put("s", "v3"); slot2.put("o", "rfd:Class");
        slot3.put("p", "verbalization"); slot3.put("s", "v3"); slot3.put("o", "rivers");
        slot4.put("p", "is"); slot4.put("s", "v2"); slot4.put("o", "rdf:Property");
        slot5.put("p", "verbalization"); slot5.put("s", "v2"); slot5.put("o", "flow");
        slot6.put("p", "is"); slot6.put("s", "v6"); slot6.put("o", "rdf:Resource|rdfs:Literal");
        slot7.put("p", "verbalization"); slot7.put("s", "v6"); slot7.put("o", "Seoul");

        slots.add(slot1); slots.add(slot2); slots.add(slot3); slots.add(slot4); slots.add(slot5); slots.add(slot6); slots.add(slot7);

        data.put("query", "SELECT ?v4 WHERE { ?v4 ?v2 ?v6 ; ?v7 ?v3 . }");
        data.put("slots", slots);
        data.put("score", "1.0");
        data.put("question", "Which rivers flow through Seoul");

        //System.out.println(data);
        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(data.toString());
        wr.flush();

        //display what returns the POST request
        result(con);
    }
    static void dm_ko() throws IOException {
        String url = "";
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
