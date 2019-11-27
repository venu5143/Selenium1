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

public class Test7 {
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;
	@Test
	public void instructionsPrintProduction() throws IOException, InterruptedException {
		res1.writeTestcase(12, "Validating the instructions for Print Tab under Production Module");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Production","Click on Menu Link");
		objTE.clickByLink("Print","Click on the Print Tab");
		Thread.sleep(5000);
		objTE.clickByID("printFilter_Field0_Button");
		objTE.clickByXpath("//*[@id='printFilter_Field0_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("printFilter_Operator0_Button");
		objTE.clickByXpath("//*[@id='printFilter_Operator0_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("printFilter_Value0_Button");
		objTE.clickByXpath("//*[@id='printFilter_Value0_Items']/table/tbody/tr[3]/td[2]");
		res1.writeResult("Selecting the Condition in Grid Row 1", "Pass", "");
		
		objTE.clickByPartialLink("advanced view","Click on the Advanced View option");
		
		objTE.verifyTextByID("printFilter_AdvancedEdit","is not blank","Validate the Advanced View Text");
		
		objTE.clickByID("printFilter_SwitchView","Click on the Basic View option");
		
		int num1 = objTE
				.getRowsInWebTableByXpath("//*[@id='printFilter_RowContainer']");
		objTE.clickByPartialLink("add condition","Click on the Add Condition Link");
		Thread.sleep(2000);
		int num = objTE
				.getRowsInWebTableByXpath("//*[@id='printFilter_RowContainer']");

		if (num > num1) {
			res1.writeResult("Validating the Add Condition link", "Pass", "");
		} else {
			res1.writeResult("Validating the Add Condition link", "Fail", "");
		}
		
		objTE.clickByID("printFilter_Field2_Button");
		objTE.clickByXpath("//*[@id='printFilter_Field2_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("printFilter_Operator2_Button");
		objTE.clickByXpath("//*[@id='printFilter_Operator2_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("printFilter_Value2_Button");
		objTE.clickByXpath("//*[@id='printFilter_Value2_Items']/table/tbody/tr[3]/td[2]");
		res1.writeResult("Validating Adding of New Condition", "Pass", "");


		}
	
	@Test(dependsOnMethods="instructionsPrintProduction")
	public void instructionsPrintProduction2() throws IOException, InterruptedException {
		res1.writeTestcase(13, "Validating the instructions from 'add print spec' link for Print Tab under Production Module");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Production","Click on Menu Link");
		objTE.clickByLink("Print","Click on the Print Tab");
		Thread.sleep(5000);
		objTE.clickByPartialLink("add print spec","Click on the Add Print Spec Link");
		objTE.enterByID("editWindow_appCode_code", "UNI004","Enter the Appcode to be added Different Application");
		objTE.clickByID("next","Click on Next option");
		Thread.sleep(10000);
		objTE.clickByID("printFilter_Field1_Button");
		objTE.clickByXpath("//*[@id='printFilter_Field1_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("printFilter_Operator1_Button");
		objTE.clickByXpath("//*[@id='printFilter_Operator1_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("printFilter_Value1_Button");
		objTE.clickByXpath("//*[@id='printFilter_Value1_Items']/table/tbody/tr[3]/td[2]");
		res1.writeResult("Selecting the Condition in Grid Row 1", "Pass", "");
		
		objTE.clickByPartialLink("advanced view","Click on the Advanced View option");
		
		objTE.verifyTextByID("printFilter_AdvancedEdit","is not blank","Validate the Advanced View Text");
		
		objTE.clickByID("printFilter_SwitchView","Click on the Basic View option");
		
		int num1 = objTE
				.getRowsInWebTableByXpath("//*[@id='printFilter_RowContainer']");
		objTE.clickByPartialLink("add condition","Click on the Add Condition Link");
		Thread.sleep(2000);
		int num = objTE
				.getRowsInWebTableByXpath("//*[@id='printFilter_RowContainer']");

		if (num > num1) {
			res1.writeResult("Validating the Add Condition link", "Pass", "");
		} else {
			res1.writeResult("Validating the Add Condition link", "Fail", "");
		}
		
		objTE.clickByID("printFilter_Field3_Button");
		objTE.clickByXpath("//*[@id='printFilter_Field3_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("printFilter_Operator3_Button");
		objTE.clickByXpath("//*[@id='printFilter_Operator3_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("printFilter_Value3_Button");
		objTE.clickByXpath("//*[@id='printFilter_Value3_Items']/table/tbody/tr[3]/td[2]");
		res1.writeResult("Validating Adding of New Condition", "Pass", "");
		objTE.clickByLink("Cancel","Click on the Cancel Button");
//		res1.writeResult("Validating Cancel button", "Pass", "");
		
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
