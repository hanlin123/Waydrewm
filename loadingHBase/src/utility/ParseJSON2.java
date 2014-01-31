package utility;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ParseJSON2 {
	
	public static String[] parseJSON(String line){
		if(line==null || line.equals(""))	return null;
		
		String[] result = new String[2];
		
		JsonElement elem = new JsonParser().parse(line);
		JsonObject parser = elem.getAsJsonObject();

		JsonObject parser2 = parser.getAsJsonObject("user");
		String re = parser2.get("id").toString();
		int size = 10-re.length();
		for(int i=0;i<size;i++)
			re = "0"+re;
		
		result[0] = re;
		result[1] = parser.get("id").toString();
		
		return result;
	}

}
