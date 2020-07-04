package Airtel.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class ValidateJson {
	
	static List<JSONObject> listOfJson= new ArrayList<JSONObject>();
	
	public static void getListofJson(JSONObject json, String key) {
		if(json.has(key)) {
			if(json.get(key) instanceof JSONObject) {
				listOfJson.add(json.getJSONObject(key));
			}else {
			listOfJson.add(json);
			}
		}
	}
	
	public static List<JSONObject> getKey(JSONObject json, String key) {
		
		boolean exits= json.has(key);
		if(!exits) {
			Set<String> set=json.keySet();
			Iterator<?> itr= set.iterator();
			String nextKey;
			while(itr.hasNext()) {
				nextKey= (String) itr.next();
				try {
					if(json.get(nextKey) instanceof JSONObject) {
						if(exits == false)
							getKey(json.getJSONObject(nextKey), key);
					}else if(json.get(nextKey) instanceof JSONArray) {
						JSONArray array= json.getJSONArray(nextKey);
						for(int i=0; i<array.length(); i++) {
							String jsonArrayString= array.get(i).toString();
							JSONObject innerJSON= new JSONObject(jsonArrayString);
							if(exits == false)
								getKey(innerJSON, key);
						}
						
					}
					
				}catch(Exception e) {
					
				}
			}
			
		}else {
			getListofJson(json, key);
		}
		return listOfJson;
	}
}
