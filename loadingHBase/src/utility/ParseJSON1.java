package utility;

import java.util.HashMap;

//import org.apache.commons.lang.StringEscapeUtils;



import org.apache.commons.lang.StringEscapeUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ParseJSON1 {
	private static HashMap<String,String> month = null;
	
	public static String[] parseJSON(String line){
		if(line==null || line.equals(""))	return null;
		String[] result = new String[3];
		
		JsonElement elem = new JsonParser().parse(line);
		JsonObject parser = elem.getAsJsonObject();
		result[0] = parser.get("created_at").toString();
		result[0] = transformTime(result[0]);
		result[1] = parser.get("id").toString();
		String text = parser.get("text").toString();
		if(text==null || text.equals(""))
			return null;
		text = text.substring(1, text.length()-1);
		
		text = text.replaceAll("\\{", "\\\\"+"u00"+Integer.toHexString(123));
		text = text.replaceAll("\\}", "\\\\"+"u00"+Integer.toHexString(125));
		
		text = StringEscapeUtils.escapeJava(text);
		
		text = convertString(text);
		text = text.replaceAll("/", "\\\\/");
		text = text.replaceAll("[\\\\]+", "\\\\");
		
		result[2] = text;
		return result;
	}
	
	private static String transformTime(String line){
		String[] elems = line.split(" ");
		if(month==null)	initMonth();
		String mon = month.get(elems[1]);
		String result = elems[5].substring(0,4)+"-"+mon+"-"+elems[2]+"+"+elems[3];
		return result;
	}
	
	private static void initMonth(){
		month = new HashMap<String,String>();
		month.put("Jan", "01");
		month.put("Feb", "02");
		month.put("Mar", "03");
		month.put("Apr", "04");
		month.put("May", "05");
		month.put("Jun", "06");
		month.put("Jul", "07");
		month.put("Aug", "08");
		month.put("Sep", "09");
		month.put("Oct", "10");
		month.put("Nov", "11");
		month.put("Dec", "12");
	}
	
	private static String convertString(String line){
		StringBuilder builder = new StringBuilder();
		int lastIndex = 0;
		int newIndex = 0;
		try{
		while(newIndex>=0){
			newIndex = line.indexOf("\\u",newIndex);
			
			if(newIndex<0){
				builder.append(line.substring(lastIndex));
				break;
			}
			builder.append(line.substring(lastIndex, newIndex));
			if((newIndex+6)<line.length()){
				builder.append("\\u"+line.substring(newIndex+2, newIndex+6).toLowerCase());
				newIndex += 6;
			}else{
				builder.append(line.substring(newIndex));
				break;
			}
			lastIndex = newIndex;
		}
		}catch(StringIndexOutOfBoundsException e){
			System.out.println("line:"+line);
			System.out.println("last:"+lastIndex);
			System.out.println("new:"+newIndex);
			return null;
		}
		return builder.toString();

	}

}
