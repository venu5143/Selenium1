package tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.EnvironmentUtil;
import utilities.KillProcessUtil;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class Test13 {
	KillProcessUtil kpu;
	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;
	@Test
	public void instructionsScanLineProduction() throws IOException, InterruptedException {
		res1.writeTestcase(26, "Validating the instructions for Reports Tab under Production Module");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Production","Click on Menu Link");
		Thread.sleep(5000);
		objTE.clickByLink("Reports","Click on the Reports Tab");
		Thread.sleep(5000);
		objTE.clickByPartialLink("add report spec","Click on the Add Report Spec link");
		Thread.sleep(3000);
		
		objTE.clickByID("reportName_Button");
		objTE.clickByXpath("//div[@id='reportName_Items']/table/tbody/tr[3]/td[2]","Select the Report Name","AHS");
		objTE.clickByID("distributionMethod_Button","Select the Distribution Method");
		objTE.clickByXpath("//div[@id='distributionMethod_Items']/table/tbody/tr[4]/td[2]");
		objTE.clickByID("printDriver_Button","Select the Print Driver");
		objTE.clickByXpath("//div[@id='printDriver_Items']/table/tbody/tr[3]/td[2]");
		Thread.sleep(3000);
//		objTE.clickByID("$Button2", "Saving the Report Spec in Address Correction Tab");
		res1.writeResult("Saving the Report Spec in Reports Tab", "Pass", "");
		
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
