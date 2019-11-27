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

public class M1TestBusinessCalendar {
	
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();

	@Test(priority=1)
	public void addBusinessCalendar1() throws IOException, InterruptedException {
		res1.writeTestcase("BusinessCalendar-1", "Validating the Business Calendar Tab without Notes under Data Acquistion and Scheduling Module");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Business Calendar", "Verifying the Business Calendar Tab");
		objTE.clickByLink("Business Calendar");
		Thread.sleep(5000);
		int num1 = objTE
				.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
		objTE.clickByPartialLink("add event");
		Thread.sleep(3000);
		objTE.enterByID("eventDate", "08/08/2015", "Entering the Event Date");
		objTE.clickByID("eventType_Button");
		objTE.clickByXpath("//div[@id='eventType_Items']/table/tbody/tr[3]/td[2]");
		
		objTE.clickONEnterKeyboard();

		Thread.sleep(2000);
	int num2 = objTE
		.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
	if(num2>num1){
			res1.writeResult("Validating the Saved Business Calendar Event", "Pass", "");
		} else {
			res1.writeResult("Validating the Saved  Business Calendar Event", "Fail", "");
		}
	
	objTE.clickByLink(eleMap.get("instruction.save.link.linktext"));
	Thread.sleep(2000);
	}
	
	@Test(priority=2)
	public void editBusinessCalendar1() throws IOException, InterruptedException {
		res1.writeTestcase("BusinessCalendar-2", "Validating the Business Calendar Tab without Notes under Data Acquistion and Scheduling Module");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Business Calendar", "Verifying the Business Calendar Tab");
		objTE.clickByLink("Business Calendar");
		Thread.sleep(5000);
		int num1 = objTE
				.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
		objTE.DoubleclickByXpath("//*[contains(text(),'08/08/2015')]", "Selecting the Event Added in Previous Steps");
		objTE.clickByID("eventType_Button");
		objTE.clickByXpath("//div[@id='eventType_Items']/table/tbody/tr[6]/td[2]");
		
		objTE.clickONEnterKeyboard();

		Thread.sleep(2000);
	int num2 = objTE
		.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
	if(num2==num1){
			res1.writeResult("Editing the Saved Business Calendar Event", "Pass", "");
		} else {
			res1.writeResult("Editing the Saved  Business Calendar Event", "Fail", "");
		}
	
	objTE.clickByLink(eleMap.get("instruction.save.link.linktext"));
	Thread.sleep(2000);
	
	}
	@Test(priority=3)
	public void deleteBusinessCalendar1() throws IOException, InterruptedException {
		res1.writeTestcase("BusinessCalendar-3", "Deleting the Business Calendar Tab without Notes under Data Acquistion and Scheduling Module");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Business Calendar", "Verifying the Business Calendar Tab");
		objTE.clickByLink("Business Calendar");
		Thread.sleep(5000);
		int num1 = objTE
				.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
		objTE.clickByXpath("//*[contains(text(),'08/08/2015')]", "Selecting the Event Added in Previous Steps");
		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
		
		
	int num2 = objTE
		.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
	if(num2<num1){
			res1.writeResult("Deleting the Saved Business Calendar Event", "Pass", "");
		} else {
			res1.writeResult("Deleting the Saved  Business Calendar Event", "Fail", "");
		}
	
	objTE.clickByLink(eleMap.get("instruction.save.link.linktext"));
	Thread.sleep(2000);
	
	}
	
	@Test(priority=4)
	public void addBusinessCalendar2() throws IOException, InterruptedException {
		res1.writeTestcase("BusinessCalendar-4", "Validating the Business Calendar Tab without Notes under Data Acquistion and Scheduling Module");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Business Calendar", "Verifying the Business Calendar Tab");
		objTE.clickByLink("Business Calendar");
		Thread.sleep(5000);
		int num1 = objTE
				.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
		objTE.clickByPartialLink("add event");
		Thread.sleep(3000);
		objTE.enterByID("eventDate", "09/09/2015", "Entering the Event Date");
		objTE.clickByID("eventType_Button");
		objTE.clickByXpath("//div[@id='eventType_Items']/table/tbody/tr[3]/td[2]");
		objTE.enterByID("notes", "Notes for Event", "Entering the Notes for the Event");
		objTE.clickONEnterKeyboard();

		Thread.sleep(2000);
	int num2 = objTE
		.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
	if(num2>num1){
			res1.writeResult("Validating the Saved Business Calendar Event", "Pass", "");
		} else {
			res1.writeResult("Validating the Saved  Business Calendar Event", "Fail", "");
		}
	
	objTE.clickByLink(eleMap.get("instruction.save.link.linktext"));
	Thread.sleep(2000);
	}
	
	@Test(priority=5)
	public void editBusinessCalendar2() throws IOException, InterruptedException {
		res1.writeTestcase("BusinessCalendar-5", "Validating the Business Calendar Tab without Notes under Data Acquistion and Scheduling Module");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Business Calendar", "Verifying the Business Calendar Tab");
		objTE.clickByLink("Business Calendar");
		Thread.sleep(5000);
		int num1 = objTE
				.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
		objTE.DoubleclickByXpath("//*[contains(text(),'09/09/2015')]", "Selecting the Event Added in Previous Steps");
		objTE.clickByID("eventType_Button");
		objTE.clickByXpath("//div[@id='eventType_Items']/table/tbody/tr[6]/td[2]");
		objTE.enterByID("notes", "Notes for Event Edited", "Entering the Notes for the Event");
		objTE.clickONEnterKeyboard();

		Thread.sleep(2000);
	int num2 = objTE
		.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
	if(num2==num1){
			res1.writeResult("Editing the Saved Business Calendar Event", "Pass", "");
		} else {
			res1.writeResult("Editing the Saved  Business Calendar Event", "Fail", "");
		}
	
	objTE.clickByLink(eleMap.get("instruction.save.link.linktext"));
	Thread.sleep(2000);
	
	}
	@Test(priority=6)
	public void deleteBusinessCalendar2() throws IOException, InterruptedException {
		res1.writeTestcase("BusinessCalendar-6", "Deleting the Business Calendar Tab without Notes under Data Acquistion and Scheduling Module");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Business Calendar", "Verifying the Business Calendar Tab");
		objTE.clickByLink("Business Calendar");
		Thread.sleep(5000);
		int num1 = objTE
				.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
		objTE.clickByXpath("//*[contains(text(),'09/09/2015')]", "Selecting the Event Added in Previous Steps");
		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
		
		
	int num2 = objTE
		.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
	if(num2<num1){
			res1.writeResult("Deleting the Saved Business Calendar Event", "Pass", "");
		} else {
			res1.writeResult("Deleting the Saved  Business Calendar Event", "Fail", "");
		}
	
	objTE.clickByLink(eleMap.get("instruction.save.link.linktext"));
	Thread.sleep(2000);
	
	}
	
//	@Test
//	public void validateBusinessCalendar2() throws IOException, InterruptedException {
//		res1.writeTestcase(21, "Validating the Business Calendar Tab with Notes under Data Acquistion and Scheduling Module");
//		objTE.clickByPartialLink("INW010");
//		objTE.clickByLink("Data Acquisition and Scheduling");
//		objTE.verifyLink("Business Calendar", "Verifying the Business Calendar Tab");
//		objTE.clickByLink("Business Calendar");
//		Thread.sleep(5000);
//		int num1 = objTE
//				.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
//		objTE.clickByPartialLink("add event");
//		Thread.sleep(3000);
//		objTE.enterByID("eventDate", "09/09/2015", "Entering the Event Date");
//		objTE.clickByID("eventType_Button");
//		objTE.clickByXpath("//div[@id='eventType_Items']/table/tbody/tr[3]/td[2]");
//		objTE.enterByID("notes", "Notes for Event", "Entering the Notes for the Event");
//		objTE.clickONEnterKeyboard();
//
//		Thread.sleep(2000);
//	int num2 = objTE
//		.getRowsInWebTableByXpath("//table[@id='businessCalendarGrid_1_Grid']/tbody");
//	if(num2>num1){
//			res1.writeResult("Validating the Saved Business Calendar Event", "Pass", "");
//		} else {
//			res1.writeResult("Validating the Saved  Business Calendar Event", "Fail", "");
//		}
//	
//	}
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
