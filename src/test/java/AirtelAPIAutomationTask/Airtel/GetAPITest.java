package AirtelAPIAutomationTask.Airtel;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Airtel.Utils.ValidateJson;
import Airtel.apiConfig.APIPath;
import apiVerifications.APIVerifications;
import baseTest.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAPITest extends BaseTest{
	
	@Test
	public void getAirtelAPITest() {
		
		Response response=RestAssured.given().when().get(APIPath.apipaths.AIRTEL_TEST);
		
		APIVerifications.responseCodeValidation(response, 200);
		APIVerifications.responseKeyvalidation(response, "status");
		APIVerifications.responseTimevalidation(response);
		JSONObject json= new JSONObject(response.getBody().asString());
		
		//list of character
		List<JSONObject> descriptionResult = ValidateJson.getKey(json, "description");
		for(int i=0; i<descriptionResult.size(); i++) {
			JSONObject obj= descriptionResult.get(i);
			if(!obj.get("description").toString().isEmpty()) {
				JSONObject comicJSON= obj.getJSONObject("comics");
				if(comicJSON instanceof JSONObject) {
					JSONObject comicItemJSON= comicJSON.getJSONObject("items");
					for(int j=0; j<comicItemJSON.length(); j++) {
						JSONObject nameJSON= comicItemJSON.getJSONObject(String.valueOf(j));
						System.out.println(nameJSON.get("name"));
					}
				}
			
			}
		}
		
		//list of series
		for(int i=0; i<descriptionResult.size(); i++) {
			JSONObject obj= descriptionResult.get(i);
			if(!obj.get("description").toString().isEmpty()) {
				JSONObject seriesJSON= obj.getJSONObject("series");
				if(seriesJSON instanceof JSONObject) {
					JSONObject seriesItemJSON= seriesJSON.getJSONObject("items");
					for(int j=0; j<seriesItemJSON.length(); j++) {
						JSONObject nameJSON= seriesItemJSON.getJSONObject(String.valueOf(j));
						System.out.println(nameJSON.get("name"));
					}
				}
			
			}
		}
		
		//list of stories does not involve a character with description
		
		for(int i=0; i<descriptionResult.size(); i++) {
			JSONObject obj= descriptionResult.get(i);
			if(obj.get("description").toString().isEmpty()) {
				System.out.println(obj.get("description"));
				JSONObject storiesJSON= obj.getJSONObject("stories");
				if(storiesJSON instanceof JSONObject && storiesJSON.has("items")) {
					System.out.println(storiesJSON);
					JSONObject storiesItemJSON= storiesJSON.getJSONObject("items");
					System.out.println(storiesItemJSON.length());
					for(int j=0; j<storiesItemJSON.length(); j++) {
						JSONObject nameJSON= storiesItemJSON.getJSONObject(String.valueOf(j));
						System.out.println(nameJSON.get("name"));
					}
				}
			
			}
		}
		
	}

}
