package service_check;

import service_check.module.dm;
import service_check.module.tgm;

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
            //main.module.cm.cm_check();
            tgm.tgm_check();
            dm.dm_check();
            //main.module.dm.dm_check();
            //main.module.qgm.qgm_check();
            //main.module.agm.agm_check();

            Thread.sleep(3000);
        }
    }


    public static void result(HttpURLConnection con) throws IOException {
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
            System.out.println("" + sb.toString());
        } else {
            System.out.println(con.getResponseMessage());
        }
    }
}