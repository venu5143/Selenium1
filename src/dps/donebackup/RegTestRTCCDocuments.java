package dps.donebackup;


import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import testengine.TestEngine;
import utilities.KillProcessUtil;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class RegTestRTCCDocuments {
	TestEngine objTE;
	ReportUtility res1,res;
	KillProcessUtil kpu;
	String wsName,wsVenuDate,strDate,wsDate1;
	
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	@Test(priority=-10)
	public void rtccSelectProduction() throws IOException, InterruptedException {
		res1.writeTestcase("RTCC-Documents", "Selecting the Production Workspace");
		
		
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Real-time Content Control");
		objTE.clickByXpath("//img[contains(@src,'arrow-descending-white.gif')]","Click on the Select Icon in the Workspace");
		objTE.clickByXpath("//li[contains(text(),'List')]","Click on the List option in the Workspace menu");
		Thread.sleep(1000);
		objTE.clickByXpath("//td[contains(text(),'Production')]/preceding-sibling::td/input","Click on the Radiobutton before the 'Production' Workspace in the Workspaces Listed");
		
	}
	
	
	@Test(priority=-9)
	public void rtccAddDocumentsMandatoryCheck() throws IOException, InterruptedException {
		res1.writeTestcase("RTCC-Documents", "Validating the Documents Tab - Adding instruction with Mandatory Checked");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Real-time Content Control");
		objTE.verifyTextByID("wsTopName", "Production", "Verifying the Workspace Selected");
		objTE.waitForElementByXpath("//td[contains(text(),'COV020 Temp1 page1')]");
		objTE.DoubleclickByXpath("//td[contains(text(),'COV020 Temp1 page1')]","Selecting a Document");
		objTE.waitForElementByXpath("//div[contains(text(),'Zone 2')]");
		objTE.clickByXpath("//div[contains(text(),'Zone 2')]","Selecting a Zone");
		objTE.clickByPartialLink("add instruction");
		objTE.waitForElementByID("instructionWindow_title");
		objTE.verifyTextByID("instructionWindow_title", "Instruction Detail");
		objTE.waitForElementByID("startDate");
		objTE.enterByID("startDate", "08/08/2015", "Enter the Value in the startDate Field");
		objTE.enterByID("endDate", "08/08/2016", "Enter the Value in the endDate Field");
		objTE.clickByID("mandatory", "Selecting the Checkbox for Mandatory");
		objTE.clickByID("filter_Field1_Button");
		objTE.clickByXpath("//*[@id='filter_Field1_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("filter_Operator1_Button");
		objTE.clickByXpath("//*[@id='filter_Operator1_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("filter_Value1_Button");
		objTE.clickByXpath("//*[@id='filter_Value1_Items']/table/tbody/tr[3]/td[2]");
		res1.writeResult("Validating Adding of New Condition", "Pass", "");
		Thread.sleep(2000);
		String f=objTE.getAttributeValueByID("filter_Field1_Display");
		String o=objTE.getAttributeValueByID("filter_Operator1_Display");
		String v=objTE.getAttributeValueByID("filter_Value1_Display");
		objTE.clickByID("filter_SwitchView");
		if(o.equals("Does Not Equal "))
			o=" is not";
		if(v.equals("(blank)"))
			v=" blank";
		objTE.verifyTextByID("filter_AdvancedEdit", f+o+v,"Verifying the Advanced View Filter Against values in Basic View");
		objTE.clickByLink("Save");
		res1.writeResult("Saving the Document", "Pass", "");	
	}

	
	
	@Test(priority=-8)
	public void rtccDocumentsVerificationProd() throws IOException, InterruptedException {
		res1.writeTestcase("RTCC-Documents", "Verifications of all Instructions Added in Previous Steps");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Real-time Content Control");
		objTE.verifyTextByID("wsTopName", "Production", "Verifying the Workspace Selected");
		objTE.waitForElementByXpath("//td[contains(text(),'COV020 Temp1 page1')]");
		objTE.DoubleclickByXpath("//td[contains(text(),'COV020 Temp1 page1')]","Selecting a Document");
		objTE.waitForElementByXpath("//div[contains(text(),'Zone 2')]");
		objTE.clickByXpath("//div[contains(text(),'Zone 2')]","Selecting a Zone");
		objTE.verifyElementByXpath("//td[contains(text(),'08/08/2015')]/preceding-sibling::td/img[contains(@src,'required.gif')]", "Verifying the Required Star image For Instruction with Mandatory Checked");		
	}
	
	@Test(priority=-7)
	public void rtccAddNewWS() throws IOException, InterruptedException {
		res1.writeTestcase("RTCC-Documents", "Adding a New Workspace");
		
		
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Real-time Content Control");
		objTE.clickByXpath("//img[contains(@src,'arrow-descending-white.gif')]","Click on the Select Icon in the Workspace");
		objTE.clickByXpath("//li[contains(text(),'New')]","Click on the List option in the Workspace menu");
		wsName="QAWS"+objTE.getDateName();
		
		objTE.waitForElementByID("wsNewName");
		objTE.enterByID("wsNewName", wsName, "Entering the New WorkSpace Name");
		objTE.enterByID("wsDescription", "Desc", "Entering the Description for the New Workspace");
		objTE.clickByLink("Create");
	}
	
	@Test(priority=-6)
	public void rtccSelectVenuWS() throws IOException, InterruptedException {
		res1.writeTestcase("RTCC-Documents", "Selecting the New Workspace");
		
		
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Real-time Content Control");
		objTE.clickByXpath("//img[contains(@src,'arrow-descending-white.gif')]","Click on the Select Icon in the Workspace");
		objTE.clickByXpath("//li[contains(text(),'List')]","Click on the List option in the Workspace menu");
		Thread.sleep(1000);

		objTE.clickByXpath("//td[contains(text(),'"+wsName+"')]/preceding-sibling::td/input","Click on the Radiobutton before the 'Production' Workspace in the Workspaces Listed");
		
	}
	
	@Test(priority=-5)
	public void rtccAddDocumentsWS() throws IOException, InterruptedException {
		res1.writeTestcase("RTCC-Documents", "Validating the Documents Tab - Adding instruction without Mandatory Checked");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Real-time Content Control");
		objTE.verifyTextByID("wsTopName", wsName, "Verifying the Workspace Selected");
		objTE.waitForElementByXpath("//td[contains(text(),'COV020 Temp1 page1')]");
		objTE.DoubleclickByXpath("//td[contains(text(),'COV020 Temp1 page1')]","Selecting a Document");
		objTE.waitForElementByXpath("//div[contains(text(),'Zone 2')]");
		objTE.clickByXpath("//div[contains(text(),'Zone 2')]","Selecting a Zone");
		objTE.clickByPartialLink("add instruction");
		objTE.waitForElementByID("instructionWindow_title");
		objTE.verifyTextByID("instructionWindow_title", "Instruction Detail");
		objTE.waitForElementByID("startDate");
		objTE.enterByID("startDate", "07/07/2015", "Enter the Value in the startDate Field");
		objTE.enterByID("endDate", "08/08/2016", "Enter the Value in the endDate Field");
		objTE.clickByID("filter_Field1_Button");
		objTE.clickByXpath("//*[@id='filter_Field1_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("filter_Operator1_Button");
		objTE.clickByXpath("//*[@id='filter_Operator1_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("filter_Value1_Button");
		objTE.clickByXpath("//*[@id='filter_Value1_Items']/table/tbody/tr[3]/td[2]");
		res1.writeResult("Validating Adding of New Condition", "Pass", "");
		Thread.sleep(2000);
		String f=objTE.getAttributeValueByID("filter_Field1_Display");
		String o=objTE.getAttributeValueByID("filter_Operator1_Display");
		String v=objTE.getAttributeValueByID("filter_Value1_Display");
		objTE.clickByID("filter_SwitchView");
		if(o.equals("Does Not Equal "))
			o=" is not";
		if(v.equals("(blank)"))
			v=" blank";
		objTE.verifyTextByID("filter_AdvancedEdit", f+o+v,"Verifying the Advanced View Filter Against values in Basic View");
		objTE.clickByLink("Save");
		res1.writeResult("Saving the Document", "Pass", "");	
	}

	@Test(priority=-4)
	public void rtccDocumentsVerificationWS() throws IOException, InterruptedException {
		res1.writeTestcase("RTCC-Documents", "Verifications of all Instructions Added in Previous Steps");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Real-time Content Control");
		objTE.verifyTextByID("wsTopName", wsName, "Verifying the Workspace Selected");
		objTE.waitForElementByXpath("//td[contains(text(),'COV020 Temp1 page1')]");
		objTE.DoubleclickByXpath("//td[contains(text(),'COV020 Temp1 page1')]","Selecting a Document");
		objTE.waitForElementByXpath("//div[contains(text(),'Zone 2')]");
		objTE.clickByXpath("//div[contains(text(),'Zone 2')]","Selecting a Zone");
		objTE.verifyElementByXpath("//td[contains(text(),'07/07/2015')]", "Verifying the Instruction with Mandatory unChecked");

	}
	
	@Test(priority=-3)
	public void dateProofingRTCC3() throws IOException, InterruptedException {
		res1.writeTestcase(37, "Validating the instructions for Reports Tab under Production Module");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Real-time Content Control");
		objTE.clickByLink("Proofing");
		objTE.waitForElementByPartialLink("run new proof job");

		objTE.clickByPartialLink("run new proof job");
		
		objTE.enterByID("name", "QA Reg proof", "Entering the Name of the Proofing Job");
		objTE.clickByID("dataSet_Button", "Selecting the Data Set for the Proofing Job");
		objTE.clickByXpath("//div[@id='dataSet_Items']/table/tbody/tr[2]/td[2]");
		
		String dt=objTE.getDate();
		
		objTE.enterByID("runAsDate", dt, "Enter the Value in the Run as Date Field");
		objTE.clickByID("receiveNotifications", "Clicking on the Receive Notifications");
		objTE.clickByID("run");
		res1.writeResult("Clicking on the Run Proofing Job", "Pass", "");
	
		
	}

	@Test(priority=-1)
	public void rtccSelectWSMerge() throws IOException, InterruptedException {
		res1.writeTestcase("RTCC-Documents", "Selecting the New Workspace");
		
		
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Real-time Content Control");
		objTE.clickByXpath("//img[contains(@src,'arrow-descending-white.gif')]","Click on the Select Icon in the Workspace");
		objTE.clickByXpath("//li[contains(text(),'List')]","Click on the List option in the Workspace menu");
		Thread.sleep(1000);
		objTE.clickByXpath("//td[contains(text(),'"+wsName+"')]/preceding-sibling::td/input","Click on the Radiobutton before the 'Production' Workspace in the Workspaces Listed");
//		objTE.clickByID("wsMergeTab", "Click on the Merge Option in the Workspace menu");
		
		objTE.clickByXpath("//li[contains(text(),'Merge')]","Click on the Merge option in the Workspace menu");
		 
		
	}
	
	
	@Test(priority=8)
	public void rtccDeleteDocumentsProd() throws IOException, InterruptedException {
		res1.writeTestcase("RTCC-Documents", "Deletion of all Instructions Added in Previous Steps");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Real-time Content Control");
		objTE.verifyTextByID("wsTopName", "Production", "Verifying the Workspace Selected");
		objTE.waitForElementByXpath("//td[contains(text(),'COV020 Temp1 page1')]");
		objTE.DoubleclickByXpath("//td[contains(text(),'COV020 Temp1 page1')]","Selecting a Document");
		objTE.waitForElementByXpath("//div[contains(text(),'Zone 2')]");
		objTE.clickByXpath("//div[contains(text(),'Zone 2')]","Selecting a Zone");
//		objTE.verifyElementByXpath("//td[contains(text(),'07/07/2015')]", "Verifying the Instruction with Mandatory unChecked");
//		objTE.verifyElementByXpath("//td[contains(text(),'08/08/2015')]/preceding-sibling::td/img[contains(@src,'required.gif')]", "Verifying the Required Star image For Instruction with Mandatory Checked");
		objTE.clickByXpath("//td[contains(text(),'07/07/2015')]","Selecting the Instruction added in New Workspace for Deletion");
		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
		Thread.sleep(2000);
		objTE.clickByXpath("//td[contains(text(),'08/08/2015')]","Selecting the Mandatory Instruction for Deletion");
		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
		Thread.sleep(2000);
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

	@BeforeSuite
	  public void beforeSuite() {
		res = new ReportUtility();
		  res.moveTheFiles();
	  }

	  @AfterSuite
	  public void afterSuite() {
		  res.archiveTheFiles();
	  }
	
}
