package service_check;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by Sung9 on 2017. 5. 12..
 */
public class json_verifier {
    public static void verifier(HttpURLConnection con, String input, String module) throws ParseException, IOException {
        JSONObject input_JSON = new JSONObject();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(input);
        input_JSON = (JSONObject)obj;

        switch(module) {
            case ("tgm_en"):
                JSONArray slots_en = (JSONArray) input_JSON.get("slots");

                if (slots_en.isEmpty()){
                    mail_send.sendmail(module, "null" , "Output is null");
                }
                break;
            case ("tgm_ko"):
                JSONArray slots_ko = (JSONArray) input_JSON.get("slots");

                if (slots_ko.isEmpty()){
                    mail_send.sendmail(module, "null" , "Output is null");
                }
                break;
            case ("dm_en"):
                JSONArray ned_en = (JSONArray) input_JSON.get("ned");
                JSONObject ned_arr_en = (JSONObject) ned_en.get(0);
                JSONArray entities_en = (JSONArray) ned_arr_en.get("entities");
                JSONArray classes_en = (JSONArray) ned_arr_en.get("classes");
                JSONArray properties_en = (JSONArray) ned_arr_en.get("properties");

                System.out.println("entities : " + entities_en + "\nclasses : " + classes_en + "\nproperties : " + properties_en + "\n");
                if(entities_en.isEmpty() && classes_en.isEmpty() && properties_en.isEmpty()){
                    mail_send.sendmail(module, "null" , "Output is null");
                }
                break;
            case ("dm_ko"):
                JSONArray ned = (JSONArray) input_JSON.get("ned");
                JSONObject ned_arr = (JSONObject) ned.get(0);
                JSONArray entities = (JSONArray) ned_arr.get("entities");
                JSONArray classes = (JSONArray) ned_arr.get("classes");
                JSONArray properties = (JSONArray) ned_arr.get("properties");

                System.out.println("entities : " + entities + "\nclasses : " + classes + "\nproperties : " + properties + "\n");
                if(entities.isEmpty() && classes.isEmpty() && properties.isEmpty()){
                    mail_send.sendmail(module, "null" , "Output is null");
                }
                break;
            case ("qgm"):
                String message = (String) input_JSON.get("message");

                if(message.equals("undefined method 'product' for nil:NilClass")){
                    mail_send.sendmail(module, "null" , "Output is null");
                }
                break;
            case ("agm"):
                JSONArray result = (JSONArray) input_JSON.get("output");

                if(result.isEmpty()){
                    mail_send.sendmail(module, "null" , "Output is null");
                }
                break;
            case ("cm"):
                break;
        }
        //System.out.println(input_JSON);

        //if (input.equals("[]")){
        //    System.out.println("It is null!!!!");
        //}
    }
}
