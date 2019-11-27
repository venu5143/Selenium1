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

public class TestNValidation1{
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

	
	
	@Test(priority=5,dataProvider = "test")
    public void monitorJobs(String appCode,String lib) throws InterruptedException, IOException{
    	objTE.launchBrowser();
	  	objTE.launchApp("http://monitoruat.regulusgroup.net/dps_monitor/Login.jsf");
		objTE.enterByID("j_username", "admin");
		objTE.enterByID("j_password", "admin");
		objTE.clickONEnterKeyboard();
//		objTE.clickByXpath("//*[@name='submit']");
		Thread.sleep(15000);
		objTE.assignWindowFromWindowHandles();
		objTE.clickByXpath("//*[contains(@name,'jobNumber']");
		objTE.enterByXpath("//*[@name='Search:SearchForm:jobNumber']", hm.get(appCode));
		objTE.enterByXpath("//*[@name='Search:SearchForm:appCode']", appCode);
		objTE.clickByXpath("//*[@name='searchButton']");
		

		Thread.sleep(15000);
		

		

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
	public void beforeClass() {
		objTE = new TestEngine();
		strDate=objTE.getDateName();
		res1 = new ReportUtility();
	}

}
