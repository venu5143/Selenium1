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

public class Test10 {
	KillProcessUtil kpu;
	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;
	@Test(priority=1)
	public void verifyDataAcquisitionScheduling() throws IOException, InterruptedException {
		res1.writeTestcase(16, "Verifying the Data Acquisition and Scheduling");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Data Acquisition and Scheduling","Click on Menu Link");
		objTE.verifyLink("Overview", "Verifying the Overview Tab");
		objTE.verifyIsSelectedByXpath("//*[@class='tabSelected']","Overview", "Verifying that Overview Is Selected By Default");
		objTE.verifyLink("Schedule", "Verifying the Schedule Tab");
		objTE.verifyLink("Business Calendar", "Verifying the Business Calendar Tab");
		objTE.verifyLink("Data Files", "Verifying the Data Files Tab");
		objTE.verifyLink("Index Columns", "Verifying the Index Columns Tab");
		objTE.verifyLink("ETL Columns", "Verifying the ETL Columns Tab");
		objTE.verifyLink("Fields", "Verifying the Fields Tab");
		objTE.verifyLink("Archive", "Verifying the Archive Tab");
		objTE.verifyElementByXpath("//td[contains(text(),'Schedule')]", "Verifying the Schedule Section");
		objTE.verifyElementByXpath("//td[contains(text(),'Business Calendar')]", "Verifying the Business Calendar Section");
		objTE.verifyElementByXpath("//td[contains(text(),'Data Files')]", "Verifying the Data Files Section");
		objTE.verifyElementByXpath("//td[contains(text(),'Index Columns')]", "Verifying the Index Columns Section");
		objTE.verifyElementByXpath("//td[contains(text(),'ETL Columns')]", "Verifying the ETL Columns Section");
		objTE.verifyElementByXpath("//td[contains(text(),'Fields')]", "Verifying the Fields Section");
//		objTE.verifyElementByXpath("//td[contains(text(),'Archive')]", "Verifying the Archive Section");
	}
	@Test(priority=2)
	public void validateSchedule1() throws IOException, InterruptedException {
		res1.writeTestcase(17, "Validating the Scheduling Tab - Click on Skip Job Allowed Check box");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Data Acquisition and Scheduling","Click on Menu Link");
		objTE.verifyLink("Schedule", "Verifying the Schedule Tab");
		objTE.clickByLink("Schedule", "Click on the Schedule Tab");
		Thread.sleep(5000);
		objTE.clickByID("skipJobAllowed", "Click on Skip Job Allowed Check box");
		objTE.clickByLink("Save","Click on Save Button");
		Thread.sleep(10000);
		objTE.verifyTextByID("messagePanel", "Your changes have been successfully saved.", "Validating the Success message");
		boolean st=objTE.getIsSelected("skipJobAllowed");
		objTE.clickByLink("Overview");
		Thread.sleep(5000);
		boolean st1=objTE.getIsSelected("overviewSkipJobAllowed");
		if(st==st1){
			res1.writeResult("Skip Job Allowed Checkbox is Updated in Overview Tab as Expected", "Pass", "");
		}else{
			res1.writeResult("Skip Job Allowed Checkbox is Not Updated in Overview Tab as Expected", "Fail", "");
		}
	}
	@Test(priority=3)
	public void validateSchedule2() throws IOException, InterruptedException {
		res1.writeTestcase(18, "Validating the Scheduling Tab - Click on Skip Job Allowed Check box");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		Thread.sleep(5000);
		objTE.clickByLink("Data Acquisition and Scheduling","Click on Menu Link");
		objTE.verifyLink("Schedule", "Verifying the Schedule Tab");
		objTE.clickByLink("Schedule", "Click on the Schedule Tab");
		Thread.sleep(5000);
		int num1 = objTE
				.getRowsInWebTableByXpath("//table[@id='standardSchedule_schedules_1_Grid']/tbody");
		objTE.clickByPartialLink("add schedule pattern","Click on the Add Schedule Pattern Option");
		Thread.sleep(3000);
//		objTE.verifyPartialLinkIsDisabled("add schedule pattern");
		objTE.verifyIsSelectedByID("standardSchedule_weekly", "Verify the Weekly option is Selected by Default");
		objTE.clickByID("standardSchedule_weeklyWednesday", "Click on the Wednesday Checkbox");
		objTE.enterByID("standardSchedule_startTime", "09:00 AM", "Enter the Value in Time Field");
		objTE.enterByID("standardSchedule_startDate","08/09/2015", "Enter the Value in Start Date Field");
		objTE.clickByLink("Save","Click on the Save Button");
		Thread.sleep(10000);
	int num2 = objTE
		.getRowsInWebTableByXpath("//table[@id='standardSchedule_schedules_1_Grid']/tbody");
	if(num2>num1){
			res1.writeResult("Validate the Saved Standard Schedule", "Pass", "");
		} else {
			res1.writeResult("Validate the Saved Standard Schedule", "Fail", "");
		}
	}
	@Test(priority=4)
	public void validateSchedule3() throws IOException, InterruptedException {
		res1.writeTestcase(19, "Validating the Scheduling Tab - Click on Skip Job Allowed Check box");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Data Acquisition and Scheduling","Click on Menu Link");
		objTE.verifyLink("Schedule", "Verifying the Schedule Tab");
		objTE.clickByLink("Schedule", "Click on the Schedule Tab");
		Thread.sleep(5000);
		int num1 = objTE
				.getRowsInWebTableByXpath("//table[@id='shDgSchedule_schedules_1_Grid']/tbody");
		objTE.clickByID("shDgSchedule_addSchedule","Click on the add schedule pattern for SH & DG");
		Thread.sleep(3000);
//		objTE.verifyElementByIDIsDisabled("shDgSchedule_addSchedule");
		objTE.verifyIsSelectedByID("shDgSchedule_weekly", "Verify the Weekly option is Selected by Default");
		objTE.clickByID("shDgSchedule_weeklyWednesday", "Click the Wednesday Checkbox");
		objTE.enterByID("shDgSchedule_startTime", "09:00 AM", "Enter the Value in Time Field");
		objTE.enterByID("shDgSchedule_startDate","08/09/2015", "Enter the Value in Start Date Field");
		objTE.clickByLink("Save","Click on Save Button");
		Thread.sleep(10000);
	int num2 = objTE
		.getRowsInWebTableByXpath("//table[@id='shDgSchedule_schedules_1_Grid']/tbody");
	if(num2>num1){
			res1.writeResult("Validate the Saved shDg Schedule", "Pass", "");
		} else {
			res1.writeResult("Validate the Saved shDg Schedule", "Fail", "");
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
