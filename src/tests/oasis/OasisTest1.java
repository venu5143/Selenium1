package tests.oasis;


import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
//@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class OasisTest1{
	
//	{
//		System.setProperty("atu.reporter.config", "atu.properties");
//	}
	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	@Test(priority=1)
	public void verifyLogoFB18010() throws IOException, InterruptedException {
		res1.writeTestcase("FB18010", "Verify the Logo and Copyright Changes in DPS monitor.");
		objTE.verifyElementExistingUsingXpath("//*[@src='image/regulus_t.png']", "Verifying the Transcentra Logo Existence or Presence");
		objTE.verifyLocationByXpath("//*[@src='image/regulus_t.png']",31,32,"Verifying the Location of the Transcentra Logo");
		objTE.verifyTextByXpath("//*[contains(@class,'copyright padding')]", "© 2006 - 2015 TransCentra, Inc. All rights reserved.", "Verifying the Copy Rights Text displayed at the bottom of the page");
		
		
	}
	
	
	
	
	@BeforeMethod
	public void loginApp() throws InterruptedException {
		objTE.launchApp(prop.get("Monitor_URL"));
		
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
