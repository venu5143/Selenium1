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

public class Test6 {
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;
	
	@Test(priority=1)
	public void instructionsDocPullsRoutingCustomerModules() throws IOException, InterruptedException {
		res1.writeTestcase(10, "Validating the instructions for Doc Pulls & Routing Tab under Customer Modules");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Customer Modules","Click on Menu Link");
		objTE.clickByLink("Doc Pulls & Routing","Click on the Doc Pulls & Routing Tab");
		Thread.sleep(5000);
		objTE.clickByPartialLink("add instruction","Click on the Add Instruction Link");
		Thread.sleep(5000);
		objTE.enterByID(eleMap.get("instruction.name.text.id"), "qains", "Enter the Name for Instruction");
		objTE.enterByID(eleMap.get("instruction.startdate.text.id"), "08/08/2015", "Enter the Start Date for Instruction");
		objTE.enterByID(eleMap.get("instruction.enddate.text.id"), "08/09/2015", "Enter the End Date for Instruction");
		objTE.clickByID(eleMap.get("instruction.reason.btn.id"));
		objTE.clickByXpath(eleMap.get("instruction.reason.option.xpath"));
		objTE.clickByID(eleMap.get("docpullsroute.field1.btn.id"));
		objTE.clickByXpath(eleMap.get("docpullsroute.field1.option.xpath"));
		objTE.clickByID(eleMap.get("docpullsroute.operator1.btn.id"));
		objTE.clickByXpath(eleMap.get("docpullsroute.operator1.option.xpath"));
		objTE.clickByID(eleMap.get("docpullsroute.value1.btn.id"));
		objTE.clickByXpath(eleMap.get("docpullsroute.value1.option.xpath"));
		res1.writeResult("Selecting the Condition in Grid Row 1", "Pass", "");
		
		objTE.clickByID(eleMap.get("docpullsroute.switchview.link.id"),"Click on the Advanced View option");
		
		objTE.verifyTextByID(eleMap.get("docpullsroute.advancedview.text.id"),"is not blank","Validate the Advanced View Text");
		
		objTE.clickByID(eleMap.get("docpullsroute.switchview.link.id"),"Click on the Basic View option");
		

		

		Thread.sleep(5000);

		objTE.clickByXpath("//*[@id='editWindow']/table/tbody/tr[2]/td/table[3]/tbody/tr/td/a","Click on the Save button");
		Thread.sleep(2000);
		objTE.verifyElementByXpath("//*[contains(text(),'qains')]", "Verify the Saved instruction");


	
		}

	@Test(priority=2)
	public void instructionsDocPullsRoutingCustomerModules2() throws IOException, InterruptedException {
		res1.writeTestcase(11, "Validating the instructions in exemption filter under Docs Pulls & Routing Tab in Customer Modules");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Customer Modules","Click on Menu Link");
		objTE.clickByLink("Doc Pulls & Routing","Click on the Doc Pulls & Routing Tab");
		Thread.sleep(5000);
		objTE.clickByPartialLink("exemption filter","Click on the Exemption Filter option");
		Thread.sleep(5000);
		objTE.clickByXpath("//table[@id='exemptions_RowContainer']/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td[2]/img");
		objTE.clickByXpath("//table[@id='exemptions_RowContainer']/tbody/tr[2]/td[2]/div[2]/table/tbody/tr[3]/td[2]");
		objTE.clickByXpath("//table[@id='exemptions_RowContainer']/tbody/tr[2]/td[3]/table/tbody/tr/td/div/table/tbody/tr[1]/td[2]/img");
		objTE.clickByXpath("//table[@id='exemptions_RowContainer']/tbody/tr[2]/td[3]/table/tbody/tr/td/div[2]/table/tbody/tr[3]/td[2]");
		objTE.clickByXpath("//table[@id='exemptions_RowContainer']/tbody/tr[2]/td[4]/table/tbody/tr/td/div/table/tbody/tr[1]/td[2]/img");
		objTE.clickByXpath("//table[@id='exemptions_RowContainer']/tbody/tr[2]/td[4]/table/tbody/tr/td/div[2]/table/tbody/tr[3]/td[2]");
		res1.writeResult("Selecting the Condition in Grid Row 1", "Pass", "");
		Thread.sleep(2000);
		objTE.clickByPartialLink("advanced view","Click on the Advanced View option");
		
		objTE.verifyTextByID("exemptions_AdvancedEdit","is not blank","Validate the Advanced View Text");
		
		
		objTE.clickByID("exemptions_SwitchView","Click on the Basic View option");
		
		int num1 = objTE
				.getRowsInWebTableByXpath("//*[@id='exemptions_RowContainer']");
		objTE.clickByPartialLink("add condition","Click on the Add Condition link");
		Thread.sleep(2000);
		int num = objTE
				.getRowsInWebTableByXpath("//*[@id='exemptions_RowContainer']");

		if (num > num1) {
			res1.writeResult("Validating the Add Condition link", "Pass", "");
		} else {
			res1.writeResult("Validating the Add Condition link", "Fail", "");
		}
		
		objTE.clickByXpath("//table[@id='exemptions_RowContainer']/tbody/tr[3]/td[2]/div/table/tbody/tr[1]/td[2]/img");
		objTE.clickByXpath("//table[@id='exemptions_RowContainer']/tbody/tr[3]/td[2]/div[2]/table/tbody/tr[3]/td[2]");
		objTE.clickByXpath("//table[@id='exemptions_RowContainer']/tbody/tr[3]/td[3]/table/tbody/tr/td/div/table/tbody/tr[1]/td[2]/img");
		objTE.clickByXpath("//table[@id='exemptions_RowContainer']/tbody/tr[3]/td[3]/table/tbody/tr/td/div[2]/table/tbody/tr[3]/td[2]");
		objTE.clickByXpath("//table[@id='exemptions_RowContainer']/tbody/tr[3]/td[4]/table/tbody/tr/td/div/table/tbody/tr[1]/td[2]/img");
		objTE.clickByXpath("//table[@id='exemptions_RowContainer']/tbody/tr[3]/td[4]/table/tbody/tr/td/div[2]/table/tbody/tr[3]/td[2]");
		res1.writeResult("Validating Adding of New Condition", "Pass", "");
		objTE.clickByID("exemptionsCancel","Click on Cancel Button");
		
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
