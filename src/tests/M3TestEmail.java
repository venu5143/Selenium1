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

public class M3TestEmail {
	KillProcessUtil kpu;
	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
//	@Test(priority=1)
//	public void addEmailDeliverySettings() throws IOException, InterruptedException {
//		res1.writeTestcase(6, "Validating the instructions under Email Tab in Delivery Settings Module");
//		objTE.clickByPartialLink("UNI004");
//		objTE.clickByLink("Delivery Settings");
//		objTE.clickByLink("Email");
//		objTE.waitForElementByPartialLink("add brand settings");
//		objTE.clickByPartialLink("add brand settings");
//		
//		objTE.verifyTextByID("brandSettingsWindow_title", "Add Brand Settings");
//		
//		objTE.waitForElementByID("brandFilter_Field1_Button");
//		objTE.clickByPartialLink("select brand");
//
//		objTE.enterByIDIfEnabled("brandNameSearchField", "UNI300");
//		objTE.clickByLink("Search");
//		
//		objTE.clickByXpath("//tbody[@id='brandsView_1_GridBody']/tr/td[1]","Selcting the BrandName");
//		
//		objTE.clickByID("selectBrand","Selecting the BrandName");
//		
//		objTE.clickByID("brandFilter_Field1_Button");
//		objTE.clickByXpath("//*[@id='brandFilter_Field1_Items']/table/tbody/tr[3]/td[2]");
//		objTE.clickByID("brandFilter_Operator1_Button");
//		objTE.clickByXpath("//*[@id='brandFilter_Operator1_Items']/table/tbody/tr[3]/td[2]");
//		objTE.clickByID("brandFilter_Value1_Button");
//		objTE.clickByXpath("//*[@id='brandFilter_Value1_Items']/table/tbody/tr[3]/td[2]");
//		res1.writeResult("Selecting the Condition in Grid Row 1", "Pass", "");
////		objTE.verifyTextByID("brandFilter_AdvancedEdit","Accountnumber is not blank","Validating the Advanced View Text");
//		
//		objTE.enterByID("emailFrom", "qa@test.com");
//		objTE.clickByPartialLink("select template");
//		objTE.enterByID("templateNameSearchField", "email_html");
//		objTE.clickByID("templateNameSearch","Searching the Template");
//		objTE.clickByXpath("//*[contains(text(),'email_html')]");
//		objTE.clickByID("selectTemplate", "Selecting the Template");
//		objTE.enterByID("emailSubject", "QATestReg");
//		objTE.clickByLink("Save");
//		res1.writeResult("Validating Save button", "Pass", "");
//		
//	}

	@Test(priority=2)
	public void editEmailDeliverySettings() throws IOException, InterruptedException {
		res1.writeTestcase(6, "Validating the instructions under Email Tab in Delivery Settings Module");
		objTE.clickByPartialLink("UNI004");
		objTE.clickByLink("Delivery Settings");
		objTE.clickByLink("Email");
		objTE.doubleClickByTextUsingXpath("//tbody[@id='secureEmailSettingsView_1_GridBody']","qa@test.com",3);
		objTE.waitForElementByID("emailSubject");
//		objTE.waitForElementByXpath("//*[contains(text(),'QATestReg')]");
//		objTE.DoubleclickByXpath("//tbody[@id='secureEmailSettingsView_1_GridBody']", "Opening the Brand Settings Added in Previous Steps");
		objTE.enterByID("emailSubject", "QATestRegEdited");
		objTE.clickByLink("Save");
		res1.writeResult("Validating Save button", "Pass", "");
		
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
		kpu = new KillProcessUtil();
		objTE.launchBrowser();
	}

	@AfterClass
	public void afterClass() throws Exception {
		objTE.closeBrowser();
		kpu.killProcess("iexplore.exe");
	}

}
