package baseTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Airtel.Utils.ExtentReportListner;
import Airtel.Utils.Enviroment;
import io.restassured.RestAssured;

@Listeners(ExtentReportListner.class)
public class BaseTest extends ExtentReportListner{
	
	@BeforeTest
	public void baseTest() {
		System.out.println(Enviroment.envAndFile().get("serverUrl"));
		RestAssured.baseURI=Enviroment.envAndFile().get("serverUrl");
	}

}
