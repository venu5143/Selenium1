package tests.navigatorlite;


import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import testengine.TestEngine;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class NavTest3{
	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	@Test(priority=1)
	public void imageArchive() throws IOException, InterruptedException {
		res1.writeTestcase("10", "'Ignore' IA permissions in retired applications in CIA searches");
		
		Thread.sleep(5000);
		objTE.clickByPartialLink("WFM310");
		
		objTE.clickByLink("Image Archive");
		
		Thread.sleep(10000);
		objTE.enterByID("searchFields_3894864", "R0T44I", "Entering the Job Number is the Search Field");
		objTE.clickByID("searchFields_Search","Click on the Search button");
		Thread.sleep(10000);
		String results=objTE.getTextByID("searchLabel");
		res1.writeResult("Search Results for the Search Criteria", "Pass", results);
		objTE.selectMultipleByXpath("//*[contains(text(),'R0T44I')]");
		objTE.clickByID("viewSelected", "Clicking on the View Selected Button");
		
		objTE.switchToWindow();
		res1.writeResult("Switching to the Report Window", "Pass", "");
		Thread.sleep(15000);
		objTE.verifyTextByXpath("//*[@id='contextTable']/tbody/tr[2]/td[2]","R0T44I","Verifying the Search Results");
		
		objTE.closeWindow();
		res1.writeResult("Closing the Report Window", "Pass", "");
		objTE.switchToParentWindow();
		res1.writeResult("Switching to Parent Window", "Pass", "");

		
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
