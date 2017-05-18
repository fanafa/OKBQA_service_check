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
public class cm {
    public static void cm_check() throws IOException, ParseException {
        String url = "http://ws.okbqa.org:7047/cm";
        URL object = new URL(url);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");

        String data = "{\"input\": {\"string\": \"Which rivers flow through Seoul?\",\"language\": \"en\"},\"conf\": {\"sequence\": [\"TGM\", \"DM\", \"QGM\", \"AGM\"]}}";

        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(data.toString());
        wr.flush();

        //display what returns the POST request
        result(con, "cm");
    }
}
