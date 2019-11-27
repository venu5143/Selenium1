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

public class NavTestSP25609{
	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	@Test(priority=1)
	public void imageArchiveSP25609() throws IOException, InterruptedException {
		res1.writeTestcase("SP25609", "'Ignore' IA permissions in retired applications in CIA searches");
		objTE.enterByID("searchFilter_Field", "WFM310");
		objTE.clickByID("searchFilter_Button", "Searching the Project");
		Thread.sleep(5000);
		objTE.clickByPartialLink("WFM310");
		
		objTE.clickByLink("Manage Security");
		objTE.clickByPartialLink("add user");
		
		objTE.enterByID("userName", "Archive Test", "Entering the Username");
		objTE.enterByID("login", "AI25609", "Entering the Login Name");
		objTE.enterByID("email", "xyz@transcentra.com", "Entering the Email");
		objTE.enterByID("password", "February2015!", "Entering the Password");
		objTE.enterByID("confirmPassword", "February2015!", "Entering the Forgot Password");
		objTE.enterByID("beginDate", "02/01/2015", "Entering the Login Name");
//		objTE.enterByID("endDate", "AI25609", "Entering the Login Name");
		Thread.sleep(3000);
		System.out.println("Hi");
		
		objTE.DoubleclickByXpath("//*[contains(text(),'Image Search Only')]", "Selecting the 'WFM310 Image Search Only' option");
		System.out.println(12);
		objTE.clickByLink("Add >");
		System.out.println(31);
		objTE.clickByID("userNext","Clicking on Next");
		System.out.println(1);
//		objTE.clickByXpath("//*[contains(text(),'Application Runtime')]", "Clicking on the 'Application Runtime' option");
		System.out.println(2);
		objTE.clickByLink("Finish");
		
		System.out.println(3);
		
//		objTE.launchApp(prop.get("URL"));
//		objTE.enterByID(eleMap.get("login.username.text.id"), "AI25609");
//		objTE.enterByID(eleMap.get("login.password.text.id"), "February2015!");
//		objTE.clickByID(eleMap.get("login.login.btn.id"));
//		
//		Thread.sleep(5000);
//		objTE.clickByPartialLink("WFM310");
//		
//		objTE.clickByLink("Image Archive");
//		
//		Thread.sleep(10000);
//		objTE.enterByID("searchFields_3894864", "R0T44I", "Entering the Job Number is the Search Field");
//		objTE.clickByID("searchFields_Search","Click on the Search button");
//		Thread.sleep(10000);
//		String results=objTE.getTextByID("searchLabel");
//		res1.writeResult("Search Results for the Search Criteria", "Pass", results);
//		objTE.selectMultipleByXpath("//*[contains(text(),'R0T44I')]");
//		objTE.clickByID("viewSelected", "Clicking on the View Selected Button");
//		
//		objTE.switchToWindow();
//		res1.writeResult("Switching to the Report Window", "Pass", "");
//		Thread.sleep(15000);
//		objTE.verifyTextByXpath("//*[@id='contextTable']/tbody/tr[2]/td[2]","R0T44I","Verifying the Search Results");
//		
//		objTE.closeWindow();
//		res1.writeResult("Closing the Report Window", "Pass", "");
//		objTE.switchToParentWindow();
//		res1.writeResult("Switching to Parent Window", "Pass", "");

		
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
//		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}

	@BeforeClass
	public void beforeClass() {
		objTE = new TestEngine();
		res1 = new ReportUtility();
		objTE.launchBrowser();
	}

	@AfterClass
	public void afterClass() {
//		objTE.closeBrowser();
	}

}
