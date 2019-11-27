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

public class Test12 {

	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;

	@Test
	public void mailSettingsMoveUpdate() throws IOException, InterruptedException {
		res1.writeTestcase(22, "Validating Move Update Tab from Mail Settings Page");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Mail Settings","Click on Menu Link");
		objTE.clickByLink("Move Update","Click on the Move Update Tab");
		objTE.clickByID("moveUpdateSpecFields_Button");
		objTE.clickByXpath("//div[@id='moveUpdateSpecFields_Items']/table/tbody/tr[5]/td[2]");
		objTE.clickByID("specFieldAdd");
		res1.writeResult("Select the Value for Move Update Specify Fields", "Pass", "");
		objTE.clickByID("useConnector","Click on Use Connector Checkbox");
		objTE.clickByID("moveUpdateSave","Click on the Save button");

		Thread.sleep(10000);
//		objTE.verifyElementExistingUsingXpath("//*[contains(text(),'successfully saved')]","Verifying Message 'Your changes have been successfully saved.'");
		
	}
	
	@Test(dependsOnMethods="mailSettingsMoveUpdate")
	public void mailSettingsMoveUpdateDateFields() throws IOException, InterruptedException {
		res1.writeTestcase(23, "Validating Move Update Tab Date Fields from Mail Settings Page");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Mail Settings","Click on Menu Link");
		objTE.clickByLink("Move Update","Click on the Move Update Tab");
		objTE.enterByID("signedDate", "08/08/2015", "Enter the Date in the Signed Date Field");
		objTE.enterByID("regulusSignedDate", "08/09/2015", "Enter the Date in the TransCentra Signed Date Field");
		objTE.clickByID("moveUpdateSave","Click on the Save button");
		Thread.sleep(3000);
		objTE.verifyElementExistingUsingXpath("//*[contains(text(),'successfully saved')]","Verifying Message 'Your changes have been successfully saved.'");
		
	}
	

	@Test(dependsOnMethods="mailSettingsMoveUpdateDateFields")
	public void mailSettingsAddressCorrectionTab() throws IOException, InterruptedException {
		res1.writeTestcase(24, "Validating Address Correction Tab for Date Fields  from Mail Settings Page");
		Thread.sleep(3000);
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Mail Settings","Click on Menu Link");
		objTE.clickByLink("Address Correction","Click on the Address Correction Tab");
		objTE.clickByPartialLink("add report spec","Click on the Add Report Spec Link");
		Thread.sleep(5000);
		objTE.clickByID("reportName_Button");
		objTE.clickByXpath("//div[@id='reportName_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("distributionMethod_Button");
		objTE.clickByXpath("//div[@id='distributionMethod_Items']/table/tbody/tr[4]/td[2]");
		objTE.clickByID("printDriver_Button");
		objTE.clickByXpath("//div[@id='printDriver_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickONEnterKeyboard();
		res1.writeResult("Save the Report Spec in Address Correction Tab", "Pass", "");

	}
	@Test(dependsOnMethods="mailSettingsAddressCorrectionTab")
	public void mailSettingsPresortTab() throws IOException, InterruptedException {
		res1.writeTestcase(25, "Validating Presort Tab for Date Fields  from Mail Settings Page");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Mail Settings","Click on Menu Link");
		objTE.clickByLink("Presort","Click on the Presort Tab");
		Thread.sleep(5000);
		
		objTE.enterByID("imbStartDate","08/09/2016", "Enter the Value in IMB Start Date Field");
		objTE.clickONEnterKeyboard();
		
		res1.writeResult("Save the IMB Start Date in Presort Tab", "Pass", "");

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
