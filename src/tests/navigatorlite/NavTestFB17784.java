package tests.navigatorlite;


import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class NavTestFB17784{
	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	
	@Test(priority=1,enabled=false)
	public void runTestJob17784() throws IOException, InterruptedException {
		res1.writeTestcase("FB17784", "WFM311 jobs have no Container_Report if no ALT_IMGs in job - FP 112553");
		Thread.sleep(2000);
		objTE.clickByPartialLink("MVC001");
		Thread.sleep(2000);
		objTE.clickByLink("Test and Validation");
		Thread.sleep(3000);
		objTE.clickByPartialLink("run new test job");
		Thread.sleep(5000);
		objTE.enterByID("nameRun", "RegRun1", "Entering the Value in Run Name Field ");
		objTE.enterByID("description", "Description RegRun1", "Entering the Value in Description Field ");
		objTE.clickByPartialLink("library");
		objTE.enterByID("datasetNameLib", "mayil", "Entering the Value in Dataset Name Text Field ");
		objTE.clickByID("$Button2");
		objTE.DoubleclickByXpath("//*[contains(text(),'mayil')]", "Selecting the Data set");
		objTE.clickByID("runSnapshot", "Click on the Run Snapshot Checkbox");
		objTE.clickByID("notifications", "Click on the Notifications Checkbox");
		Thread.sleep(3000);
		objTE.clickByID("runJob", "Click on the Run Job Button");
		
		objTE.verifyElementNonExistenceByID("runTestJobWin_title","Checking for the Run Test Job Window is Closed");
		

	}
	


	
	@BeforeMethod
	public void loginApp() throws InterruptedException {
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
	}

	@AfterMethod
	public void logoutApp() throws InterruptedException {
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}

	@BeforeClass
	public void beforeClass() {
		objTE = new TestEngine();
		res1 = new ReportUtility();
		objTE.launchBrowser();
	}

	@AfterClass
	public void afterClass() {
		objTE.closeBrowser();
	}

}
