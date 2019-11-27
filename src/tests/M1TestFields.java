package tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class M1TestFields{
	TestEngine objTE;
	ReportUtility res1;
	
	@DataProvider(name="IndexCol")
	public Object[][] createData1() {
		 return new Object[][] {
		   { "AReg1IndexField",74,5,20},
		   
		 };
		}
	
	
	@DataProvider(name="ETLCol")
	public Object[][] createData2() {
		 return new Object[][] {
		   { "AReg1ETLField",1,1},
		   
		 };
		}
	
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	
	@Test(priority=1,dataProvider="IndexCol")
	public void addfieldIndexColumn(String name,int opt,int min,int max) throws IOException, InterruptedException {
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("Field1-1", "Successfully Adding the Field with Index Column");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Fields", "Verifying the Fields Tab");
		objTE.clickByLink("Fields");
		Thread.sleep(5000);
		objTE.clickByPartialLink("add field");
		Thread.sleep(2000);
		objTE.enterByID("fieldName", name);
		objTE.clickByID("columnList_Button");
		objTE.selectOptionInCollectionByXpath("//*[@columnType='Index_Column']",opt);
		Thread.sleep(2000);
		
		objTE.enterByID("minLength", min+"");
		objTE.enterByID("maxLength", max+"");
		objTE.clickByID("ok");
//		Thread.sleep(10000);
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}
	
	@Test(priority=2,dataProvider="IndexCol")
	public void editfieldIndexColumn(String name,int opt,int min,int max) throws IOException, InterruptedException {
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("Field1-2", "Successfully Editing the Field with Index Column");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Fields", "Verifying the Fields Tab");
		objTE.clickByLink("Fields");
		Thread.sleep(5000);
		objTE.DoubleclickByXpath("//*[contains(text(),'"+name+"')]","");
//		objTE.enterByID("fieldName", name);
//		objTE.clickByID("columnList_Button");
////		objTE.scrollAndSelectByID("columnList_Items");
//		System.out.println(objTE.getRowsInWebTableByXpath("//div[@id='columnList_Items']/table/tbody"));
//		objTE.selectOptionInCollectionByXpath("//*[@columnType='Index_Column']",74);
		Thread.sleep(2000);
//		objTE.clickByID("columnList_Button");
//		
//		objTE.selectOptionInCollectionByXpath("//*[@columnType='DPS_ETL_Data_Item']",opt);
		min=min+1;
		objTE.enterByID("minLength", min+"");
		
		objTE.clickByID("ok");
//		Thread.sleep(10000);
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}
	
	@Test(priority=3,dataProvider="IndexCol")
	public void deletefieldIndexColumn(String name,int opt,int min,int max) throws IOException, InterruptedException {
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("Field1-3", "Successfully Deleting the Field with Index Column");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Fields", "Verifying the Fields Tab");
		objTE.clickByLink("Fields");
		Thread.sleep(5000);
		objTE.clickByXpath("//*[contains(text(),'"+name+"')]");
//		objTE.enterByID("fieldName", name);
//		objTE.clickByID("columnList_Button");
////		objTE.scrollAndSelectByID("columnList_Items");
//		System.out.println(objTE.getRowsInWebTableByXpath("//div[@id='columnList_Items']/table/tbody"));
//		objTE.selectOptionInCollectionByXpath("//*[@columnType='Index_Column']",74);
		Thread.sleep(2000);
//		objTE.clickByID("columnList_Button");
//		
//		objTE.selectOptionInCollectionByXpath("//*[@columnType='DPS_ETL_Data_Item']",opt);
		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
		Thread.sleep(5000);
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}
	
//// ******************************************
	
	@Test(priority=4,dataProvider="ETLCol")
	public void addfieldETLColumn(String name,int opt,int min) throws IOException, InterruptedException {
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("Field2-1", "Successfully Adding the Field with ETL Column");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Fields", "Verifying the Fields Tab");
		objTE.clickByLink("Fields");
		Thread.sleep(5000);
		objTE.clickByPartialLink("add field");
		Thread.sleep(2000);
		objTE.enterByID("fieldName", name);
//		objTE.clickByID("columnList_Button");
////		objTE.scrollAndSelectByID("columnList_Items");
//		System.out.println(objTE.getRowsInWebTableByXpath("//div[@id='columnList_Items']/table/tbody"));
//		objTE.selectOptionInCollectionByXpath("//*[@columnType='Index_Column']",74);
		Thread.sleep(2000);
		objTE.clickByID("columnList_Button");
		
		objTE.selectOptionInCollectionByXpath("//*[@columnType='DPS_ETL_Data_Item']",opt);
		
		objTE.enterByID("minLength", min+"");
		
		objTE.clickByID("ok");
//		Thread.sleep(10000);
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}
	
	@Test(priority=5,dataProvider="ETLCol")
	public void editfieldETLColumn(String name,int opt,int min) throws IOException, InterruptedException {
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("Field2-2", "Successfully Editing the Field with ETL Column");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Fields", "Verifying the Fields Tab");
		objTE.clickByLink("Fields");
		Thread.sleep(5000);
		objTE.DoubleclickByXpath("//*[contains(text(),'"+name+"')]","");
//		objTE.enterByID("fieldName", name);
//		objTE.clickByID("columnList_Button");
////		objTE.scrollAndSelectByID("columnList_Items");
//		System.out.println(objTE.getRowsInWebTableByXpath("//div[@id='columnList_Items']/table/tbody"));
//		objTE.selectOptionInCollectionByXpath("//*[@columnType='Index_Column']",74);
		Thread.sleep(2000);
//		objTE.clickByID("columnList_Button");
//		
//		objTE.selectOptionInCollectionByXpath("//*[@columnType='DPS_ETL_Data_Item']",opt);
		min=min+1;
		objTE.enterByID("minLength", min+"");
		
		objTE.clickByID("ok");
//		Thread.sleep(10000);
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}
	
	@Test(priority=6,dataProvider="ETLCol")
	public void deletefieldETLColumn(String name,int opt,int min) throws IOException, InterruptedException {
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("Field2-3", "Successfully Deleting the Field with ETL Column");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Fields", "Verifying the Fields Tab");
		objTE.clickByLink("Fields");
		Thread.sleep(5000);
		objTE.clickByXpath("//*[contains(text(),'"+name+"')]");
//		objTE.enterByID("fieldName", name);
//		objTE.clickByID("columnList_Button");
////		objTE.scrollAndSelectByID("columnList_Items");
//		System.out.println(objTE.getRowsInWebTableByXpath("//div[@id='columnList_Items']/table/tbody"));
//		objTE.selectOptionInCollectionByXpath("//*[@columnType='Index_Column']",74);
		Thread.sleep(2000);
//		objTE.clickByID("columnList_Button");
//		
//		objTE.selectOptionInCollectionByXpath("//*[@columnType='DPS_ETL_Data_Item']",opt);
		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
		Thread.sleep(5000);
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}
	@BeforeMethod
	public void loginApp() throws InterruptedException {
		
	}
	
	@AfterMethod
	public void logoutApp() throws InterruptedException {
		
	}
	
	@BeforeClass
	public void beforeClass() {
		objTE = new TestEngine();
		res1 = new ReportUtility();
		objTE.launchBrowser();
	}
	
	@AfterClass
	public void afterClass() {
		objTE.closeBrowser();
	}
}