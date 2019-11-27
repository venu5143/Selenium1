package tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.KillProcessUtil;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class M2TestETL {

	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators")
			.getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment")
			.getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials")
			.getWebElementMapping();

	@Test(priority = 1)
	public void customPLSQLCodeETL() throws IOException,
			InterruptedException {
		res1.writeTestcase("ETL-1",
				"Validating the ETL Tab - Custom PL-SQL Code option");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Processing");
		objTE.waitForElementByLink("ETL");
		objTE.clickByLink("ETL");
		objTE.waitForElementByID("etlProcessor_Button");
		objTE.clickByID("etlProcessor_Button");
		objTE.clickByXpath("//div[@id='etlProcessor_Items']/table/tbody/tr[1]/td[2]","Selecting the 'Custom PL-SQL Code' option in Processor type");
		objTE.verifyElementByIDIsDisabled("etlProcessorVersion_Display", "The ETL Processor Version Dropdown is Disabled");
		objTE.waitForElementByID("save");
		objTE.clickByID("save");
		
		
	}

	@Test(priority = 2)
	public void pervasiveDataIntegratorETL() throws IOException,
			InterruptedException {
		res1.writeTestcase("ETL-2",
				"Validating the ETL Tab - Pervasive Data Integrator option");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Processing");
		objTE.waitForElementByLink("ETL");
		objTE.clickByLink("ETL");
		objTE.waitForElementByID("etlProcessor_Button");
		objTE.clickByID("etlProcessor_Button");
		objTE.clickByXpath("//div[@id='etlProcessor_Items']/table/tbody/tr[2]/td[2]","Selecting the 'Pervasive Data Integrator' option in Processor type");
		objTE.verifyElementByIDIsNotDisabled("etlProcessorVersion_Button", "The ETL Processor Version Dropdown is Enabled");
		objTE.clickByID("etlProcessorVersion_Button");
		objTE.clickByID("8.10", "Selecting the Version of the ETL Processor");
		objTE.waitForElementByID("save");
		objTE.clickByID("save");
			
	}

	@BeforeMethod
	public void loginApp() throws InterruptedException {
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"),
				cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
	}

	@BeforeClass
	public void beforeClass() {
		objTE = new TestEngine();
		res1 = new ReportUtility();
		kpu = new KillProcessUtil();
		objTE.launchBrowser();
	}
	@AfterMethod
	public void logoutApp() throws InterruptedException {
		 objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}
	@AfterClass
	public void afterClass() throws Exception {
		 objTE.closeBrowser();
		 kpu.killProcess("iexplore.exe");
	}

}
