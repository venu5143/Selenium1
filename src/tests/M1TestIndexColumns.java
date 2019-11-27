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

public class M1TestIndexColumns {
	TestEngine objTE;
	ReportUtility res1;

	@DataProvider(name = "IndexCol")
	public Object[][] createData1() {
		return new Object[][] {
				{ "ARegDateIndex", 1, "Y", "Y", "N", 0, "N", 0, "N", 0 },
				{ "ARegNumberIndex", 2, "Y", "Y", "N", 0, "Y", 12, "Y", 3 },
				{ "ARegStringIndex", 3, "Y", "Y", "Y", 8, "N", 0, "N", 0 },
				{ "ARegYNIndex", 4, "Y", "Y", "N", 0, "N", 0, "N", 0 },

		};
	}

	@DataProvider(name = "IndexColEdit")
	public Object[][] EditData1() {
		return new Object[][] {
				{ "ARegDateIndex", 1, "N", "N", "N", 0, "N", 0, "N", 0 },
				{ "ARegNumberIndex", 2, "N", "N", "N", 0, "Y", 12, "Y", 3 },
				{ "ARegStringIndex", 3, "N", "N", "Y", 8, "N", 0, "N", 0 },
				{ "ARegYNIndex", 4, "N", "N", "N", 0, "N", 0, "N", 0 },

		};
	}

	private HashMap<String, String> eleMap = new PropertyFileUtil("locators")
			.getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment")
			.getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials")
			.getWebElementMapping();

	@Test(priority = 1, dataProvider = "IndexCol")
	public void addIndexColumn(String name, int opt, String archive,
			String field, String len, int len_val, String dig, int dig_val,
			String deci, int deci_val) throws IOException, InterruptedException {
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"),
				cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("IndexColumn-" + opt,
				"Successfully Adding the Index Column");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Index Columns", "Verifying the Fields Tab");
		objTE.clickByLink("Index Columns");
		Thread.sleep(5000);
		objTE.clickByPartialLink("add index column");
		Thread.sleep(3000);
		objTE.enterByID("name", name, "Entering the Name of the Index");
		objTE.clickByID("indexColumnTypeList_Button",
				"Selecting the Option from the Type Dropdown");
		objTE.clickByXpath("//*[@id='indexColumnTypeList_Items']/table/tbody/tr["
				+ opt + "]/td[2]");
		Thread.sleep(2000);
		if (archive.equals("Y"))
			objTE.clickByID("archive", "Clicking on Checkbox of Archive");
		if (field.equals("Y"))
			objTE.clickByID("isUsableForFields",
					"Clicking on Checkbox of Fields");

		if (!len.equals("N"))
			objTE.enterByID("length", len_val + "", "Entering the Length");
		if (!dig.equals("N"))
			objTE.enterByID("digits", dig_val + "", "Entering the Digits");
		if (!deci.equals("N"))
			objTE.enterByID("decimals", deci_val + "",
					"Entering the Decimal Points");
		int a = 0;
		switch (opt) {
		case 1:
			objTE.verifyElementByIDIsDisabled("length");
			objTE.verifyElementByIDIsDisabled("digits");
			objTE.verifyElementByIDIsDisabled("decimals");
			break;
		case 2:
			objTE.verifyElementByIDIsDisabled("length");
			break;
		case 3:
			objTE.verifyElementByIDIsDisabled("digits");
			objTE.verifyElementByIDIsDisabled("decimals");
			break;
		case 4:
			objTE.verifyElementByIDIsDisabled("length");
			objTE.verifyElementByIDIsDisabled("digits");
			objTE.verifyElementByIDIsDisabled("decimals");
			break;
		}
		if (opt == 2) {
			if (dig.equals("Y"))
				a = dig_val;
			a++;
			objTE.verifyValueByID("length", a + "");
		}

		objTE.clickByID("save", "Saving the Index");
		Thread.sleep(3000);
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}

	@Test(priority = 2, dataProvider = "IndexColEdit")
	public void editIndexColumn(String name, int opt, String archive,
			String field, String len, int len_val, String dig, int dig_val,
			String deci, int deci_val) throws IOException, InterruptedException {
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"),
				cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		int option=opt+2;
		res1.writeTestcase("IndexColumn-" + option,
				"Successfully Editing the Index Column Added in Previous Step");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Index Columns", "Verifying the Fields Tab");
		objTE.clickByLink("Index Columns");
		Thread.sleep(5000);

		objTE.clickByXpath("//*[contains(text(),'View All')]",
				"Clicking on View All Link and Scrolling down to Choose the Index Column");
		Thread.sleep(3000);
		objTE.scrollAndSelectByID("indexColumnsView");
		Thread.sleep(2000);
		name = name.toUpperCase();
		objTE.DoubleclickByXpath("//*[contains(text(),'" + name + "')]",
				"Selecting the Index to be Edited");

		Thread.sleep(2000);
		if (archive.equals("N"))
			objTE.clickByID("archive", "Clicking on Checkbox of Archive");
		if (field.equals("N"))
			objTE.clickByID("isUsableForFields",
					"Clicking on Checkbox of Fields");
		objTE.clickByID("save", "Saving the Index");
		Thread.sleep(10000);
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}

	@Test(priority = 3, dataProvider = "IndexColEdit")
	public void deleteIndexColumn(String name, int opt, String archive,
			String field, String len, int len_val, String dig, int dig_val,
			String deci, int deci_val) throws IOException, InterruptedException {
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"),
				cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		int option=opt+4;
		res1.writeTestcase("IndexColumn-" + option,
				"Successfully Deleting the Index Column Edited in Previous Steps");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Index Columns", "Verifying the Fields Tab");
		objTE.clickByLink("Index Columns");
		Thread.sleep(5000);

		objTE.clickByXpath("//*[contains(text(),'View All')]",
				"Clicking on View All Link and Scrolling down to Choose the Index Column");
		Thread.sleep(3000);
		objTE.scrollAndSelectByID("indexColumnsView");
		Thread.sleep(2000);
		name = name.toUpperCase();
		objTE.clickByXpath("//*[contains(text(),'" + name + "')]",
				"Selecting the Index to be Deleted");

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