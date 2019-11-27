package tests.navigatorlite;


import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class NavTestSP25509{
	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	
	@Test(priority=1)
	public void addTemplete2771() throws IOException, InterruptedException {
		res1.writeTestcase("JIRA#2771-1", "Verify the Include or Exclude Settings change in Customer Modules page in View2");

		Thread.sleep(3000);
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Customer Modules");
		objTE.clickByLink("Templates");
		Thread.sleep(3000);
		objTE.clickByPartialLink("add template");
		
		Thread.sleep(10000);
		objTE.enterByID("templateName", "RegTemp1", "Entering the Value in Template Name Field ");
		objTE.enterByID("description", "Description RegTemp1", "Entering the Value in Template Description Field ");
		objTE.clickByID("templateSettingsSave");
		Thread.sleep(3000);
		objTE.verifyElementExistingUsingXpath("//*[contains(text(),'successfully saved')]","Verifying Message 'Your changes have been successfully saved.'");

		
	}
	
@Test(priority=2)
	public void validateTemplete2771() throws IOException, InterruptedException {
	res1.writeTestcase("JIRA#2771-2", "Verify the Include or Exclude Settings change in Customer Modules page in View2");

	Thread.sleep(3000);
	objTE.clickByPartialLink("MVC001");
	objTE.clickByLink("Customer Modules");
	objTE.clickByLink("Templates");
	Thread.sleep(5000);
	objTE.clickByXpath("//*[contains(text(),'RegTemp1')]", "Selecting the Templete");
	Thread.sleep(5000);
	objTE.clickByPartialLink("Fill to Ounce Behavior");
	objTE.verifyIsSelectedByID("includeEligibleTemplate", "Verify Whether 'Include Eligible Templete' is selected by default");
	
	objTE.clickByID("excludeOptionalTemplate","Selecting Exclude Optional Template option");
	objTE.clickByID("saveFillToOunceBtn", "Clicking on Save in Fill To Ounce window");
	Thread.sleep(3000);
	objTE.verifyElementExistingUsingXpath("//*[contains(text(),'successfully saved')]","Verifying Message 'Your changes have been successfully saved.'");

	}
	
@Test(priority=3)
public void deleteTemplete2771() throws IOException, InterruptedException {
	res1.writeTestcase("JIRA#2771-3", "Verify the Include or Exclude Settings change in Customer Modules page in View2");

	Thread.sleep(3000);
	objTE.clickByPartialLink("MVC001");
	objTE.clickByLink("Customer Modules");
	objTE.clickByLink("Templates");
	Thread.sleep(3000);
	objTE.clickByXpath("//*[contains(text(),'RegTemp1')]", "Selecting the Templete to Delete");
	Thread.sleep(1000);
	objTE.clickByXpath("//*[contains(text(),'RegTemp1')]", "Selecting the Templete to Delete");
	Thread.sleep(3000);
	objTE.clickONDeleteKeyboard();
	objTE.acceptAlert();
	res1.writeResult("Deleting the Template created in Previous Steps", "Pass", "RegTemp1");
	Thread.sleep(3000);
	objTE.verifyElementExistingUsingXpath("//*[contains(text(),'successfully saved')]","Verifying Message 'Your changes have been successfully saved.'");

	
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
