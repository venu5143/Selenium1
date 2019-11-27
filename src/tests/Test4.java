package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.NoSuchElementException;
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

public class Test4 {
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;
	@Test
	public void instructionsAddressCorrectionMailSettings() throws IOException, InterruptedException {
		res1.writeTestcase(7, "Validating the instructions under Address Correction Tab in Mail Settings Module");
		try {
			objTE.clickByPartialLink(m.get("appcode2").toString(),"Select the Application");
		} catch (NoSuchElementException e) {
			objTE.enterByID("searchFilter_Field", m.get("appcode2").toString());
			objTE.clickByID("searchFilter_Button");
			Thread.sleep(20000);
			objTE.clickByPartialLink(m.get("appcode2").toString());
		}
		objTE.clickByLink("Mail Settings","Click on the Menu Link");
		objTE.clickByLink("Address Correction","Click on the Address Correction Link");
		Thread.sleep(5000);		
		objTE.clickByID("filter_Field1_Button");
		objTE.clickByXpath("//*[@id='filter_Field1_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("filter_Operator1_Button");
		objTE.clickByXpath("//*[@id='filter_Operator1_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("filter_Value1_Button");
		objTE.clickByXpath("//*[@id='filter_Value1_Items']/table/tbody/tr[3]/td[2]");
		res1.writeResult("Selecting the Condition in Grid Row 1", "Pass", "");
		Thread.sleep(2000);
		objTE.clickByPartialLink("advanced view","Click on the Advanced View Option");
		
		objTE.verifyTextByID("filter_AdvancedEdit","is not blank","Validating the Advanced View Text");
		
		
		objTE.clickByID("filter_SwitchView","Click on the Basic View Option");
		
		int num1 = objTE
				.getRowsInWebTableByXpath("//*[@id='filter_RowContainer']");
		objTE.clickByPartialLink("add condition","Click on the Add Condition Option");
		Thread.sleep(2000);
		int num = objTE
				.getRowsInWebTableByXpath("//*[@id='filter_RowContainer']");

		if (num > num1) {
			res1.writeResult("Validating the Add Condition link", "Pass", "");
		} else {
			res1.writeResult("Validating the Add Condition link", "Fail", "");
		}
		
		objTE.clickByID("filter_Field3_Button");
		objTE.clickByXpath("//*[@id='filter_Field3_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("filter_Operator3_Button");
		objTE.clickByXpath("//*[@id='filter_Operator3_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("filter_Value3_Button");
		objTE.clickByXpath("//*[@id='filter_Value3_Items']/table/tbody/tr[3]/td[2]");
		res1.writeResult("Validating Adding of New Condition", "Pass", "");
	
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
