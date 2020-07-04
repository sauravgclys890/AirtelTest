package apiVerifications;

import org.json.JSONObject;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import Airtel.Utils.ExtentReportListner;
import io.restassured.response.Response;

public class APIVerifications extends ExtentReportListner{

	public static void responseCodeValidation(Response res, int statuscode) {
		
		try {
			
			Assert.assertEquals(statuscode, res.getStatusCode());
			test.log(LogStatus.PASS, "Successfully validated status code "+ res.getStatusCode());
		}catch(AssertionError e) {
			test.log(LogStatus.FAIL, 
					"My expected status code is ="+ statuscode +", instead I am getting" + res.getStatusCode());
		}catch(Exception e) {
			test.log(LogStatus.FAIL, e.fillInStackTrace());
		}
	}
	
	public static void responseKeyvalidation(Response res, String key) {
		
		try {
		
		JSONObject obj= new JSONObject(res.getBody().asString());
		
		test.log(LogStatus.PASS, obj.getString(key));
		
		}catch(Exception e) {
			test.log(LogStatus.FAIL, e.fillInStackTrace());
		}
	}
	
	public static void responseTimevalidation(Response res) {
		 try {
			 Long time=res.getTime();
			 test.log(LogStatus.PASS, "API response time is "+ time);
		 }catch(Exception e) {
			 
		 }
	}
	
	public static void responseKeyValidationFromJsonObject(Response res, String key) {
		
		try {
			JSONObject obj= new JSONObject(res.getBody().asString());
			
			if(obj.has(key) && obj.get(key) != null) {
				test.log(LogStatus.PASS, "Response has key: "+ obj.get(key) + " its not null");
			}
			else {
				test.log(LogStatus.FAIL, "key is not availbe");
			}
			
		}catch(Exception e) {
			
			test.log(LogStatus.FAIL, e.fillInStackTrace());
			
		}
	}
}
