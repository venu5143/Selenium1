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

public class Test5 {
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;
	
	@Test(priority=1)
	public void instructionsNoMailMailSettings() throws IOException, InterruptedException {
		res1.writeTestcase(8, "Validating the instructions under No-Mail Tab in Mail Settings Module");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Mail Settings","Click on the Menu Link");
		objTE.clickByLink("No-Mail","Click on the No Mail Tab");
		Thread.sleep(5000);

		
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
		res1.writeResult("Validating Adding of New Condition", "Pass", "");
		Thread.sleep(3000);
		objTE.clickByLink("Save","Click on the Save Button");
		Thread.sleep(10000);
	}
	
	
	@Test(priority=2)
	public void instructionsDeleteNoMailMailSettings() throws IOException, InterruptedException {
		res1.writeTestcase(9, "Deleting the instructions Saved in Previous Steps under No-Mail Tab in Mail Settings Module");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Mail Settings","Click on the Menu Link");
		objTE.clickByLink("No-Mail","Click on the No Mail Tab");
		Thread.sleep(5000);

		objTE.clickByXpath("//table[@id='filter_RowContainer']/tbody/tr[3]/td/input","Selecting Checkbox in the Grid Row");	
		objTE.clickByPartialLink("delete condition","Click on the Delete Condition Link");
		Thread.sleep(3000);
		objTE.clickByLink("Save","Click on the Save Button");
		res1.writeResult("Saving the Instruction after deleting the Grid Row", "Pass", "");
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
