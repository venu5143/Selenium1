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

public class NavTest2 {
	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	@Test(priority=1)
	public void mailSettingsVerification() throws IOException, InterruptedException {
		res1.writeTestcase(6, "Verifying the Mail Settings Page From Project Page");
		objTE.enterByID("searchFilter_Field", "UNI004");
		objTE.clickByID("searchFilter_Button");
		objTE.clickByPartialLink("UNI004");
		objTE.clickByLink("Mail Settings");
		objTE.verifyLink("Overview","Verifying the Overview Tab");
		objTE.verifyLink("USPS","Verifying the USPS Tab");
		objTE.verifyLink("Move Update","Verifying the Move Update Tab");
		objTE.verifyLink("Address Correction","Verifying the Address Correction Tab");
		objTE.verifyLink("Track It","Verifying the Track It Tab");
		objTE.verifyLink("Presort","Verifying the Presort Tab");
		objTE.verifyLink("No-Mail","Verifying the No-Mail Tab");
		objTE.verifyIsSelectedByXpath("//*[@class='tabSelected']","Overview", "Verifying that Overview Is Selected By Default");
		objTE.verifyElementByXpath("//td[contains(text(),'USPS')]","Verifying the USPS Section in the Page");
		objTE.verifyElementByXpath("//td[contains(text(),'Move Update')]","Verifying the Move Update Section in the Page");
		objTE.verifyElementByXpath("//td[contains(text(),'Address Correction')]","Verifying the Address Correction Section in the Page");
		objTE.verifyElementByXpath("//td[contains(text(),'Track It')]","Verifying the Track It Section in the Page");
		objTE.verifyElementByXpath("//td[contains(text(),'Presort')]","Verifying the Presort Section in the Page");
		objTE.verifyElementByXpath("//td[contains(text(),'No-Mail')]","Verifying the No-Mail Section in the Page");
		
	}
	@Test(priority=2)
//	@Test(dependsOnMethods="mailSettingsVerification")
	public void mailSettingsUSPS() throws IOException, InterruptedException {
		res1.writeTestcase(7, "Validating USPS Tab from Mail Settings Page");
		objTE.clickByPartialLink("UNI004");
		objTE.clickByLink("Mail Settings");
		objTE.clickByLink("USPS");
		objTE.clickByID("postageClass_Button");
		objTE.clickByXpath("//div[@id='postageClass_Items']/table/tbody/tr[3]/td[2]");
		res1.writeResult("Selecting the Value for Postage Class", "Pass", "");
		objTE.clickByID("postageType_Button");
		objTE.clickByXpath("//div[@id='postageType_Items']/table/tbody/tr[3]/td[2]");
		res1.writeResult("Selecting the Value for Postage Type", "Pass", "");
		objTE.clickByID("capsAccount");
		res1.writeResult("Selecting the Value for Caps Account", "Pass", "");
//		objTE.clickByID("mailDateFromInputData");
//		objTE.clickByID("dropDateFields_Button");
//		objTE.clickByXpath("//*[@id='dropDateFields_Items']/table/tbody/tr[3]/td[2]");
//		res1.writeResult("Selecting the Value for Drop Date Fields", "Pass", "");
		objTE.clickByID("save");
		res1.writeResult("Saving the USPS values entered", "Pass", "");
		objTE.verifyElementExistingUsingXpath("//*[contains(text(),'successfully saved')]","Verifying Message 'Your changes have been successfully saved.'");
		
	}

	
//	@Test(dependsOnMethods="mailSettingsUSPS")
	@Test(priority=3)
	public void mailSettingsMoveUpdate() throws IOException, InterruptedException {
		res1.writeTestcase(8, "Validating Move Update Tab from Mail Settings Page");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Mail Settings");
		objTE.clickByLink("Move Update");
//		int n1=objTE.getRowsInWebTableByXpath("//*[@id='specFieldsView_1_GridBody']");
		objTE.clickByID("moveUpdateSpecFields_Button");
		objTE.clickByXpath("//div[@id='moveUpdateSpecFields_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("specFieldAdd");
//		int n2=objTE.getRowsInWebTableByXpath("//*[@id='specFieldsView_1_GridBody']");
//		if(n2>n1){
//			res1.writeResult("Add Field to Move Update Specify Fields is Successful", "Pass", "");	
//		}
//		
		objTE.clickByID("useConnector","Click on Use Connector Checkbox");
		objTE.clickByID("moveUpdateSave");
		
		
		objTE.verifyElementExistingUsingXpath("//*[contains(text(),'successfully saved')]","Verifying Message 'Your changes have been successfully saved.'");
		
	}
	

//	@Test(dependsOnMethods="mailSettingsMoveUpdate")
	@Test(priority=4)
	public void mailSettingsAddressCorrectionTab() throws IOException, InterruptedException {
		res1.writeTestcase(9, "Validating Address Correction Tab from Mail Settings Page");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Mail Settings");
		objTE.clickByLink("Address Correction");
//		objTE.clickByID("moveUpdateSpecFields_Button");
//		objTE.clickByXpath("//div[@id='moveUpdateSpecFields_Items']/table/tbody/tr[5]/td[2]");
		objTE.clickByID("minor","Click on the Minor radiobutton option in General Section");
//		res1.writeResult("Selecting the Value for Move Update Specify Fields", "Pass", "");
		objTE.clickByID("identifiedInInputData","Click on the 'Identified in the input data' radiobutton option in Foreign Mail Identification Section");
		objTE.clickByLink("Save", "Click on Save Button");
		objTE.verifyElementExistingUsingXpath("//*[contains(text(),'successfully saved')]","Verifying Message 'Your changes have been successfully saved.'");
		objTE.clickByID("identifiedByAce", "Click on the 'Identified by ACE' radiobutton option in Foreign Mail Identification Section");
		objTE.clickByID("max","Click on the Maximum radiobutton option in General Section");
		objTE.clickByLink("Save", "Click on Save Button");
		objTE.verifyElementExistingUsingXpath("//*[contains(text(),'successfully saved')]","Verifying Message 'Your changes have been successfully saved.' after reverting to the Actual Setup");
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
