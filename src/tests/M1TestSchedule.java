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

public class M1TestSchedule {
	KillProcessUtil kpu;
	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	@Test(priority=1)
	public void verifyDataAcquisitionScheduling() throws IOException, InterruptedException {
		res1.writeTestcase("DAS Module", "Verifying the Data Acquisition and Scheduling");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Overview", "Verifying the Overview Tab");
		objTE.verifyIsSelectedByXpath(eleMap.get("data.acqandschedule.default.xpath"),"Overview", "Verifying that Overview Is Selected By Default");
		objTE.verifyLink(eleMap.get("data.schedule.tab.link"), "Verifying the Schedule Tab");
		objTE.verifyLink(eleMap.get("data.businesscalendar.tab.link"), "Verifying the Business Calendar Tab");
		objTE.verifyLink(eleMap.get("data.datafiles.tab.link"), "Verifying the Data Files Tab");
		objTE.verifyLink(eleMap.get("data.indexcolumns.tab.link"), "Verifying the Index Columns Tab");
		objTE.verifyLink(eleMap.get("data.etlcolumns.tab.link"), "Verifying the ETL Columns Tab");
		objTE.verifyLink(eleMap.get("data.fields.tab.link"), "Verifying the Fields Tab");
		objTE.verifyLink(eleMap.get("data.archive.tab.link"), "Verifying the Archive Tab");
		objTE.verifyElementByXpath(eleMap.get("data.schedule.section.xpath"), "Verifying the Schedule Section");
		objTE.verifyElementByXpath(eleMap.get("data.businesscalendar.section.xpath"), "Verifying the Business Calendar Section");
		objTE.verifyElementByXpath(eleMap.get("data.datafiles.section.xpath"), "Verifying the Data Files Section");
		objTE.verifyElementByXpath(eleMap.get("data.indexcolumns.section.xpath"), "Verifying the Index Columns Section");
		objTE.verifyElementByXpath(eleMap.get("data.etlcolumns.section.xpath"), "Verifying the ETL Columns Section");
		objTE.verifyElementByXpath(eleMap.get("data.fields.section.xpath"), "Verifying the Fields Section");
		objTE.verifyElementByXpath(eleMap.get("data.archive.section.xpath"), "Verifying the Archive Section");
	}
	@Test(priority=2)
	public void validateSchedule1() throws IOException, InterruptedException {
		res1.writeTestcase("Schedule-1", "Validating the Scheduling Tab - Click on Skip Job Allowed Check box");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyCheckboxIsNotSelected("overviewSkipJobAllowed_Image", "Verify the Checkbox 'Allow Job to be Skipped if Files are not in place'");
		objTE.verifyLink("Schedule", "Verifying the Schedule Tab");
		objTE.clickByLink("Schedule");
		Thread.sleep(5000);
//		if(objTE.getCheckboxStatusByID("overviewSkipJobAllowed_Image")){
		objTE.clickByID(eleMap.get("schedule.skip.chkbox.id"), "Click on Skip Job Allowed Check box");
		objTE.clickByLink(eleMap.get("instruction.save.link.linktext"));
		Thread.sleep(2000);
		objTE.verifyTextByID(eleMap.get("common.message.id"), "Your changes have been successfully saved.", "Validating the Success message");
		boolean st=objTE.getIsSelected(eleMap.get("schedule.skip.chkbox.id"));
		objTE.clickByLink("Overview");
		Thread.sleep(5000);
		boolean st1=objTE.getIsSelected(eleMap.get("overview.skip.chkbox.id"));
		if(st==st1){
			res1.writeResult("Skip Job Allowed Checkbox is Updated in Overview", "Pass", "");
		}
		
		
	}
	

	@Test(priority=3)
	public void validateSchedule2() throws IOException, InterruptedException {
		res1.writeTestcase("Schedule-2", "Validating the Scheduling Tab - Click on Skip Job Allowed Check box");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Schedule", "Verifying the Schedule Tab");
		objTE.clickByLink("Schedule");
		Thread.sleep(5000);
		int num1 = objTE
				.getRowsInWebTableByXpath("//table[@id='standardSchedule_schedules_1_Grid']/tbody");
		objTE.clickByPartialLink("add schedule pattern");
		Thread.sleep(3000);
		objTE.verifyPartialLinkIsDisabled("add schedule pattern");
		objTE.verifyIsSelectedByID("standardSchedule_weekly", "Verify the Weekly option is Selected by Default");
		objTE.clickByID("standardSchedule_weeklyWednesday", "Clicking the Wednesday Checkbox");
		objTE.enterByID("standardSchedule_startTime", "09:00 AM", "Entering the Value in Time Field");
		objTE.enterByID("standardSchedule_startDate","08/09/2015", "Entering the Value in Start Date Field");
		objTE.clickByLink("Save");
		Thread.sleep(10000);
	int num2 = objTE
		.getRowsInWebTableByXpath("//table[@id='standardSchedule_schedules_1_Grid']/tbody");
	if(num2>num1){
			res1.writeResult("Validating the Saved Standard Schedule", "Pass", "");
		} else {
			res1.writeResult("Validating the Saved Standard Schedule", "Fail", "");
		}
	objTE.clickByLink("Save");
	}
	
	@Test(priority=5)
	public void editSchedule2() throws IOException, InterruptedException {
		res1.writeTestcase("Schedule-4", "Validating the Scheduling Tab - Click on Skip Job Allowed Check box");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Schedule", "Verifying the Schedule Tab");
		objTE.clickByLink("Schedule");
		Thread.sleep(5000);
		objTE.DoubleclickByXpath("//*[contains(text(),'Every Wednesday')]", "Selecting the Schedule added in Previous Step for Editing");
		objTE.clickByID("standardSchedule_businessDaysOnly", "Clicking on the Checkbox 'Processing Days Only'");
	objTE.clickByLink("Save");
	Thread.sleep(1000);
	objTE.clickByLink("Save");
	}
	
	@Test(priority=6)
	public void editSchedule3() throws IOException, InterruptedException {
		res1.writeTestcase("Schedule-5", "Validating the Scheduling Tab - Click on Skip Job Allowed Check box");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Schedule", "Verifying the Schedule Tab");
		objTE.clickByLink("Schedule");
		Thread.sleep(5000);
		objTE.DoubleclickByXpath("//*[contains(text(),'Every Friday')]", "Selecting the Schedule added in Previous Step for Editing");
		objTE.clickByID("shDgSchedule_businessDaysOnly", "Clicking on the Checkbox 'Processing Days Only'");
	objTE.clickByLink("Save");
	Thread.sleep(1000);
	objTE.clickByLink("Save");
	}
	
	@Test(priority=7)
	public void deleteSchedule2() throws IOException, InterruptedException {
		res1.writeTestcase("Schedule-6", "Validating the Scheduling Tab - Click on Skip Job Allowed Check box");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Schedule", "Verifying the Schedule Tab");
		objTE.clickByLink("Schedule");
		Thread.sleep(5000);
		objTE.clickByXpath("//*[contains(text(),'Every Wednesday')]", "Selecting the Schedule added in Previous Step for Editing");
		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
	Thread.sleep(1000);
	objTE.clickByLink("Save");
	}
	
	@Test(priority=8)
	public void deleteSchedule3() throws IOException, InterruptedException {
		res1.writeTestcase("Schedule-7", "Validating the Scheduling Tab - Click on Skip Job Allowed Check box");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Schedule", "Verifying the Schedule Tab");
		objTE.clickByLink("Schedule");
		Thread.sleep(5000);
		objTE.clickByXpath("//*[contains(text(),'Every Friday')]", "Selecting the Schedule added in Previous Step for Editing");
		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
	Thread.sleep(1000);
	objTE.clickByLink("Save");
	}
	@Test(priority=4)
	public void validateSchedule3() throws IOException, InterruptedException {
		res1.writeTestcase("Schedule-3", "Validating the Scheduling Tab - Click on Skip Job Allowed Check box");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Schedule", "Verifying the Schedule Tab");
		objTE.clickByLink("Schedule");
		Thread.sleep(5000);
		int num1 = objTE
				.getRowsInWebTableByXpath("//table[@id='shDgSchedule_schedules_1_Grid']/tbody");
		objTE.clickByID("shDgSchedule_addSchedule","Click on the add schedule pattern for SH & DG");
		Thread.sleep(3000);
		objTE.verifyElementByIDIsDisabled("shDgSchedule_addSchedule");
		objTE.verifyIsSelectedByID("shDgSchedule_weekly", "Verify the Weekly option is Selected by Default");
		objTE.clickByID("shDgSchedule_weeklyFriday", "Clicking the Wednesday Checkbox");
		objTE.enterByID("shDgSchedule_startTime", "09:00 AM", "Entering the Value in Time Field");
		objTE.enterByID("shDgSchedule_startDate","08/09/2015", "Entering the Value in Start Date Field");
		objTE.clickByLink("Save");
		Thread.sleep(1000);
	int num2 = objTE
		.getRowsInWebTableByXpath("//table[@id='shDgSchedule_schedules_1_Grid']/tbody");
	if(num2>num1){
			res1.writeResult("Validating the Saved shDg Schedule", "Pass", "");
		} else {
			res1.writeResult("Validating the Saved shDg Schedule", "Fail", "");
		}
	objTE.clickByLink("Save");
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
