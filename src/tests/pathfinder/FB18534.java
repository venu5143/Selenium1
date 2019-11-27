package tests.pathfinder;


import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.KillProcessUtil;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class FB18534 {
	
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
//	@DataProvider(name = "browser")
//	public Object[][] createData1() {
//	 return new Object[][] {
//	   { "11"},
////	   { "10"},
////	   { "9"},
////	   { "8"},
////	   { "7"},
//	 };
//	}
	
//	@Test(priority=1,dataProvider = "browser")
//	public void whileAddingEmailSettings(String version) throws Exception {
		@Test
		public void whileAddingEmailSettings() throws Exception {
		objTE.launchBrowser();
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("FB18534-1", "Group Document Approval --> Instructions tab.I added an instruction to test this ticket and realized that begin and end dates doesn't match what I selected.");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Group Document Approval");
		objTE.clickByLink("Instructions");
		Thread.sleep(3000);
		objTE.clickByPartialLink("add instruction");
		Thread.sleep(5000);
		objTE.verifyTextByID("instructionWindow_title", "Instruction Detail");
		objTE.enterByID("name", "QAReg1", "Enter the Value in the name Field");
		objTE.enterByID("startDate", "08/08/2015", "Enter the Value in the startDate Field");
		objTE.enterByID("endDate", "08/08/2016", "Enter the Value in the endDate Field");
//		objTE.verifyByXpathContainsText("//td[contains(text(),'QAReg1')]/../td[3]","08/08/2015","Verifying the Start Date for Instruction","QAReg1");
		objTE.enterByID("documentCount", "10", "Enter the value in Document Count Field");
		objTE.clickByLink("Save");
		res1.writeResult("Saving the Instruction", "Pass", "");
		objTE.verifyByXpathContainsSRC("//td[contains(text(),'QAReg1')]/../td/img","active","Validating the Added instruction is Active");
		objTE.verifyByXpathContainsText("//td[contains(text(),'QAReg1')]/../td[3]","08/08/2015","Verifying the Start Date for Instruction","QAReg1");
		objTE.verifyByXpathContainsText("//td[contains(text(),'QAReg1')]/../td[3]","08/08/2016","Verifying the End Date for Instruction","QAReg1");
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();
		kpu.killProcess("iexplore.exe");
	}
	
//		(priority=2,dataProvider = "browser")
	@Test
	public void whileEditingEmailSettings() throws Exception {
		objTE.launchBrowser();
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("FB18534-2", "Group Document Approval --> Instructions tab.I added an instruction to test this ticket and realized that begin and end dates doesn't match what I selected.");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Group Document Approval");
		objTE.clickByLink("Instructions");
		Thread.sleep(3000);
		objTE.clickByPartialLink("add instruction");
		Thread.sleep(5000);
		objTE.verifyTextByID("instructionWindow_title", "Instruction Detail");
		objTE.enterByID("name", "QAReg2", "Enter the Value in the name Field");
		objTE.enterByID("startDate", "08/08/2015", "Enter the Value in the startDate Field");
		objTE.enterByID("endDate", "08/08/2016", "Enter the Value in the endDate Field");
		
		objTE.enterByID("documentCount", "10", "Enter the value in Document Count Field");
		objTE.clickByLink("Save");
		res1.writeResult("Saving the Instruction", "Pass", "");
		objTE.verifyByXpathContainsSRC("//td[contains(text(),'QAReg2')]/../td/img","active","Validating the Added instruction is Active");
		objTE.verifyByXpathContainsText("//td[contains(text(),'QAReg2')]/../td[3]","08/08/2015","Verifying the Start Date for Instruction","QAReg2");
		objTE.verifyByXpathContainsText("//td[contains(text(),'QAReg2')]/../td[3]","08/08/2016","Verifying the End Date for Instruction","QAReg2");
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();
		kpu.killProcess("iexplore.exe");
	}
	
	

	@BeforeClass
	public void beforeClass() {
		objTE = new TestEngine();
		res1 = new ReportUtility();
		kpu = new KillProcessUtil();
	}

	

}
