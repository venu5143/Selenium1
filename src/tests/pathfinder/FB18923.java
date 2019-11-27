package tests.pathfinder;

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

public class FB18923 {

	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators")
			.getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment")
			.getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials")
			.getWebElementMapping();

	@Test(priority = 1)
	public void addAlternateImagesPackageintoContainerFileChecked()
			throws IOException, InterruptedException {
		res1.writeTestcase(
				"FB18923",
				"Instruction assigned Work group can be deleted and instruction still assign to deleted group.");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Data Processing");
		Thread.sleep(5000);
		objTE.clickByLink("Alternate Images");
		Thread.sleep(5000);
		objTE.clickByPartialLink("add alternate image");
		Thread.sleep(2000);
		objTE.verifyCheckboxIsNotSelected("container",
				"Checking for the Checkbox to be unchecked By Default");
		objTE.verifyElementByIDIsDisabled("containerFileName");
		objTE.verifyElementByIDIsDisabled("unitsPerContainerFile");
		objTE.enterByID("name", "QA Reg ainamee",
				"Entering the Name of Alternate Image");
		objTE.clickByID("filter_Field1_Button");
		objTE.clickByXpath("//*[@id='filter_Field1_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("filter_Operator1_Button");
		objTE.clickByXpath("//*[@id='filter_Operator1_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("filter_Value1_Button");
		objTE.clickByXpath("//*[@id='filter_Value1_Items']/table/tbody/tr[3]/td[2]");
		res1.writeResult("Selecting the Condition in Grid Row 1", "Pass", "");
		Thread.sleep(2000);
		objTE.enterByID("fileName", "QA",
				"Entering the File Name of Alternate Image");
		objTE.enterByID("unitsPerImageFile", "10",
				"Entering the 'Units Per Image File' of Alternate Image");
		Thread.sleep(2000);
		objTE.clickByID("container",
				"Click on the 'Package into Container File' Checkbox");
		objTE.verifyElementByIDIsNotDisabled("containerFileName",
				"Verifying the 'Container File Name' Field is Enabled");
		objTE.verifyElementByIDIsNotDisabled("unitsPerContainerFile",
				"Verifying the 'Units Per Container File' Field is Enabled");
		objTE.enterByID("containerFileName", "file1");
		objTE.enterByID("unitsPerContainerFile", "5");
		objTE.clickByID("save", "Saving the Alternative Image");
		Thread.sleep(10000);
	}

	@Test(priority = 2)
	public void editAlternateImagesPackageintoContainerFileChecked()
			throws IOException, InterruptedException {
		res1.writeTestcase(
				"FB18923",
				"Instruction assigned Work group can be deleted and instruction still assign to deleted group.");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Data Processing");
		Thread.sleep(5000);
		objTE.clickByLink("Alternate Images");
		Thread.sleep(5000);
		objTE.DoubleclickByXpath("//*[contains(text(),'QA Reg ainame')]", "");
		Thread.sleep(2000);
		objTE.verifyIsSelectedByID("container",
				"The Checkbox for the 'Package into Container File' is Selected");
		objTE.clickByID("container",
				"Click on the 'Package into Container File' Checkbox to uncheck");
		objTE.verifyElementByIDIsDisabled("containerFileName",
				"Verifying the 'Container File Name' Field is Disabled");
		objTE.verifyElementByIDIsDisabled("unitsPerContainerFile",
				"Verifying the 'Units Per Container File' Field is Disabled");
		objTE.clickByID("save", "Saving the Alternative Image");
		Thread.sleep(10000);
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
