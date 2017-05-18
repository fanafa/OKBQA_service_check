package service_check.module;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static service_check.main.result;

/**
 * Created by Sung9 on 2017. 5. 10..
 */
public class qgm {
    public static void qgm_check() throws IOException, ParseException {
        qgm();
    }
    static void qgm() throws IOException, ParseException {
        String url = "http://ws.okbqa.org:38401/queries?max_hop=1";
        URL object = new URL(url);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");

        String data = "{\"disambiguation\":[{\"entities\": [{\"var\": \"v6\", \"score\": 1, \"value\": \"http://dbpedia.org/resource/Gunsan\"}],\"classes\": [{\"var\": \"v3\", \"score\": 0.25, \"value\": \"http://dbpedia.org/ontology/River\"},{\"var\": \"v3\", \"score\": 0.2564102564102564, \"value\": \"http://dbpedia.org/ontology/River\"}],\"score\": 1,\"properties\": [{\"var\": \"v2\", \"score\": 0.2564102564102564, \"value\": \"http://dbpedia.org/ontology/city\"}]}],\"template\": {\"query\": \"SELECT ?v4 WHERE { ?v4 ?v2 ?v6 ; ?v7 ?v3 . }\",\"slots\": [{\"p\": \"is\", \"s\": \"v2\", \"o\": \"rdf:Property\"},{\"p\": \"verbalization\", \"s\": \"v2\", \"o\": \"flow\"},{\"p\": \"is\", \"s\": \"v6\", \"o\": \"rdf:Resource|rdfs:Literal\"},{\"p\": \"verbalization\", \"s\": \"v6\", \"o\": \"Gunsan\"},{\"p\": \"is\", \"s\": \"v7\", \"o\": \"<http://lodqa.org/vocabulary/sort_of>\"},{\"p\": \"is\", \"s\": \"v3\", \"o\": \"rdf:Class\"},{\"p\": \"verbalization\", \"s\": \"v3\", \"o\": \"rivers\"}],\"score\": \"1.0\",\"question\": \"Which rivers flow through Gunsan?\"}}";

        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(data.toString());
        wr.flush();

        //display what returns the POST request
        result(con, "qgm");
    }
}
