package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.EnvironmentUtil;
import utilities.KillProcessUtil;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class Test2 {
	 EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators")
			.getWebElementMapping();


	@Test(priority = 1)
	public void householdItemHousehold() throws IOException,
			InterruptedException {
		res1.writeTestcase(3,
				"Validating the HouseHolding Tab - add householding item link");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Data Processing","Click on the Menu Link");
		objTE.clickByLink("Householding","Click on the Householding Tab");
		Thread.sleep(15000);
		objTE.clickByPartialLink("add householding item","Click on Add Householding Item option");
		Thread.sleep(5000);
		objTE.clickByID(eleMap.get("household.field1.btn.id"));
		objTE.clickByXpath(eleMap.get("household.field1.option.xpath"));
		objTE.clickByID(eleMap.get("household.operator1.btn.id"));
		objTE.clickByXpath(eleMap.get("household.operator1.option.xpath"));
		objTE.clickByID(eleMap.get("household.value1.btn.id"));
		objTE.clickByXpath(eleMap.get("household.value1.option.xpath"));
		res1.writeResult("Selecting the Condition in Grid Row 1", "Pass", "");

		objTE.clickByID(eleMap.get("household.switchview.link.id"),"Click on Advanced View link");

		objTE.verifyTextByID(eleMap.get("household.advancedview.text.id"),
				"is not blank", "Validating the Advanced View Text");

		objTE.clickByID(eleMap.get("household.switchview.link.id"),"Click on the Basic View Link");

		int num1 = objTE.getRowsInWebTableByXpath(eleMap
				.get("household.condition.gridtable.xpath"));
		objTE.clickByPartialLink("add condition","Click on Add Condition Link");
		Thread.sleep(2000);
		int num = objTE.getRowsInWebTableByXpath(eleMap
				.get("household.condition.gridtable.xpath"));

		if (num > num1) {
			res1.writeResult("Validating the Add Condition link", "Pass", "");
		} else {
			res1.writeResult("Validating the Add Condition link", "Fail", "");
		}

		objTE.clickByID(eleMap.get("household.field3.btn.id"));
		objTE.clickByXpath(eleMap.get("household.field3.option.xpath"));
		objTE.clickByID(eleMap.get("household.operator3.btn.id"));
		objTE.clickByXpath(eleMap.get("household.operator3.option.xpath"));
		objTE.clickByID(eleMap.get("household.value3.btn.id"));
		objTE.clickByXpath(eleMap.get("household.value3.option.xpath"));
		res1.writeResult("Validating Adding of New Condition", "Pass", "");
		Thread.sleep(3000);
		objTE.clickByXpath("//*[@id='editWindow']/table/tbody/tr[2]/td[1]/table/tbody/tr[7]/td[1]/a[1]");
		Thread.sleep(10000);
		objTE.clickByXpath("//*[contains(text(),'is not blank')]", "Select the Conditon to be Deleted");
		objTE.clickONDeleteKeyboard();
	}

	@Test(priority = 2)
	public void alternateImageAlternateImages() throws IOException,
			InterruptedException {
		res1.writeTestcase(4,
				"Validating the AlternateImages Tab - add alternate image link");

		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Data Processing","Click on the Menu Link");
		Thread.sleep(5000);
		objTE.clickByLink("Alternate Images","Click on the Alternate Images Tab");
		Thread.sleep(5000);
		objTE.clickByPartialLink("add alternate image","Click on Add Alternate Image Option");
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

		objTE.clickByID("save", "Click on the Save button for Alternative Image");
		Thread.sleep(10000);
//		objTE.verifyElementExistingUsingXpath(
//				eleMap.get("common.success.message.xpath"),
//				"Verifying Message 'Your changes have been successfully saved.'");

	}

	@Test(priority = 3)
	public void deleteAlternateImage() throws IOException, InterruptedException {
		res1.writeTestcase(
				5,
				"Alternate Images added in previous Step can be deleted");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Data Processing","Click on the Menu Link");
		Thread.sleep(5000);
		objTE.clickByLink("Alternate Images","Click on Alternate Images Tab");
		Thread.sleep(5000);
		objTE.clickByXpath("//*[contains(text(),'QA Reg ainame')]", "Select the Alternate Image to be Deleted");
		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
//		objTE.verifyElementExistingUsingXpath(
//				eleMap.get("common.success.message.xpath"),
//				"Verifying Message 'Your changes have been successfully saved.' After deleting the Alternate Image");

	}

	@BeforeMethod
	public void loginApp() throws InterruptedException {
		objTE.launchApp(m.get("View2_URL").toString());
		objTE.enterByID(eleMap.get("login.username.text.id"),
				m.get("view2_username").toString());
		objTE.enterByID(eleMap.get("login.password.text.id"), m.get("view2_pwd").toString());
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
	}
	@Parameters({ "env" })
	@BeforeClass
	public void beforeClass(String env) throws IOException {
		m=eu.getEnvironment(env);
		objTE = new TestEngine();
		res1 = new ReportUtility();
		kpu = new KillProcessUtil();
		objTE.launchBrowser();
	}
	@AfterMethod
	public void logoutApp() throws InterruptedException {
		 objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}
	@AfterClass
	public void afterClass() throws Exception {
		 objTE.closeBrowser();
		 kpu.killProcess("iexplore.exe");
	}

}
