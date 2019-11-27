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

public class Test5 {
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;
	@Test
	public void rtccDocuments() throws IOException, InterruptedException {
		res1.writeTestcase(31, "Validating the Date Fields for Inserts under Real-time Content Control Module");
		
		
		objTE.clickByPartialLink(m.get("appcode1").toString());
		objTE.clickByLink("Real-time Content Control");
//		objTE.clickByLink("Inserts");
		Thread.sleep(10000);
		objTE.DoubleclickByXpath("//td[contains(text(),'COV020 Temp1 page1')]","Selecting a Document");
		Thread.sleep(10000);
		objTE.clickByXpath("//div[contains(text(),'Zone 2')]","Selecting a Zone");
//		objTE.verifyTextByID("insertsCaution", "Caution: All updates made to this tab are immediately made in PRODUCTION !");
		Thread.sleep(3000);
		objTE.clickByXpath("//td[contains(text(),'Default')]", "Select the Default Group");
		Thread.sleep(3000);
		objTE.clickByPartialLink("add instruction");
		Thread.sleep(5000);
		
		objTE.verifyTextByID("instructionWindow_title", "Instruction Detail");
//		
		Thread.sleep(5000);
		//objTE.enterByID("name", "QAReg", "Enter the Value in the name Field");
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
		
//		objTE.enterByID("documentCount", "10", "Enter the value in Document Count Field");
		objTE.clickByLink("Save");
		res1.writeResult("Saving the Document", "Pass", "");
		
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
