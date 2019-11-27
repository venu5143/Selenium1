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

public class M2TestHouseHold {

	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators")
			.getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment")
			.getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials")
			.getWebElementMapping();

	@Test(priority = 1)
	public void householdItemHousehold() throws IOException,
			InterruptedException {
		res1.writeTestcase("Household",
				"Validating the HouseHolding Tab - add householding item link");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Processing");
		objTE.clickByLink("Householding");
		Thread.sleep(15000);
		objTE.clickByPartialLink("add householding item");
		Thread.sleep(5000);
		objTE.clickByID(eleMap.get("household.field1.btn.id"));
		objTE.clickByXpath(eleMap.get("household.field1.option.xpath"));
		objTE.clickByID(eleMap.get("household.operator1.btn.id"));
		objTE.clickByXpath(eleMap.get("household.operator1.option.xpath"));
		objTE.clickByID(eleMap.get("household.value1.btn.id"));
		objTE.clickByXpath(eleMap.get("household.value1.option.xpath"));
		res1.writeResult("Selecting the Condition in Grid Row 1", "Pass", "");

		objTE.clickByID(eleMap.get("household.switchview.link.id"));

		objTE.verifyTextByID(eleMap.get("household.advancedview.text.id"),
				"is not blank", "Validating the Advanced View Text");

		objTE.clickByID(eleMap.get("household.switchview.link.id"));

		int num1 = objTE.getRowsInWebTableByXpath(eleMap
				.get("household.condition.gridtable.xpath"));
		objTE.clickByPartialLink("add condition");
		Thread.sleep(2000);
		int num = objTE.getRowsInWebTableByXpath(eleMap
				.get("household.condition.gridtable.xpath"));

		if (num > num1) {
			res1.writeResult("Validating the Add Condition link", "Pass", "");
		} else {
			res1.writeResult("Validating the Add Condition link", "Fail", "");
		}

		res1.writeResult("Validating Adding of New Condition", "Pass", "");
		Thread.sleep(3000);
		objTE.clickByXpath("//*[@id='editWindow']/table/tbody/tr[2]/td[1]/table/tbody/tr[7]/td[1]/a[1]");
		Thread.sleep(10000);
		objTE.clickByXpath("//*[contains(text(),'is not blank')]", "Selecting the Household to be Deleted");
		objTE.clickONDeleteKeyboard();
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

	@BeforeClass
	public void beforeClass() {
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
