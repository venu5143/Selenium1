package tests.pathfinder;


import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.KillProcessUtil;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class FB19604 {
	
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
//	@DataProvider(name = "browser")
//	public Object[][] createData1() {
//	 return new Object[][] {
//	   { "11"},
////	   { "10"},
////	   { "9"},
////	   { "8"},
////	   { "7"},
//	 };
//	}
//	
//	(priority=1,dataProvider = "browser")
	@Test(priority=1)
	public void whileAddingEmailSettings() throws Exception {
		objTE.launchBrowser();
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("FB19604-1", "There is a typo in the label shown here.  Should be Reply to, not Replay to.  Thanks for fixing this when you have a moment.");
		objTE.clickByPartialLink("UNI045");
		objTE.clickByLink("Delivery Settings");
		Thread.sleep(5000);
		objTE.clickByLink("Email");
		Thread.sleep(5000);
		objTE.clickByPartialLink("add html email settings");
		Thread.sleep(5000);
	    objTE.verifyTextByXpath("//*[@id='htmlEmailSettingsWindow']/table/tbody/tr[2]/td/table/tbody/tr[4]/td/table/tbody/tr[3]/td/label", "Reply to Address");
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();
		kpu.killProcess("iexplore.exe");
	}
	
	@Test(priority=2)
	public void whileEditingEmailSettings() throws Exception {
		objTE.launchBrowser();
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("FB19604-2", "There is a typo in the label shown here.  Should be Reply to, not Replay to.  Thanks for fixing this when you have a moment.");
		objTE.clickByPartialLink("UNI045");
		objTE.clickByLink("Delivery Settings");
		Thread.sleep(5000);
		objTE.clickByLink("Email");
		Thread.sleep(5000);
		objTE.DoubleclickByXpath("//*[contains(text(),'echo test')]", "Selecting the Existing Email Settings");
		Thread.sleep(5000);
		objTE.verifyTextByXpath("//*[@id='htmlEmailSettingsWindow']/table/tbody/tr[2]/td/table/tbody/tr[4]/td/table/tbody/tr[3]/td/label", "Reply to Address");
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();
		kpu.killProcess("iexplore.exe");
	}
	
	

	@BeforeClass
	public void beforeClass() {
		objTE = new TestEngine();
		res1 = new ReportUtility();
		kpu = new KillProcessUtil();
	}

	

}
