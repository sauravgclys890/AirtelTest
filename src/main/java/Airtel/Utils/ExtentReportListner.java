package Airtel.Utils;

import java.io.File;
import java.text.SimpleDateFormat;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import com.github.fge.jsonschema.core.report.LogLevel;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportListner implements ITestListener{
	
	protected static ExtentReports reports;
	protected static ExtentTest test;
	
	private static String resultpath = getResultPath();
	
	public static void deleteDirectory(File directory) {
		if(directory.exists()) {
			File[] files= directory.listFiles();
			if(files != null) {
				for(int i=0; i<files.length; i++) {
					System.out.println(files[i].getName());
					if(files[i].isDirectory())
						deleteDirectory(files[i]);
					else
						files[i].delete();
				}
			}
		}
	}
	
	private static String getResultPath() {
		deleteDirectory(new File("test-output/report/results"));
		resultpath = "results";
		if(!new File(resultpath).isDirectory()) {
			new File(resultpath);
		}
		return resultpath;	
	}
	
	String ReortLocation = "test-output/report/" + resultpath + "/";
	
	public void onTestStart(ITestResult res) {
		
		test=reports.startTest(res.getMethod().getMethodName());
		test.log(LogStatus.INFO, res.getMethod().getMethodName());
		System.out.println(res.getTestClass().getTestName());
		System.out.println(res.getMethod().getMethodName());
		
	}
	
	public void onTestSuccess(ITestResult res) {
		test.log(LogStatus.PASS, "Test is pass");
	}
	
	public void onTestFailure(ITestResult res) {
		test.log(LogStatus.FAIL, "Test is fail");
	}
	
	public void onTestSkipped(ITestResult res) {
		test.log(LogStatus.SKIP, "Test is skipped");
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult res) {
		
	}
	
	public void onStart(ITestContext context) {
		System.out.println(ReortLocation + "ReportLocation");
		reports = new ExtentReports(ReortLocation + "ExtentReport.html");
		test= reports.startTest("");
	}
	
	public void onFinish(ITestContext context) {
		
		reports.endTest(test);
		reports.flush();
		
	}

}
