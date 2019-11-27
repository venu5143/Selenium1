package tests;


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

public class Test8 {
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;
	
	@Test
	public void instructionsScanLineProduction() throws IOException, InterruptedException {
		res1.writeTestcase(14, "Validating the instructions for Scan Line Tab under Production Module");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Production","Click on Menu Link");
		objTE.clickByLink("Scan Line","Click on the Scan Line Tab");
		
		Thread.sleep(10000);
		objTE.enterByID(eleMap.get("instruction.name.text.id"), "qains", "Enter the Name for Instruction");
		objTE.enterByID(eleMap.get("instruction.startdate.text.id"), "08/08/2015", "Enter the Start Date for Instruction");
		objTE.enterByID(eleMap.get("instruction.enddate.text.id"), "08/09/2015", "Enter the End Date for Instruction");
		objTE.clickByID("filter_Field1_Button");
		objTE.clickByXpath("//*[@id='filter_Field1_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("filter_Operator1_Button");
		objTE.clickByXpath("//*[@id='filter_Operator1_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("filter_Value1_Button");
		objTE.clickByXpath("//*[@id='filter_Value1_Items']/table/tbody/tr[3]/td[2]");
		res1.writeResult("Select the Condition in Grid Row 1", "Pass", "");
		
		objTE.clickByPartialLink("advanced view","Click on the Advanced View option");
		
		objTE.verifyTextByID("filter_AdvancedEdit","is not blank","Validate the Advanced View Text");
		
		objTE.clickByID("filter_SwitchView","Click on the Basic View option");
		
		int num1 = objTE
				.getRowsInWebTableByXpath("//*[@id='filter_RowContainer']");
		objTE.clickByPartialLink("add condition","Click on the Add Condition Link");
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
		objTE.clickByLink("Save","Click on the Save Button");
		
		
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
