package tests.oasis;


import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import testengine.TestEngine;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;
//@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class OasisTest2{
//	{
//		System.setProperty("atu.reporter.config", "atu.properties");
//	}
	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	@Test(priority=1)
	public void verifyfieldsFB17249() throws IOException, InterruptedException {
		res1.writeTestcase("FB17249", "QA-View2-Maximum length of ETL Column is editable after several switch in ETL Columns and Index Columns in Field");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Fields", "Verifying the Fields Tab");
		objTE.clickByLink("Fields");
		Thread.sleep(15000);
		objTE.DoubleclickByXpath("//*[contains(text(),'DPS ETL Table')]", "Double-click on the Element with option as 'DPS ETL Table'");
		objTE.verifyElementByIDIsDisabled("maxLength", "Verifying the Maximum Length Field is Disabled");
		objTE.clickByID("cancel", "Click on Cancel Button");
		Thread.sleep(5000);
		objTE.DoubleclickByXpath("//*[contains(text(),'DPS Index')]", "Double-click on the Element with option as 'DPS Index'");
		objTE.verifyElementByIDIsNotDisabled("maxLength", "Verifying the Maximum Length Field is Not Disabled");
		objTE.clickByID("cancel", "Click on Cancel Button");
	}
	@Test(priority=2)
	public void addfieldsFB17249() throws IOException, InterruptedException {
		res1.writeTestcase("FB17249", "QA-View2-Maximum length of ETL Column is editable after several switch in ETL Columns and Index Columns in Field");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Fields", "Verifying the Fields Tab");
		objTE.clickByLink("Fields");
		Thread.sleep(15000);
		objTE.clickByPartialLink("add field");
		Thread.sleep(2000);
		objTE.clickByID("columnList_Display");
		objTE.scrollAndSelectByID("columnList_Items");
		System.out.println(objTE.getRowsInWebTableByXpath("//div[@id='columnList_Items']/table/tbody"));
//		objTE.clickByXpath("//div[@id='columnList_Items']/table/tbody/tr[74]/td[2]", "DESC");
//		objTE.enterByID("columnList_Display","ACCOUNTING_BREAKOUT_CODE", "Selecting the Column Option");
//		Thread.sleep(2000);
//		objTE.verifyElementByIDIsNotDisabled("maxLength", "Verifying the Maximum Length Field is Not Disabled");
//		objTE.clickByID("cancel", "Click on Cancel Button");
//		Thread.sleep(5000);
//		objTE.clickByPartialLink("add field");
//		Thread.sleep(2000);
//		objTE.clickByID("columnList_Display");
//		objTE.enterByID("columnList_Display","LR DPAS Group", "Selecting the Column Option");
//		Thread.sleep(2000);
//		objTE.verifyElementByIDIsDisabled("maxLength", "Verifying the Maximum Length Field is Disabled");
//		objTE.clickByID("cancel", "Click on Cancel Button");
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
