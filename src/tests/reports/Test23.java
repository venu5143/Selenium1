package tests.reports;


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

public class Test23 {
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	@Test
	public void dateAccountDetailTrackitReport() throws IOException, InterruptedException {
		res1.writeTestcase(11, "Validating the instructions for Reports Tab under Production Module");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Track It");
		
		Thread.sleep(10000);
		objTE.enterByID("accountDetailSearch_accountNumber", "", "Entering the Account Number");
		objTE.enterByID("accountDetailSearch_dateFrom", "06/06/2015", "Enter the Value in the Effective Date : start Date Field");
		objTE.enterByID("accountDetailSearch_dateTo", "08/08/2015", "Enter the Value in the Effective Date : End Date Field");
		
		objTE.clickByID("accountDetailSearch_Search");
	}
	@Test
	public void dateTreasuryTrackitReport() throws IOException, InterruptedException {
		res1.writeTestcase(11, "Validating the instructions for Reports Tab under Production Module");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Track It");
		Thread.sleep(10000);
		objTE.clickByLink("Treasury");
		Thread.sleep(5000);
		objTE.enterByID("search_date", "06/06/2015", "Enter the Value in the Search Date Field");
		
		objTE.clickByID("search_Search");
	}
	@Test
	public void dateOperationsTrackitReport() throws IOException, InterruptedException {
		res1.writeTestcase(11, "Validating the instructions for Reports Tab under Production Module");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Track It");
		
		Thread.sleep(10000);
		objTE.clickByLink("Operations");
		Thread.sleep(5000);

		objTE.enterByID("operationsSearch1_datesFrom", "06/06/2015", "Enter the Value in the Search From Date Field");
		objTE.enterByID("operationsSearch1_datesTo", "08/06/2015", "Enter the Value in the Search To Date Field");
		
		objTE.clickByID("operationsSearch1_Search");
		
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
