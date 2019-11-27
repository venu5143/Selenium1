package tests.contentproductioncontrol;


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

public class TestRTCCLibrary {
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	@Test
	public void dateLibrary() throws IOException, InterruptedException {
		res1.writeTestcase(33, "Validating the Date Fields for Library under Real-time Content Control Module");
		
		
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Real-time Content Control");
		objTE.clickByLink("Library");
		Thread.sleep(10000);
		objTE.clickByPartialLink("add message");
		Thread.sleep(5000);
		
		objTE.enterByID("messageName", "3QAReg", "Enter the Value in the name Field");
		
		objTE.clickByPartialLink("set effective dates");
		Thread.sleep(5000);
		
		objTE.enterByID("messageVersion_StartDate", "08/08/2015", "Enter the Value in the startDate Field");
		objTE.enterByID("messageVersion_EndDate", "08/08/2016", "Enter the Value in the endDate Field");
		
		objTE.clickByPartialLink("set message filter");
		Thread.sleep(5000);
		objTE.clickByID("messageFilter_Field1_Button");
		objTE.clickByXpath("//*[@id='messageFilter_Field1_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("messageFilter_Operator1_Button");
		objTE.clickByXpath("//*[@id='messageFilter_Operator1_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("messageFilter_Value1_Button");
		objTE.clickByXpath("//*[@id='messageFilter_Value1_Items']/table/tbody/tr[3]/td[2]");
		res1.writeResult("Validating Adding of New Condition", "Pass", "");
		objTE.clickByID("messageFilter_SwitchView");
		objTE.verifyTextByID("messageFilter_AdvancedEdit", "is not blank");
		
		objTE.enterByID("messageText", "Message Text For testing ", "Enter the value in Message Text TextArea Field");
		objTE.clickByLink("Save");
		res1.writeResult("Saving the Message", "Pass", "");
		objTE.clickByXpath("//*[contains(text(),'3QAReg')]");
		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
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
