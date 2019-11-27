package tests.contentproductioncontrol;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import testengine.TestEngine;
import utilities.EnvironmentUtil;
import utilities.KillProcessUtil;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class Test9 {
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;
	@Test
	public void dateProofingRTCC1() throws IOException, InterruptedException {
		res1.writeTestcase(35, "Validating the instructions for Reports Tab under Production Module");
		objTE.clickByPartialLink(m.get("appcode1").toString());
		objTE.clickByLink("Real-time Content Control");
		objTE.clickByLink("Proofing");
		Thread.sleep(10000);

		objTE.clickByID("searchFields_workspace_Button");
		objTE.clickByXpath("//div[@id='searchFields_workspace_Items']/table/tbody/tr[1]/td[2]");
		Thread.sleep(5000);
		
		objTE.enterByID("searchFields_runAsDateFrom", "08/08/2014", "Enter the Value in the Effective Date : start Date Field");
		objTE.enterByID("searchFields_runAsDateTo", "08/08/2015", "Enter the Value in the Effective Date : End Date Field");
		
		objTE.clickByID("searchFields_Search");
	}
	@Test
	public void dateProofingRTCC2() throws IOException, InterruptedException {
		res1.writeTestcase(36, "Validating the instructions for Reports Tab under Production Module");
		objTE.clickByPartialLink(m.get("appcode1").toString());
		objTE.clickByLink("Real-time Content Control");
		objTE.clickByLink("Proofing");
		Thread.sleep(10000);

		objTE.clickByID("searchFields_workspace_Button");
		objTE.clickByXpath("//div[@id='searchFields_workspace_Items']/table/tbody/tr[1]/td[2]");
		Thread.sleep(5000);
		
		objTE.enterByID("searchFields_addedTimeFrom", "08/08/2014", "Enter the Value in the Date Requested : start Date Field");
		objTE.enterByID("searchFields_addedTimeTo", "08/08/2015", "Enter the Value in the Date Requested : End Date Field");
		
		objTE.clickByID("searchFields_Search");

	}
	@Test
	public void dateProofingRTCC3() throws IOException, InterruptedException {
		res1.writeTestcase(37, "Validating the instructions for Reports Tab under Production Module");
		objTE.clickByPartialLink(m.get("appcode1").toString());
		objTE.clickByLink("Real-time Content Control");
		objTE.clickByLink("Proofing");
		Thread.sleep(10000);

		objTE.clickByPartialLink("run new proof job");
		
		objTE.enterByID("name", "QA Reg proof", "Entering the Name of the Proofing Job");
		objTE.clickByID("dataSet_Button", "Selecting the Data Set for the Proofing Job");
		objTE.clickByXpath("//div[@id='dataSet_Items']/table/tbody/tr[2]/td[2]");
		objTE.enterByID("runAsDate", "12/23/2016", "Enter the Value in the Run as Date Field");
		objTE.clickByID("receiveNotifications", "Clicking on the Receive Notifications");
		objTE.clickByID("run");
		res1.writeResult("Clicking on the Run Proofing Job", "Pass", "");
	
		
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
