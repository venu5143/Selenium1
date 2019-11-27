package tests.quantum;


import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.KillProcessUtil;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class FB4582 {
	
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
//	   { "10"},
//	   { "9"},
//	   { "8"},
//	   { "7"},
//	 };
//	}
	
	
	@Test(priority=1)
	public void runTestJobWithSnapshot() throws Exception {
		objTE.launchBrowser();
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("FB4582", "Increase pre-RTCC rules processing performance");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Test and Validation");
		objTE.clickByPartialLink("run new test job");
		Thread.sleep(10000);
		objTE.enterByID("nameRun", "RunFB4582");
		objTE.clickByPartialLink("library");
		objTE.enterByID("datasetNameLib", "Mayil");
		Thread.sleep(5000);
		objTE.clickByXpath("//*[@id='$Panel_0']/tbody/tr[2]/td/table/tbody/tr[5]/td/table/tbody/tr/td/a");
		Thread.sleep(5000);
		objTE.DoubleclickByXpath("//*[contains(text(),'MAyil')]", "Selecting the Library for Dataset");
		objTE.verifyTextByID("testDataSet", "MAyil");
		objTE.clickByID("runSnapshot", "Clicking the RunSnapShot Checkbox");
		objTE.clickByID("notifications");
		
		objTE.clickByID("runJob","Running the Job");
		
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();
		//kpu.killProcess("iexplore.exe");
	}
	
	
	@BeforeClass
	public void beforeClass() {
		objTE = new TestEngine();
		res1 = new ReportUtility();
		kpu = new KillProcessUtil();
		
	}

	

}
