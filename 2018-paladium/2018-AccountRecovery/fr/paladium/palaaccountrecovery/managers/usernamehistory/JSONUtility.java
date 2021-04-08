package fr.paladium.palaaccountrecovery.managers.usernamehistory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtility {
	
	public Object getJsonObject(String url) throws IOException, ParseException{
        URL url2 = new URL(url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url2.openStream()));
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(reader);
        return  obj;
    }

}
