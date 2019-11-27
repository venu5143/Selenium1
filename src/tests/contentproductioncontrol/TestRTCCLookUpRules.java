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

public class TestRTCCLookUpRules {
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	@Test
	public void instructionsDateLookupRules() throws IOException, InterruptedException {
		res1.writeTestcase(34, "Validating the date and instructions for Lookup Rules Tab under Real-time Content Control Module");
		
		
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Real-time Content Control");
		objTE.clickByLink("Lookup Rules");
		Thread.sleep(10000);
		objTE.clickByPartialLink("add lookup rule set");
		Thread.sleep(5000);
		objTE.enterByID("ruleSetName", "QAReg Rule set","Entering the Value in Rule Set Name");
		
		objTE.enterByID("ruleSetStartDate", "08/08/2015", "Enter the Value in the startDate Field");
		objTE.enterByID("ruleSetEndDate", "08/08/2016", "Enter the Value in the endDate Field");
		objTE.clickByPartialLink("manage fields");
		objTE.clickByID("ruleSetFields_Field_Button");
		objTE.clickByXpath("//*[@id='ruleSetFields_Field_Items']/table/tbody/tr[2]/td[2]");
		res1.writeResult("Selecting the Drop down Value", "Pass", "");
	
		objTE.clickByLink("Add");
		Thread.sleep(10000);
	
		objTE.clickONEnterKeyboard();

		objTE.clickByID("ruleSetSave");
		res1.writeResult("Adding the Rule Set", "Pass", "");
		Thread.sleep(5000);
		objTE.clickByXpath("//*[contains(text(),'QAReg Rule set')]");
		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
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
