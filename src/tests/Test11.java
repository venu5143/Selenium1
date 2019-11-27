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

public class Test11 {
	
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;

	@Test
	public void validateBusinessCalendar1() throws IOException, InterruptedException {
		res1.writeTestcase(20, "Validating the Business Calendar Tab without Notes under Data Acquistion and Scheduling Module");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Data Acquisition and Scheduling","Click on Menu Link");
		objTE.verifyLink("Business Calendar", "Verifying the Business Calendar Tab");
		objTE.clickByLink("Business Calendar", "Click on the Business Calendar Tab");
		Thread.sleep(15000);
		int num1 = objTE
				.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
		objTE.clickByPartialLink("add event","Click on the Add Event Link");
		Thread.sleep(3000);
		objTE.enterByID("eventDate", "08/08/2015", "Enter the Event Date");
		objTE.clickByID("eventType_Button");
		objTE.clickByXpath("//div[@id='eventType_Items']/table/tbody/tr[3]/td[2]");
		
		objTE.clickONEnterKeyboard();

		Thread.sleep(10000);
	int num2 = objTE
		.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
	if(num2>num1){
			res1.writeResult("Validate the Saved Business Calendar Event", "Pass", "");
		} else {
			res1.writeResult("Validate the Saved  Business Calendar Event", "Fail", "");
		}
	
	}
	@Test
	public void validateBusinessCalendar2() throws IOException, InterruptedException {
		res1.writeTestcase(21, "Validating the Business Calendar Tab with Notes under Data Acquistion and Scheduling Module");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Data Acquisition and Scheduling","Click on Menu Link");
		objTE.verifyLink("Business Calendar", "Verifying the Business Calendar Tab");
		objTE.clickByLink("Business Calendar", "Click on the Business Calendar Tab");
		Thread.sleep(15000);
		int num1 = objTE
				.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
		objTE.clickByPartialLink("add event","Click on Add Event Link");
		Thread.sleep(3000);
		objTE.enterByID("eventDate", "09/09/2015", "Enter the Event Date");
		objTE.clickByID("eventType_Button");
		objTE.clickByXpath("//div[@id='eventType_Items']/table/tbody/tr[3]/td[2]");
		objTE.enterByID("notes", "Notes for Event", "Enter the Notes for the Event");
		objTE.clickONEnterKeyboard();

		Thread.sleep(10000);
	int num2 = objTE
		.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
	if(num2>num1){
			res1.writeResult("Validating the Saved Business Calendar Event", "Pass", "");
		} else {
			res1.writeResult("Validating the Saved  Business Calendar Event", "Fail", "");
		}
	
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
