package tests.quantum;


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

public class FB17292 {
	
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
//	   { "10"},
//	   { "9"},
//	   { "8"},
//	   { "7"},
//	 };
//	}
	@Test(priority=1)
	public void addWorkGroup() throws Exception {
		objTE.launchBrowser();
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("FB17292-1", "Instruction assigned Work group can be deleted and instruction still assign to deleted group.");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Group Document Approval");
		Thread.sleep(5000);
		objTE.clickByLink("Work Groups");
		Thread.sleep(5000);
		objTE.clickByPartialLink("add work group");
		Thread.sleep(5000);
		objTE.enterByID("workGroupName", "QA REG WG");
		objTE.clickByLink("Save");
		objTE.verifyElementByXpath("//*[contains(text(),'QA REG WG')]", "Verifying the Work Group Created");
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();
		kpu.killProcess("iexplore.exe");
	}
	
	@Test(priority=2)
	public void addInstructionLinkWorkGroup() throws Exception {
		objTE.launchBrowser();
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("FB17292-2", "Adding an Instruction under Group Document Approval Module for Created WorkShop");
		
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Group Document Approval");
		objTE.clickByLink("Instructions");
		Thread.sleep(3000);
		objTE.clickByPartialLink("add instruction");
		Thread.sleep(5000);
		
		objTE.verifyTextByID("instructionWindow_title", "Instruction Detail");
		objTE.enterByID("name", "FB17292", "Enter the Value in the name Field");
		objTE.clickByID("WorkGroupB");
		Thread.sleep(500);
		objTE.clickByXpath("//*[contains(text(),'QA REG WG')]");
		
		Thread.sleep(5000);
		objTE.clickByLink("Select");
		objTE.enterByID("documentCount", "10", "Enter the value in Document Count Field");
		objTE.clickByLink("Save");
		res1.writeResult("Saving the Instruction", "Pass", "");
		objTE.verifyByXpathContainsSRC("//td[contains(text(),'FB17292')]/../td/img","active","Validating the Added instruction is Active");
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();
		//kpu.killProcess("iexplore.exe");
		
	}
	
	
	@Test(priority=3)
	public void deleteWorkGroupLinked() throws Exception {
		objTE.launchBrowser();
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("FB17292-3", "Instruction assigned Work group can be deleted and instruction still assign to deleted group.");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Group Document Approval");
		Thread.sleep(5000);
		objTE.clickByLink("Work Groups");
		Thread.sleep(5000);
		
		objTE.clickByXpath("//*[contains(text(),'QA REG WG')]");
		
		objTE.clickONDeleteKeyboard();
		//TO ADD Once we get the deployment
		objTE.verifyTextONAlertAndAccept("The Selected workgroup is in use and cannot be deleted.");
		objTE.acceptAlert();
		res1.writeResult("Deletion of the WorkGroup is Done", "Pass", "");
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();
		//kpu.killProcess("iexplore.exe");
	}
	
	@Test(priority=4)
	public void deleteInstructionLinkWorkGroup() throws Exception {
		objTE.launchBrowser();
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("FB17292-4", "Deleting an Instruction under Group Document Approval Module for Created WorkShop");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Group Document Approval");
		objTE.clickByLink("Instructions");
		Thread.sleep(3000);
		objTE.clickByXpath("//*[contains(text(),'FB17292')]");
		
//		objTE.clickByXpath("//td[contains(text(),'FB17292')]");
		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
		res1.writeResult("Deletion of the Instruction is Done", "Pass", "");
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();
		//kpu.killProcess("iexplore.exe");
	}
	
	@Test(priority=5)
	public void deleteWorkGroup() throws Exception {
		objTE.launchBrowser();
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("FB17292-5", "Instruction unlinked Work group can be deleted ");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Group Document Approval");
		Thread.sleep(5000);
		objTE.clickByLink("Work Groups");
		Thread.sleep(5000);
			objTE.clickByXpath("//*[contains(text(),'QA REG WG7')]");
		
		objTE.clickONDeleteKeyboard();
		//TO ADD Once we get the deployment
//		objTE.verifyTextONAlertAndAccept("String");
		objTE.acceptAlert();
		res1.writeResult("Deletion of the WorkGroup is Done", "Pass", "");
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();
		//kpu.killProcess("iexplore.exe");
	}
	


	@BeforeClass
	public void beforeClass() {
		objTE = new TestEngine();
		res1 = new ReportUtility();
		kpu = new KillProcessUtil();
		
	}

	

}
