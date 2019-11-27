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

public class NavTestFB17678{
	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	@Test
	public void mailSettingsNoMail() throws IOException, InterruptedException {
		res1.writeTestcase("FB17678-1", "Validating Move Update Tab from Mail Settings Page");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Mail Settings");
		objTE.clickByLink("No-Mail");
		Thread.sleep(10000);
		objTE.clickByID("filter_Add", "Click on Add Condition in No-Mail Page");
		// The Steps to add condition
		objTE.clickByID("filter_Field4_Button");
		objTE.clickByXpath("//div[@id='filter_Field4_Items']/table/tbody/tr[5]/td[2]");
		objTE.clickByID("filter_Operator4_Button");
		objTE.clickByXpath("//div[@id='filter_Operator4_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("filter_Value4_Button");
		objTE.clickByXpath("//div[@id='filter_Value4_Items']/table/tbody/tr[3]/td[2]");
		
//		objTE.clickByXpath("//*[@id='filter_Field4_Button']/../preceding-sibling::input[@id='rowSelect']");
//		objTE.clickByID("filter_Delete", "Clicking the Delete Condition");
		// To click on the Checkbox "Return To Client"
		objTE.clickByID("returnToClient","Clicking the Return To Client Checkbox");
		objTE.verifyElementByIDIsDisabled("destinationAddress","Verifying the Destination address Field");
		objTE.clickByLink("Save");
		objTE.verifyElementExistingUsingXpath("//*[contains(text(),'successfully saved')]","Verifying Message 'Your changes have been successfully saved.'");
		
	}
	
	
	@Test()
	public void mailSettingsNoMail2() throws IOException, InterruptedException {
		res1.writeTestcase("FB17678-2", "Validating Move Update Tab from Mail Settings Page");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Mail Settings");
		objTE.clickByLink("No-Mail");
		Thread.sleep(10000);

		

		// To click on the Checkbox "Return To Client"
		objTE.clickByID("returnToClient","Clicking the Return To Client Checkbox");
		objTE.verifyElementByIDIsDisabled("destinationAddress","Verifying the Destination address Field");
		objTE.enterByIDIfEnabled("destinationAddress", "NA");
		objTE.clickByLink("Save");
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
