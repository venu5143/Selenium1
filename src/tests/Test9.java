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

public class Test9 {
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;
	@Test
	public void instructionsScanLineProduction() throws IOException, InterruptedException {
		res1.writeTestcase(15, "Validating the instructions for Scan Line Tab under Production Module");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Production","Click on Menu Link");
		objTE.clickByLink("Print Site Allocation","Click on the Print Site Allocation Tab");
		objTE.clickByPartialLink("add allocation","Click on Add Allocation Link");
		Thread.sleep(15000);
		objTE.clickByXpath("//table[@id='filter_RowContainer']/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td[2]/img");
		objTE.clickByXpath("//table[@id='filter_RowContainer']/tbody/tr[2]/td[2]/div[2]/table/tbody/tr[3]/td[2]");
		objTE.clickByXpath("//table[@id='filter_RowContainer']/tbody/tr[2]/td[3]/table/tbody/tr/td/div/table/tbody/tr[1]/td[2]/img");
		objTE.clickByXpath("//table[@id='filter_RowContainer']/tbody/tr[2]/td[3]/table/tbody/tr/td/div[2]/table/tbody/tr[3]/td[2]");
		objTE.clickByXpath("//table[@id='filter_RowContainer']/tbody/tr[2]/td[4]/table/tbody/tr/td/div/table/tbody/tr[1]/td[2]/img");
		objTE.clickByXpath("//table[@id='filter_RowContainer']/tbody/tr[2]/td[4]/table/tbody/tr/td/div[2]/table/tbody/tr[3]/td[2]");
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
		
		objTE.clickByXpath("//table[@id='filter_RowContainer']/tbody/tr[3]/td[2]/div/table/tbody/tr[1]/td[2]/img");
		objTE.clickByXpath("//table[@id='filter_RowContainer']/tbody/tr[3]/td[2]/div[2]/table/tbody/tr[3]/td[2]");
		objTE.clickByXpath("//table[@id='filter_RowContainer']/tbody/tr[3]/td[3]/table/tbody/tr/td/div/table/tbody/tr[1]/td[2]/img");
		objTE.clickByXpath("//table[@id='filter_RowContainer']/tbody/tr[3]/td[3]/table/tbody/tr/td/div[2]/table/tbody/tr[3]/td[2]");
		objTE.clickByXpath("//table[@id='filter_RowContainer']/tbody/tr[3]/td[4]/table/tbody/tr/td/div/table/tbody/tr[1]/td[2]/img");
		objTE.clickByXpath("//table[@id='filter_RowContainer']/tbody/tr[3]/td[4]/table/tbody/tr/td/div[2]/table/tbody/tr[3]/td[2]");
		res1.writeResult("Validate Adding of New Condition", "Pass", "");
		objTE.clickByLink("Cancel","Click on the Cancel Button");
		
		
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
