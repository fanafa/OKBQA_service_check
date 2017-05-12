package service_check.module;

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
        String url = "http://ws.okbqa.org:7745/agm";
        URL object = new URL(url);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");

        String data = "{\"conf\": {\"answer_num\": 5, \"query_interval\": 0.0}, \"queries\": [{\"query\": \"SELECT ?v4 WHERE { ?v4 ?v2 <http://dbpedia.org/resource/Seoul> ; ?v7 <http://dbpedia.org/ontology/River> . } \"}]}";

        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(data.toString());
        wr.flush();

        //display what returns the POST request
        result(con);
    }
}
