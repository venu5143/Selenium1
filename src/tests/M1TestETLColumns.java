package tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class M1TestETLColumns {
	TestEngine objTE;
	ReportUtility res1;

	@DataProvider(name = "ETLCol")
	public Object[][] createData1() {
		return new Object[][] {
				{ "ZRegDataElementETL", 1},
				{ "ZRegFormETL", 2},
//				{ "ZRegTableETL", 3},
		};
	}

	@DataProvider(name = "ETLColEdit")
	public Object[][] EditData1() {
		return new Object[][] {
				{ "ZRegDataElementETL", 1,"ZRegDataElementETLEdited"},
				{ "ZRegFormETL", 2,"ZRegFormETLEdited"},
//				{ "ZRegTableETL", 3,"ZRegTableETLEdited"},
		};
	}

	private HashMap<String, String> eleMap = new PropertyFileUtil("locators")
			.getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment")
			.getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials")
			.getWebElementMapping();

	@Test(priority = 1, dataProvider = "ETLCol")
	public void addETLColumn(String name, int opt) throws IOException, InterruptedException {
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"),
				cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("ETLColumn-" + opt,
				"Successfully Adding the ETL Column");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("ETL Columns", "Verifying the Fields Tab");
		objTE.clickByLink("ETL Columns");
		Thread.sleep(5000);
		objTE.clickByPartialLink("add etl column");
		Thread.sleep(3000);
		objTE.enterByID("name", name, "Entering the Name of the ETL Column");
		objTE.clickByID("type_Button",
				"Selecting the Option from the Type Dropdown");
		objTE.clickByXpath("//*[@id='type_Items']/table/tbody/tr["
				+ opt + "]/td[2]");
		objTE.clickByID("table_Button",
				"Selecting the Option from the Table Dropdown");
		objTE.clickByXpath("//*[@id='table_Items']/table/tbody/tr[1]/td[2]");
		objTE.clickByID("column_Button",
				"Selecting the Option from the Column Dropdown");
		objTE.clickByXpath("//*[@id='column_Items']/table/tbody/tr[1]/td[2]");
		
		Thread.sleep(2000);
		objTE.clickByID("save", "Saving the ETL");
		Thread.sleep(3000);
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}

	@Test(priority = 2, dataProvider = "ETLColEdit")
	public void editETLColumn(String name, int opt, String new_name) throws IOException, InterruptedException {
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"),
				cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		int option=opt+2;
		res1.writeTestcase("ETLColumn-" + option,
				"Successfully Editing the ETL Column Added in Previous Step");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("ETL Columns", "Verifying the Fields Tab");
		objTE.clickByLink("ETL Columns");
		Thread.sleep(5000);

		
		objTE.clickByXpath("//*[contains(text(),'View All')]",
				"Clicking on View All Link and Scrolling down to Choose the ETL Column");
		Thread.sleep(3000);
		objTE.scrollAndSelectByID("etlColumnsView");
		Thread.sleep(2000);
//		name = name.toUpperCase();
		objTE.DoubleclickByXpath("//*[contains(text(),'" + name + "')]",
				"Selecting the ETL to be Edited");
		Thread.sleep(5000);
		objTE.enterByID("name", new_name, "Entering the Name of the ETL Column");
		
		objTE.clickByID("save", "Saving the ETL");
		Thread.sleep(3000);
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}

	@Test(priority = 3, dataProvider = "ETLColEdit")
	public void deleteETLColumn(String name, int opt, String newName) throws IOException, InterruptedException {
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"),
				cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		int option=opt+4;
		res1.writeTestcase("ETLColumn-" + option,
				"Successfully Deleting the ETL Column Edited in Previous Steps");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("ETL Columns", "Verifying the Fields Tab");
		objTE.clickByLink("ETL Columns");
		Thread.sleep(5000);

		objTE.clickByXpath("//*[contains(text(),'View All')]",
				"Clicking on View All Link and Scrolling down to Choose the ETL Column");
		Thread.sleep(3000);
		objTE.scrollAndSelectByID("etlColumnsView");
		Thread.sleep(2000);
//		name = newName.toUpperCase();
		objTE.clickByXpath("//*[contains(text(),'" + newName + "')]",
				"Selecting the ETL to be Deleted");

		Thread.sleep(2000);

		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
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