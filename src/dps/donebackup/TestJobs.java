package dps.donebackup;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class TestJobs{
	TestEngine objTE;
	ReportUtility res1;
	String strDate;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	String run_num="";
	HashMap<String, String> hm = new HashMap<String, String>();
	
	@DataProvider(name = "test")
	public Object[][] createData() {
		return new Object[][] {
				{ "MVC001","Mayil"},
//				{ "UNI045","Smoke Testing"},
//				{ "WFM310","Smoke Testing"},
//				{ "KAI050","Smoke Testing"},
//				{ "AVA004","Smoke Testing"},
//				{ "CNX020","Smoke Testing"},
//				{ "AGA100","Smoke Testing"},
//				{ "WPT001","Smoke Testing"},
//				{ "INW010","SmokeTesting"},
		};
	}
	
	@DataProvider(name = "proof")
	public Object[][] createData1() {
		return new Object[][] {
				{ "MVC001","07/04/2016"},
				{ "UNI045","07/04/2016"},
				
//				{ "ZRegTableETL", 3},
		};
	}

	
	@Test(priority=1,dataProvider = "test")
	public void scriptTestNValidate(String appCode,String lib) throws IOException, InterruptedException {
		objTE.launchBrowser();
		
		
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase(appCode, "Test jobs ");
		Thread.sleep(2000);
		objTE.clickByPartialLink(appCode);
		Thread.sleep(2000);
		objTE.clickByLink("Test and Validation");
		Thread.sleep(3000);
		objTE.clickByPartialLink("run new test job");
		Thread.sleep(5000);
		objTE.enterByID("nameRun", appCode+strDate , "Entering the Value in Run Name Field ");
		objTE.enterByID("description", "Description RegRun1", "Entering the Value in Description Field ");
		objTE.clickByPartialLink("library");
		objTE.enterByID("datasetNameLib", lib, "Entering the Value in Dataset Name Text Field ");
		objTE.clickByID("$Button2");
		objTE.DoubleclickByXpath("//*[contains(text(),'"+lib+"')]", "Selecting the Data set");
//		objTE.clickByID("runSnapshot", "Click on the Run Snapshot Checkbox");
		objTE.clickByID("notifications", "Click on the Notifications Checkbox");
		Thread.sleep(3000);
		objTE.clickByID("runJob", "Click on the Run Job Button");
		System.out.println("Success"+appCode);
		objTE.verifyElementNonExistenceByID("runTestJobWin_title","Checking for the Run Test Job Window is Closed");
		
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
//		objTE.closeBrowser();
	}
	
	
	

	
	
	
	
//	@Test(priority=3,dataProvider = "proof")
//	public void runProofJob(String appCode, String dt)
//			throws InterruptedException, IOException {
//		objTE.clickByPartialLink(appCode,"Select the Application");
//		objTE.clickByLink("Real-time Content Control","Click on the Menu Link");
//		objTE.clickByLink("Proofing");
//		objTE.waitForElementByPartialLink("run new proof job");
//		objTE.clickByPartialLink("run new proof job");
//		objTE.enterByID("name", "QA Reg proof",
//				"Enter the Name of the Proofing Job");
//		objTE.clickByID("dataSet_Button",
//				"Select the Data Set for the Proofing Job");
//		objTE.clickByXpath("//div[@id='dataSet_Items']/table/tbody/tr[2]/td[2]");
//
//		objTE.enterByID("runAsDate", dt, "Enter the value in the Effective Date field");
//		objTE.clickByID("receiveNotifications",
//				"Click on the Receive Notifications");
//		objTE.clickByID("run", "Click on the Run Proofing Job");
//	}



	
	
	@BeforeClass
	public void beforeClass() throws IOException {
		objTE = new TestEngine();
		strDate=objTE.getDateName();
		res1 = new ReportUtility();
		res1.writeSTRDate(strDate);
	}

}
