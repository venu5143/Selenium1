package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.NoSuchElementException;
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

public class Test3 {
	EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;
	KillProcessUtil kpu;
	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	
	@Test
	public void instructionsEmailDeliverySettings() throws IOException, InterruptedException {
		res1.writeTestcase(6, "Validating the instructions under Email Tab in Delivery Settings Module");
		try {
			objTE.clickByPartialLink(m.get("appcode2").toString(),"Select the Application");
		} catch (NoSuchElementException e) {
			objTE.enterByID("searchFilter_Field", m.get("appcode2").toString());
			objTE.clickByID("searchFilter_Button");
			Thread.sleep(20000);
			objTE.clickByPartialLink(m.get("appcode2").toString());
		}
		
		objTE.clickByLink("Delivery Settings","Click on the Menu Link");
		objTE.clickByLink("Email","Click on the Email Tab");
		Thread.sleep(5000);
		objTE.clickByPartialLink("add schedule pattern","Click on the Add Schedule Pattern option");
		objTE.clickByID("eCXReportSettingSchedule_weeklyWednesday", "Click on the Wednesday option in the Day");
		objTE.enterByID("eCXReportSettingSchedule_startTime", "09:00 AM", "Enter the Start time");
		objTE.enterByID("eCXReportSettingSchedule_startDate", "02/29/2016", "Enter the Start Date");
		objTE.enterByID("eCXReportSettingSchedule_endDate", "08/08/2016", "Enter the End date");
		objTE.clickByID("eCXReportSettingSchedule_save", "Click on Save Button");
		Thread.sleep(5000);
		objTE.clickByXpath("//*[contains(text(),'Every Wednesday from')]", "Select the Schedule to be deleted");
		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
			}

	@BeforeMethod
	public void loginApp() throws InterruptedException {
		objTE.launchApp(m.get("View2_URL").toString());
		objTE.enterByID(eleMap.get("login.username.text.id"), m.get("view2_username").toString());
		objTE.enterByID(eleMap.get("login.password.text.id"), m.get("view2_pwd").toString());
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
	}

	@AfterMethod
	public void logoutApp() throws InterruptedException {
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
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

	@AfterClass
	public void afterClass() throws Exception {
		objTE.closeBrowser();
		kpu.killProcess("iexplore.exe");
	}

}
