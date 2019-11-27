package testengine;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.internal.runners.model.EachTestNotifier;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import utilities.PropertyFileUtil;
import utilities.ReportUtility;
import utilities.TestDataUtility;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestEngine {

    WebDriver driver;
	ReportUtility res;
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	String parent;
	public void launchBrowser() {
		res= new ReportUtility();
		System.setProperty("webdriver.ie.driver",
				prop.get("IEDriverPath"));
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		parent=driver.getWindowHandle();
	}
	
	public void launchChromeBrowser() {
		res= new ReportUtility();
		//System.setProperty("webdriver.ie.driver",
				//prop.get("IEDriverPath"));
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		parent=driver.getWindowHandle();
	}
	
	public String dateQA(){
		Format formatter = new SimpleDateFormat("dd-MMM-yy");
	    String s = formatter.format(new Date());
//	    System.out.println(s);
		return s;
	}
	
	public String getDate(){
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		Date d=new Date();
		String[] dt=dateFormat.format(d).split("-");
		return(dt[0]+"/"+dt[1]+"/"+dt[2]);
	}
	
	public String getPSTDate(){
		Date startTime1 = new Date(); // current date time
	    TimeZone pstTimeZone = TimeZone.getTimeZone("America/Los_Angeles");
	    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss"); // just date, you might want something else
	    formatter.setTimeZone(pstTimeZone);
	    String formattedDate = formatter.format(startTime1);
	    String[] dt=formattedDate.split("-");
		return(dt[1]+"/"+dt[0]+"/"+dt[2]);
	    
	}
	
	public String getDateName(){
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		Date d=new Date();
		String[] dt=dateFormat.format(d).split("-");
		return(dt[0]+dt[1]+dt[2]+dt[3]+dt[4]+dt[5]);
	}
	
	public void launchBrowser(String version) throws MalformedURLException {
		res= new ReportUtility();
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("version", version);
		cap.setBrowserName(DesiredCapabilities.internetExplorer()
				.getBrowserName());
		cap.setCapability("platform", Platform.WINDOWS);
		driver = new RemoteWebDriver(new URL(prop.get("Hub_URL")),
				cap);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void waitForAjax() {
	    new WebDriverWait(driver, 180).until(new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver driver) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return (Boolean) js.executeScript("return jQuery.active == 0");
	        }
	    });
	}

	public void launchApp(String URL) {
		driver.get(URL);
	}

	public void enterByXpath(String pr, String dt) {
		WebElement obj = driver.findElement(By.xpath(pr));
		obj.sendKeys(dt);
	}

	public void enterByXpath(String pr, String dt,String desc) throws IOException {
		try {
			WebElement obj = driver.findElement(By.xpath(pr));
			obj.sendKeys(dt);
			res.writeResult(desc, "Pass", dt);
		} catch (Exception e) {
			res.writeResult(desc, "Fail", dt);
			
		}
	}
	public void enterByID(String pr, String dt) {
		WebElement obj = driver.findElement(By.id(pr));
		obj.clear();
		obj.sendKeys(dt);
	}

	public void enterByID(String pr, String dt,String desc) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.id(pr));
		obj.clear();
		obj.sendKeys(dt);
		res.writeResult(desc, "Pass", dt);
	
	}
	public void clickByID(String pr) throws InterruptedException {
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(pr)));
		WebElement obj = driver.findElement(By.id(pr));
		obj.click();
	}

	public void clickByXpath(String pr) throws InterruptedException {
		WebElement obj = driver.findElement(By.xpath(pr));
		Thread.sleep(1000);
		obj.click();
	}
	
	public void clickByXpath(String pr,String desc,String td) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.xpath(pr));
		obj.click();
		res.writeResult(desc, "Pass", td);
	}
	public void clickByXpath(String pr,String desc) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.xpath(pr));
		obj.click();
		
		res.writeResult(desc, "Pass", "");
		
	}

	public void clickByPartialLink(String pr) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.partialLinkText(pr));
		if(obj.isEnabled()){
			obj.click();
		}
		res.writeResult("Click on the Link", "Pass", pr);
	}
	public void clickByPartialLink1(String pr,String desc) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.partialLinkText(pr));
		if(obj.isEnabled()){
			obj.click();
		}
		res.writeResult(desc, "Pass","");
	}
	public void clickByPartialLink(String pr,String desc) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.partialLinkText(pr));
		if(obj.isEnabled()){
			obj.click();
		}
		
		res.writeResult(desc, "Pass", pr);
		
	}

	
	
	public void switchToFrameByElementUsingName(String loc_name) {
		driver.switchTo().frame(driver.findElement(By.name(loc_name)));
	}

	public void verifyLink(String link,String desc) throws InterruptedException, IOException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.linkText(link));

		if(obj.isDisplayed()==true){
			res.writeResult(desc, "Pass", "");
			
		}
		else{
			res.writeResult(desc, "Fail", "");
			
		}
	}
	
	public void clickByLink(String pr) throws InterruptedException, IOException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.linkText(pr));
		obj.click();
		res.writeResult("Click on the Link", "Pass", pr);
		
	}
	public void clickByLink1(String pr,String Desc) throws InterruptedException, IOException {
		Thread.sleep(2000);
		try {
			WebElement obj = driver.findElement(By.linkText(pr));
			obj.click();
			res.writeResult(Desc, "Pass", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			res.writeResult(Desc, "Fail", "");
		}
		
	}
	
	public void clickByLink(String pr,String Desc) throws InterruptedException, IOException {
		Thread.sleep(2000);
		try {
			WebElement obj = driver.findElement(By.linkText(pr));
			obj.click();
			res.writeResult(Desc, "Pass", pr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			res.writeResult(Desc, "Fail", pr);
		}
		
	}

	public void verifyTextByID(String string, String string2)
			throws IOException {
		String actualValue = driver.findElement(By.id(string)).getText();
		if (actualValue.contains(string2)) {
			res.writeResult("Verifying the Text from the Application", "Pass",
					actualValue);
		
		} else {
			res.writeResult("Verifying the Text from the Application", "Fail",
					actualValue);
			
		}
	}
	public void verifyTextByXpath(String loc, String exp_val)
			throws IOException {
		String actualValue = driver.findElement(By.xpath(loc)).getText();
		if (actualValue.contains(exp_val)) {
			res.writeResult("Verifying the Text from the Application", "Pass",
					actualValue);
		} else {
			res.writeResult("Verifying the Text from the Application", "Fail",
					actualValue);
		}
	}
	public void verifyTextByXpath(String loc, String exp_val,String desc)
			throws IOException {
		String actualValue = driver.findElement(By.xpath(loc)).getText();
		if (actualValue.contains(exp_val)) {
			res.writeResult(desc, "Pass",actualValue);
			
		} else {
			res.writeResult(desc, "Fail",actualValue);
			
		}
	}
	public void closeBrowser(){
		driver.quit();
	}
	public boolean checkElementIsDisplayedByLinkText(String linkText){
		boolean qa=false;
		try {
			System.out.println( driver.findElement(By.linkText(linkText)).isDisplayed());
			qa=driver.findElement(By.linkText(linkText)).isDisplayed();
		} catch (Exception e) {
			qa=false;
		}
		return qa;
	}
	public int getRowsInWebTableByXpath(String xpath) {
		
		WebElement tbl=driver.findElement(By.xpath(xpath));
		return tbl.findElements(By.tagName("tr")).size();
	}
	
	public int getgridRowsInWebTableByXpath(String xpath) {
		
		List<WebElement> tbl=driver.findElements(By.xpath(xpath));
		return tbl.size();
	}

	public void verifyElementByID(String loc, String desc) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.id(loc));

		if(obj.isDisplayed()==true){
			res.writeResult(desc, "Pass", "");
			
		}
		else{
			res.writeResult(desc, "Fail", "");
			
		}
		
	}

	public void verifyIsSelectedByID(String loc, String desc) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.id(loc));

		if(obj.isSelected()==true){
			res.writeResult(desc, "Pass", "");
		
		}
		else{
			res.writeResult(desc, "Fail", "");
			
		}
	}
	
	public void verifyIsSelectedByXpath(String loc,String val, String desc) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.xpath(loc));
		
		if(obj.getText().equals(val)){
			res.writeResult(desc, "Pass", "");
			
		}
		else{
			res.writeResult(desc, "Fail", "");
			
		}
	}

	public void verifyImgSelectedByXpath(String loc,String val, String desc,String td) throws IOException, InterruptedException {
//		Thread.sleep(000);
		WebElement obj = driver.findElement(By.xpath(loc));
		
		if(obj.getAttribute("src").contains(val)){
			res.writeResult(desc, "Pass", td);
			
		}
		else{
			res.writeResult(desc, "Fail", td);
			
		}
	}
	
	public void verifyImgNotSelectedByXpath(String loc,String val, String desc,String td) throws IOException, InterruptedException {
//		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.xpath(loc));
		
		if(obj.getAttribute("src").contains(val)){
			res.writeResult(desc, "Pass", td);
			
		}
		else{
			res.writeResult(desc, "Fail", td);
			
		}
	}
	public String getTextByID(String loc) {
		WebElement obj = driver.findElement(By.id(loc));
		return obj.getText();
	}

	public void verifyGridResultsByID(String... args) throws IOException {
		int count=args.length;
		WebElement obj = driver.findElement(By.id(args[0]));
		
		String str=obj.getText();
		
		String actualValue[] = str.split("\n");
		int flag=0;
		for(int i=1;i<count-1;i++){
			if(args[i].equals(actualValue[i-1])){
			}else{
				flag++;
				break;
			}
		}
		if(flag==0){
			res.writeResult(args[count-1], "Pass", "");
			
		}else{
			res.writeResult(args[count-1], "Fail", "");
			
		}
		
		
		
	}

	public void mouseOverByID(String string) {
		WebElement obj = driver.findElement(By.id(string));
		Actions act=new Actions(driver);
		act.moveToElement(obj).build().perform();
	}

	public void clickONDeleteKeyboard() throws IOException {
		Actions act=new Actions(driver);
		act.sendKeys(Keys.DELETE).build().perform();
		res.writeResult("Clicking on Delete Key in Keyboard", "Pass", "");
	}

	public void acceptAlert() throws IOException {
		driver.switchTo().alert().accept();
		res.writeResult("Clicking on OK Button in Alert", "Pass", "");
	}

	public void clickByID(String pr, String desc) throws InterruptedException, IOException {
		Thread.sleep(1000);
		
		WebElement obj = driver.findElement(By.id(pr));
		obj.click();
		res.writeResult(desc, "Pass", "");
		
		
	}

	public void scrollDown() throws InterruptedException{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,900)", "");
		Thread.sleep(5000);
	}
	
	public void verifyElementByXpath(String loc, String desc) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.xpath(loc));

		if(obj.isDisplayed()==true){
			res.writeResult(desc, "Pass", "");
			
		}
		else{
			res.writeResult(desc, "Fail", "");
			
		}
		
	}
	
	public void verifyElementNotDisplayedByXpath(String loc, String desc) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.xpath(loc));

		if(obj.isDisplayed()==true){
			res.writeResult(desc, "Fail", "");
			
		}
		else{
			res.writeResult(desc, "Pass", "");
			
		}
		
	}
	
	public void confirmElementDeletionByXpath(String loc, String desc) throws IOException, InterruptedException {
		Thread.sleep(2000);
		
		try {
			if(driver.findElement(By.xpath(loc)).isDisplayed()){
				res.writeResult(desc, "Fail", "");
			}
		} catch (NoSuchElementException e) {
			res.writeResult(desc, "Pass", "");
		}
		
		
	}
	
	public void pastePathofFile(String filePath) throws AWTException{
		//Setting clipboard with file location
		
		StringSelection stringSelection = new StringSelection(filePath);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		//native key strokes for CTRL, V and ENTER keys
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public String renameFile(String filePath){
		  String a[]=filePath.split("1");
//		  System.out.println(a[0]+">><<<<");
		  String newPath=a[0]+getDate()+".rtf";
		  File oldName = new File(filePath);
	      File newName = new File(newPath);
	     oldName.renameTo(newName);
	     return newPath;
	}

	public void clickONUPArrowANDEnter(String loc, int i) {
		WebElement ele=driver.findElement(By.id(loc));
		Actions act=new Actions(driver);
		act.click(ele).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
	}

	public void verifyElementExistingUsingXpath(String xpath,String desc) throws IOException {
		
		if(driver.findElement(By.xpath(xpath)).isDisplayed()){
			res.writeResult(desc, "Pass", "");
		}
	}

	public void verifyTextByID(String string, String string2, String string3) throws IOException {
		String actualValue = driver.findElement(By.id(string)).getText();
//		System.out.println(actualValue);
		if (actualValue.contains(string2)) {
			res.writeResult(string3, "Pass",
					actualValue);
		
		} else {
			res.writeResult(string3, "Fail",
					actualValue);
			
		}
	}

	public void waitForElementByID(String id){
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}
	public void waitForElementByXpath(String xpath){
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	
	public boolean getIsSelected(String string) {
		return driver.findElement(By.id(string)).isSelected();
		
	}

	public void verifyPartialLinkIsDisabled(String pl) throws IOException {
		if(driver.findElement(By.partialLinkText(pl)).isEnabled()){
			res.writeResult("The Link is Enabled", "Fail", pl);
			
		}else{
			res.writeResult("The Link is Enabled", "Pass", pl);
			
		}
	}

	public void verifyByXpathContainsSRC(String loc, String val,
			String desc) throws IOException {
		WebElement obj = driver.findElement(By.xpath(loc));
		String str=obj.getAttribute("src");
		if(str.contains(val)){
			res.writeResult(desc, "Pass", "");
		}
		else{
			res.writeResult(desc, "Fail", "");
		}
	}

	public void DoubleclickByXpath(String xpath, String desc) throws IOException {
		WebElement obj = driver.findElement(By.xpath(xpath));
		Actions act=new Actions(driver);
		act.doubleClick(obj).build().perform();
		res.writeResult(desc, "Pass", "");
	}
	public void DoubleclickByXpath1(String xpath, String desc,String td) throws IOException {
		WebElement obj = driver.findElement(By.xpath(xpath));
//		System.out.println("Xpath:::"+xpath);
		Actions act=new Actions(driver);
		act.click(obj).doubleClick().build().perform();
		res.writeResult(desc, "Pass", td);
	}
	public void DoubleclickByXpath(String xpath, String desc,String td) throws IOException {
		WebElement obj = driver.findElement(By.xpath(xpath));
		//System.out.println("Xpath:::"+xpath);
		Actions act=new Actions(driver);
		act.doubleClick(obj).build().perform();
		res.writeResult(desc, "Pass", td);
	}
	public void clickONEnterKeyboard() {
		Actions act=new Actions(driver);
		act.sendKeys(Keys.ENTER).build().perform();
	}
	
	public void dismissAlert() {
		Alert a=driver.switchTo().alert();
		a.dismiss();
	}

	public void verifyElementByIDIsDisabled(String id) throws IOException {
		// TODO Auto-generated method stub
	if(driver.findElement(By.id(id)).isEnabled()){
		res.writeResult("The Element is Enabled", "Fail", id);
		
	}else{
		res.writeResult("The Element is Disabled", "Pass", id);
		
	}
	}
	
	public void verifyElementByIDIsDisabled(String id,String desc) throws IOException {
		// TODO Auto-generated method stub
	if(driver.findElement(By.id(id)).isEnabled()){
		res.writeResult(desc, "Fail", "");
		
	}else{
		res.writeResult(desc, "Pass", "");
	}
	}
	public boolean getStatusElementByIDIsDisabled(String id,String desc) throws IOException {
		boolean sta;
	if(driver.findElement(By.id(id)).isSelected()){
		res.writeResult(desc, "Fail", "");
		
		sta=true;
	}else{
		res.writeResult(desc, "Pass", "");
		
		sta=false;
	}
	return sta;
	}

	public void verifyElementByIDIsNotDisabled(String id,String desc) throws IOException {
		// TODO Auto-generated method stub
	if(driver.findElement(By.id(id)).isEnabled()){
		res.writeResult(desc, "Pass", "");
		
	}else{
		res.writeResult(desc, "Fail", "");
	}
	}
	
	public void selectMultipleByXpath(String xpath) {
		List<WebElement> gridRecords=driver.findElements(By.xpath(xpath));
		Actions act=new Actions(driver);
		act.sendKeys(Keys.CONTROL).clickAndHold(gridRecords.get(0))
		.clickAndHold(gridRecords.get(1)).clickAndHold(gridRecords.get(2))
		.sendKeys(Keys.CONTROL).build().perform();

	}

	public void switchToWindow() {
		Set<String> wins=driver.getWindowHandles();
		//System.out.println(wins.size());
		for (String string : wins) {
			driver.switchTo().window(string);
		}
	}

	public void closeWindow() {
		driver.close();
		
	}

	public void switchToParentWindow() {
		Set<String> wins=driver.getWindowHandles();
		int i=0;
		for (String string : wins) {
			driver.switchTo().window(string);
			i++;
			if(i==1)
				break;
		}
	}

	public void enterByIDIfEnabled(String id, String data) {
		if(driver.findElement(By.id(id)).isEnabled()){
			driver.findElement(By.id(id)).sendKeys(data);
		}
		
	}

	public void verifyElementNonExistenceByID(String id, String desc) throws IOException {
		if(driver.findElement(By.id(id)).isDisplayed()){
			res.writeResult(desc, "Fail", "");
			
		}else{
			res.writeResult(desc, "Pass", "");
			
		}
	}

	public void verifyLocationByXpath(String xpath, int x, int y,
			String desc) throws IOException {
		if(driver.findElement(By.xpath(xpath)).getLocation().getX()==x){
		if(driver.findElement(By.xpath(xpath)).getLocation().getY()==y){
			res.writeResult(desc, "Pass", "");
			
		}else
		{
			res.writeResult(desc, "Fail", "");
		}
		}
		else{
			res.writeResult(desc, "Fail", "");
			
		}
		
		
	}
	
	public void scrollAndSelectByID(String loc){
		WebElement scroll = driver.findElement(By.id(loc));
		Actions act=new Actions(driver);
		act.click(scroll).sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.ENTER).build().perform();;
	}

	public void verifyCheckboxIsNotSelected(String id, String desc) throws IOException {
		if(driver.findElement(By.id(id)).isSelected()){
			res.writeResult(desc, "Fail", "");
			
		}else{
			res.writeResult(desc, "Pass", "");
			
		}
		
	}

	public boolean getCheckboxStatusByID(String id){
		return driver.findElement(By.id(id)).isSelected();
	}
	public void selectByVisibleTextUsingID(String id, String visibleText) {
		Select dd=new Select(driver.findElement(By.id(id)));
		dd.selectByVisibleText(visibleText);
	}

	public void selectByVisibleTextUsingID(String id, String visibleText,
			String desc) throws IOException {
		try {
			Select dd=new Select(driver.findElement(By.id(id)));
			dd.selectByVisibleText(visibleText);
			res.writeResult(desc, "Pass", visibleText);
		} catch (Exception e) {
			res.writeResult(desc, "Fail", visibleText);
		}
	}

	public int getRowsInWebTableByClass(String className) {
		List<WebElement> tbl=driver.findElements(By.className(className));
		return tbl.size();
	}

	public void verifyTextONAlertAndAccept(String str) throws IOException {
		Alert a=driver.switchTo().alert();
		if(a.getText().equalsIgnoreCase(str)){
			res.writeResult("Text on the Alert Matches as Expected", "Pass", str);
		}else{
			res.writeResult("Text on the Alert Doesn't Match as Expected", "Fail", str);
		}
		
	}

	public void verifyByXpathContainsText(String xpath, String expval,
			String desc, String td) throws IOException {
		WebElement obj = driver.findElement(By.xpath(xpath));
		String str=obj.getText();
		if(str.contains(expval)){
			res.writeResult(desc, "Pass", td+"::"+expval);
			
		}
		else{
			res.writeResult(desc, "Fail", td+"::"+expval);
			
		}
	}

	public String getTextByXpath(String string) {
		
		return driver.findElement(By.xpath(string)).getText();
	}

	public void selectOptionInCollectionByXpath(String string, int i) throws IOException {
		List<WebElement> col=driver.findElements(By.xpath(string));
		String td=col.get(i).getText();
		col.get(i).click();
		res.writeResult("Selecting the Option in the List", "Pass",td);
//		System.out.println(col.size());
		
	}

	public void verifyValueByID(String string, String string2) throws IOException {
		String av=driver.findElement(By.id(string)).getAttribute("value");
		if(av.equals(string2)){
			res.writeResult("Verifying the Value in Text Field", "Pass", string2);
		}else{
			res.writeResult("Verifying the Value in Text Field", "Fail", string2);
		}
		
	}

	public void waitForElementByLink(String link) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(link)));
		
	}

	public void waitForElementByPartialLink(String pl) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(pl)));
		
	}

	public void doubleClickOnLastRow(String tb) {
		WebElement tbl=driver.findElement(By.xpath(tb));
		List<WebElement> rws=tbl.findElements(By.tagName("tr"));
		
		Actions act=new Actions(driver);
		act.doubleClick(rws.get(rws.size()-1)).build().perform();
	}

	public void doubleClickByTextUsingXpath(String tbl, String str,int colIndex) {
		
		WebElement tb=driver.findElement(By.xpath(tbl));
		List<WebElement> rws=tb.findElements(By.tagName("tr"));
		for(int i=1;i<rws.size();i++){
			if(driver.findElement(By.xpath(tbl+"/tr["+i+"]/td["+colIndex+"]")).getText().contains(str)){
				Actions act=new Actions(driver);
				act.doubleClick(driver.findElement(By.xpath(tbl))).build().perform();
			}	
		}
		
		
		
		
	}

	public String getAttributeValueByID(String id) {
		
		return driver.findElement(By.id(id)).getAttribute("value");
	}

	public String getAttributeTitleByXpath(String xpath) {
		
		return driver.findElement(By.xpath(xpath)).getAttribute("title");
	}
	public void switchToFrameByElementUsingID(String string) {
		driver.switchTo().frame(driver.findElement(By.id(string)));
		
	}

	public void doubleClickByID(String string, String string2) {
		Actions a=new Actions(driver);
		a.doubleClick(driver.findElement(By.id(string))).build().perform();
		
	}

	public void switchToDefaultWindow() {
		driver.switchTo().defaultContent();
		// TODO Auto-generated method stub
		
	}

	public void verifyValuesInFirstRow(String xpath, int cols, String da,
			String byName) throws IOException {
		String eip=driver.findElement(By.xpath(xpath+"/tr[1]/td[1]")).getText();
		String md=driver.findElement(By.xpath(xpath+"/tr[1]/td[2]")).getText();
		String mb=driver.findElement(By.xpath(xpath+"/tr[1]/td[3]")).getText();
		if(eip.contains("Edit in Production")){
			res.writeResult("Edit in Production is Displayed", "Pass",eip);
		}else{
			res.writeResult("Edit in Production is Not Displayed", "Fail",eip);
		}
		//System.out.println(">>>>>>>>>> Passed"+da);
		String[] dt=da.split("/");
		String date1=dt[1]+"/"+dt[0]+"/"+dt[2];
		//System.out.println("<<<<<< From App"+md);
		
		if(md.contains(da)){
			res.writeResult("Modified Date is Displayed Correctly", "Pass",md);
		}else{
			res.writeResult("Modified Date is Not Displayed Correctly", "Fail",md);
		}
		if(mb.contains(byName)){
			res.writeResult("Modified By Name is Displayed Correctly", "Pass",mb);
		}else{
			res.writeResult("Modified By Name is Not Displayed Correctly", "Fail",mb);
		}
		
	}

	public String getAttributeSRCByXpath(String xpath) {
		return driver.findElement(By.xpath(xpath)).getAttribute("src");
		
	}

	public void verifyAttributeSRCByXpath(String xpath,String ev,String desc) throws IOException {
		
		String ac=driver.findElement(By.xpath(xpath)).getAttribute("src");
		
		if(ac.contains(ev)){
			res.writeResult(desc, "Pass",ev);
		}else{
			res.writeResult(desc, "Fail",ev);
		}
	}

	public void verifySRCByID(String id, String ev, String desc) throws IOException {
        String ac=driver.findElement(By.id(id)).getAttribute("src");
		if(ac.contains(ev)){
			res.writeResult(desc, "Pass",ev);
		}else{
			res.writeResult(desc, "Fail",ev);
		}
		
	}

	public int getCountOFElementsByXpath(String string) {
		return driver.findElements(By.xpath(string)).size();
		
		
	}

	

	public void saveScreenShotByXpath(String xpath, int i,String appCode,String jobCode) throws IOException, InterruptedException {
//		TestDataUtility r=new TestDataUtility();
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		driver.findElements(By.xpath(xpath)).get(i-1).click();
		Thread.sleep(5000);
		Set<String> wins=driver.getWindowHandles();
		for (String w : wins) {
			driver.switchTo().window(w);
		}
		Thread.sleep(5000);
		for (int j = 0; j < 4; j++) {
			
		Thread.sleep(2000);
		//System.out.println(driver.getTitle());
		String s=driver.getTitle();
		if(!s.contains("Step Details")){
			Set<String> wins1=driver.getWindowHandles();
			for (String w : wins1) {
				driver.switchTo().window(w);
			}
			
		}
//		WebElement scroll = driver.findElement(By.tagName("h3"));
		Actions act=new Actions(driver);
		if(j==1)
			act.click().sendKeys(Keys.PAGE_DOWN).build().perform();
		else if(j==2)
			act.click().sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).build().perform();
		else if(j==3)
			act.click().sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).build().perform();
		  String path="\\screenshot\\"+appCode+"\\QA"+appCode+"_"+jobCode+"_"+i+"_"+j+".jpg";
		  String basePath = new File("").getAbsolutePath();
		  String pathf=basePath+path;
		  String dir=basePath+"\\screenshot\\"+appCode;
		  File SnapDir = new File(dir);
          if(!SnapDir.exists()){
          	SnapDir.mkdir();
          }  
          
		createScreenshotUsingRobot(pathf);
		Thread.sleep(5000);
		}
		
		driver.close();
		
//		}else{
//			Set<String> wins1=driver.getWindowHandles();
//			for (String w : wins1) {
//				driver.switchTo().window(w);
//			}
//			Actions act=new Actions(driver);
//			if(j==1)
//				act.click().sendKeys(Keys.PAGE_DOWN).build().perform();
//			else if(j==2)
//				act.click().sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).build().perform();
//			else if(j==3)
//				act.click().sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).build().perform();
//			  String path="\\screenshot\\"+appCode+"\\QA"+appCode+"_"+jobCode+"_"+i+"_"+j+".jpg";
//			  String basePath = new File("").getAbsolutePath();
//			  String pathf=basePath+path;
//			  String dir=basePath+"\\screenshot\\"+appCode;
//			  File SnapDir = new File(dir);
//	          if(!SnapDir.exists()){
//	          	SnapDir.mkdir();
//	          }  
//	          
//			createScreenshotUsingRobot(pathf);
//			Thread.sleep(5000);
//			driver.close();
//			}
		
				
		driver.switchTo().window(parent);
	}
	
	public static void createScreenshotUsingRobot(String location) {
        
	    BufferedImage image = null;
	    try {
	        image = new Robot().createScreenCapture(
	        		new Rectangle(Toolkit.getDefaultToolkit()
	        				.getScreenSize()));
	    } catch (HeadlessException | AWTException e1) {
	        
	    }
	    try {
	        ImageIO.write(image, "jpg", new File(location));
	    } catch (IOException e) {
	        
	    }
	}

	public void clickByIDDisplayTheOption(String pr, String desc,String xpath) throws IOException, InterruptedException {
		Thread.sleep(1000);
		
		WebElement obj = driver.findElement(By.id(pr));
		obj.click();
		Thread.sleep(3000);
		String str=driver.findElement(By.xpath(xpath)).getText();
		res.writeResult(desc, "Pass", str);
		
	}

	public void verifyAttributeValueByID(String id, String ev,
			String desc) throws IOException {
		String av=driver.findElement(By.id(id)).getText();
		//System.out.println("Actual Value Text"+av);
		String ac=driver.findElement(By.id(id)).getAttribute("value");
		//System.out.println("Actual Value"+ac);
		if(av.contains(ev)){
			res.writeResult(desc, "Pass",ev);
		}else{
			res.writeResult(desc, "Fail",ev);
		}
	}

	public void assignWindowFromWindowHandles() {
		Set<String> w=driver.getWindowHandles();
		//System.out.println(w.size());
		for (String s : w) {
			driver.switchTo().window(s);
		}
		
	}

	

	}
