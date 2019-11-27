package tests.pathfinder;


import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import testengine.TestEngine;
import utilities.KillProcessUtil;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class FB185341 {
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	@Test
	public void dateInstructions() throws IOException, InterruptedException {
		res1.writeTestcase("FB18534", "Validating the Date Fields for Instructions under Group Document Approval Module");
		
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Group Document Approval");
		objTE.clickByLink("Instructions");
		Thread.sleep(3000);
		objTE.clickByPartialLink("add instruction");
		Thread.sleep(5000);
		
		objTE.verifyTextByID("instructionWindow_title", "Instruction Detail");
		objTE.enterByID("name", "QAReg", "Enter the Value in the name Field");
		objTE.enterByID("startDate", "08/08/2015", "Enter the Value in the startDate Field");
		objTE.enterByID("endDate", "08/08/2016", "Enter the Value in the endDate Field");
		
		
		objTE.clickByID("filter_Field1_Button");
		objTE.clickByXpath("//*[@id='filter_Field1_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("filter_Operator1_Button");
		objTE.clickByXpath("//*[@id='filter_Operator1_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("filter_Value1_Button");
		objTE.clickByXpath("//*[@id='filter_Value1_Items']/table/tbody/tr[3]/td[2]");
		res1.writeResult("Validating Adding of New Condition", "Pass", "");
		objTE.clickByID("filter_SwitchView");
		objTE.verifyTextByID("filter_AdvancedEdit", "is not blank");
		objTE.enterByID("documentCount", "10", "Enter the value in Document Count Field");
		objTE.clickByLink("Save");
		res1.writeResult("Saving the Instruction", "Pass", "");
		objTE.verifyByXpathContainsSRC("//td[contains(text(),'QAReg')]/../td/img","active","Validating the Added instruction is Active");
		
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
