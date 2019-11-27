package tests.contentproductioncontrol;


import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import testengine.TestEngine;
import utilities.KillProcessUtil;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class TestRTCCProofing {
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	@Test
	public void dateProofingRTCC1() throws IOException, InterruptedException {
		res1.writeTestcase("RTCC-Proofing", "Validating the instructions for Reports Tab under Production Module");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Real-time Content Control");
		objTE.clickByLink("Proofing");
//		Thread.sleep(10000);
		objTE.waitForElementByID("searchFields_workspace_Button");

		objTE.clickByID("searchFields_workspace_Button");
		objTE.clickByXpath("//div[@id='searchFields_workspace_Items']/table/tbody/tr[4]/td[2]");
//		Thread.sleep(5000);
		objTE.waitForElementByID("searchFields_addedTimeFrom");
		
		objTE.enterByID("searchFields_addedTimeFrom", "08/08/2014", "Enter the Value in the Date Requested : start Date Field");
		objTE.enterByID("searchFields_addedTimeTo", "08/08/2015", "Enter the Value in the Date Requested : End Date Field");
		
		objTE.clickByID("searchFields_Search");

	}
	@Test
	public void dateProofingRTCC3() throws IOException, InterruptedException {
		res1.writeTestcase(37, "Validating the instructions for Reports Tab under Production Module");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Real-time Content Control");
		objTE.clickByLink("Proofing");
		objTE.waitForElementByPartialLink("run new proof job");

		objTE.clickByPartialLink("run new proof job");
		
		objTE.enterByID("name", "QA Reg proof", "Entering the Name of the Proofing Job");
		objTE.clickByID("dataSet_Button", "Selecting the Data Set for the Proofing Job");
		objTE.clickByXpath("//div[@id='dataSet_Items']/table/tbody/tr[2]/td[2]");
		
		String dt=objTE.getDate();
		
		objTE.enterByID("runAsDate", dt, "Enter the Value in the Run as Date Field");
		objTE.clickByID("receiveNotifications", "Clicking on the Receive Notifications");
		objTE.clickByID("run");
		res1.writeResult("Clicking on the Run Proofing Job", "Pass", "");
	
		
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
		kpu = new KillProcessUtil();
		objTE.launchBrowser();
	}

	@AfterClass
	public void afterClass() throws Exception {
		objTE.closeBrowser();
		kpu.killProcess("iexplore.exe");
	}

}
