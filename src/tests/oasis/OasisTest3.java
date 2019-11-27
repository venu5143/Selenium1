package tests.oasis;


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

public class OasisTest3{
	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	@Test
	public void noMailFB17744() throws IOException, InterruptedException {
		res1.writeTestcase("FB17744", "NAPA QA-AVA001-could not save filter in No-Mail pag3");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Mail Settings");
		objTE.clickByLink("No-Mail");
		Thread.sleep(10000);
	// The Steps to add condition
	
		boolean status=objTE.getStatusElementByIDIsDisabled("returnToClient", "Getting the status of the Checkbox for Return to Client");
		if(!status){
			objTE.clickByID("returnToClient","Clicking the Return To Client Checkbox");
			objTE.verifyElementByIDIsDisabled("destinationAddress","Verifying the Destination address Field");
		}
		
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
