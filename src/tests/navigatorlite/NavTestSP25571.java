package tests.navigatorlite;


import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import testengine.TestEngine;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class NavTestSP25571{
	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	@DataProvider(name = "positive")
	public Object[][] createData1() {
	 return new Object[][] {
	   { 1,11},
	   { 2,0},
	   { 3,-99},
	 };
	}
	
	@DataProvider(name = "negative")
	public Object[][] createData2() {
	 return new Object[][] {
	   { 1,"A1234"},
	   { 2,"99.99"},
	   { 3,"99.00"},
	   { 4,"0.99"},
	   { 5,"-99"},
	 };
	}
	
	@Test(priority=1)
	public void imageArchive3755() throws IOException, InterruptedException {
		res1.writeTestcase("JIRA#3755-1", "Verifying the textbox 'Projected monthly volume (units)' is added on Image Archive page");

		Thread.sleep(3000);
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Customer Modules");
		objTE.clickByLink("Image Archive");
		
		
		Thread.sleep(10000);
		objTE.verifyElementByID("volume", "Verifying the Edit Field 'Projected monthly volume (units)'");
		
	}
	
	@Test(priority=2,dataProvider = "positive")
	public void imageArchive3755Positive(int itr,int val) throws IOException, InterruptedException {
		res1.writeTestcase("JIRA#3755-2:"+itr, "Validating the textbox 'Projected monthly volume (units)' is added on Image Archive page");

		Thread.sleep(3000);
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Customer Modules");
		objTE.clickByLink("Image Archive");
		
		
		Thread.sleep(10000);
//		objTE.verifyElementByID("volume", "Verifying the Edit Field 'Projected monthly volume (units)'");
		if(val==-99){
			objTE.enterByID("volume", "", "Entering the Value in 'Projected monthly volume (units)' : ");
		}else{
		objTE.enterByID("volume", val+"", "Entering the Value in 'Projected monthly volume (units)' : "+val);
		}
		objTE.clickByLink("Save");
		Thread.sleep(3000);
		objTE.verifyElementExistingUsingXpath("//*[contains(text(),'successfully saved')]","Verifying Message 'Your changes have been successfully saved.'");
	}
	

	@Test(priority=3,dataProvider = "negative")
	public void imageArchive3755Negative(int itr,String val) throws IOException, InterruptedException {
		res1.writeTestcase("JIRA#3755-3:"+itr, "Validating the textbox 'Projected monthly volume (units)' is added on Image Archive page");

		Thread.sleep(3000);
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Customer Modules");
		objTE.clickByLink("Image Archive");
		
		
		Thread.sleep(10000);
//		objTE.verifyElementByID("volume", "Verifying the Edit Field 'Projected monthly volume (units)'");
		
		objTE.enterByID("volume", val, "Entering the Value in 'Projected monthly volume (units)' : "+val);
	
		objTE.clickByLink("Save");
		Thread.sleep(3000);
		objTE.verifyElementExistingUsingXpath("//*[contains(text(),'Please enter a valid whole number greater than')]","Verifying Message 'Please enter a valid whole number greater than or equal to 0 for Projected monthly volume (units).'");
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
