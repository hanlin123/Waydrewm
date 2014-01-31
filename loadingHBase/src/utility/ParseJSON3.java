package utility;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ParseJSON3 {
	
	public static String[] parseJSON(String line){
		if(line==null || line.equals(""))	return null;
		String[] result = new String[2];
		
		JsonElement elem = new JsonParser().parse(line);
		JsonObject parser = elem.getAsJsonObject();

		JsonObject parser2 = null;
		
		try{
			parser2 = parser.getAsJsonObject("retweeted_status");
			parser2 = parser2.getAsJsonObject("user");
			result[0] = parser2.get("id").toString();
		}catch(NullPointerException e){
			return null;
		}
		
		parser2 = parser.getAsJsonObject("user");
		String tmp = parser2.get("id").toString();
		int size = 10-tmp.length();
		for(int i=0; i<size; i++)
			tmp = "0"+tmp;
		result[1] = tmp;
		
		return result;
	}
}
