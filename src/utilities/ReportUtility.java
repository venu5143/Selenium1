package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReportUtility {
	XSSFSheet sh,sh1;
	XSSFWorkbook wb,wb1;
	final String pathd="\\testdata\\Test-Data.xlsx";
	final String path="\\testresults\\Test-Results.xlsx";
	String pathf,paths;
	public void writeSTRDate(String strDate) throws IOException{
		openData();
		XSSFRow rw=sh1.createRow(1);
		rw.createCell(1).setCellValue(strDate);
		saveData();
	}
	
	public String readSTRDate() throws IOException{
		openData();
		String date_run=sh1.getRow(1).getCell(1).getStringCellValue();
		saveData();
		return date_run;
	}
	
	public void openData() throws IOException{
		
		 String basePath = new File("").getAbsolutePath();
		 //System.out.println(pathf);
		  paths=basePath+pathd;
		  //System.out.println(pathf);
		 FileInputStream fis = new FileInputStream(new File(paths));
		wb1=new XSSFWorkbook(fis);
//		System.out.println(wb.getNumberOfSheets());
		sh1=wb1.getSheet("Sheet1");
//		System.out.println(sh.getLastRowNum());
	}
	
	public void saveData() throws IOException{
		FileOutputStream fos = new FileOutputStream(new File(paths));
		wb1.write(fos);
		fos.close();
	}
	
	public void openReport() throws IOException{
		
		 String basePath = new File("").getAbsolutePath();
		 //System.out.println(pathf);
		  pathf=basePath+path;
		  //System.out.println(pathf);
		 FileInputStream fis = new FileInputStream(new File(pathf));
		wb=new XSSFWorkbook(fis);
//		System.out.println(wb.getNumberOfSheets());
		sh=wb.getSheet("Sheet1");
//		System.out.println(sh.getLastRowNum());
	}
	
	public void archiveTheFiles(){
		try {
			  File dstTR = new File("C:\\Selenium\\testresults\\Test-Results.xlsx");
	            File srcDir = new File("C:\\Selenium\\testresults");
	            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
	    	     Date date = new Date();
	      	     String[] dt=dateFormat.format(date).split("-");
	      	   File dtbk=new File("D:\\Archived\\back up("+dt[0]+"-"+dt[1]+"-"+dt[2]+"_h"+dt[3]+"-m"+dt[4]+"-s"+dt[5]+")");
	    	     FileUtils.copyDirectory(srcDir,dtbk);
	    	     File flbk=new File("D:\\Archived\\back up("+dt[0]+"-"+dt[1]+"-"+dt[2]+"_h"+dt[3]+"-m"+dt[4]+"-s"+dt[5]+")\\"+"Test-Results"+"_"+dt[0]+dt[1]+dt[2]+"_"+dt[3]+dt[4]+".xlsx");
	    	     FileUtils.moveFile(dstTR, flbk);
	    	     
	         } catch(Exception e) {}
	}
	
	public void moveTheFiles(){
		try {
			  File ExeDir = new File("C:\\Selenium\\testresults");
	            File srcTR = new File("C:\\Selenium\\Execution\\Test-Results.xlsx");
	            File dstTR = new File("C:\\Selenium\\testresults\\Test-Results.xlsx");
	            if(!dstTR.exists()){
	            	FileUtils.copyFileToDirectory(srcTR, ExeDir);
	            }
	            } catch(Exception e) {}
	}
	public void writeResult(String stepDesc,String result,String testdata) throws IOException{
		openReport();
		int rows=sh.getLastRowNum();
		XSSFRow rw=sh.createRow(rows+1);
		rw.createCell(2).setCellValue(stepDesc);
		rw.createCell(3).setCellValue(result);
		rw.createCell(4).setCellValue(testdata);
		saveReport();
	}
	public void writeResult2(String appCode,String stepDesc,String result,String testdata) throws IOException{
		openReport();
		int rows=sh.getLastRowNum();
		XSSFRow rw=sh.createRow(rows+1);
		rw.createCell(1).setCellValue(appCode);
		rw.createCell(2).setCellValue(stepDesc);
		rw.createCell(3).setCellValue(result);
		rw.createCell(4).setCellValue(testdata);
		saveReport();
	}
	public void writeResult1(String stepDesc,String result,String reason) throws IOException{
		openReport();
		int rows=sh.getLastRowNum();
		XSSFRow rw=sh.createRow(rows+1);
		
		rw.createCell(2).setCellValue(stepDesc);
		rw.createCell(5).setCellValue(reason);
		rw.createCell(6).setCellValue(result);
		saveReport();
	}
	
	public void writeResult12(String appCode,String stepDesc,String result,String reason) throws IOException{
		openReport();
		int rows=sh.getLastRowNum();
		XSSFRow rw=sh.createRow(rows+1);
		rw.createCell(1).setCellValue(appCode);
		rw.createCell(2).setCellValue(stepDesc);
		rw.createCell(5).setCellValue(reason);
		rw.createCell(6).setCellValue(result);
		saveReport();
	}
public void writeTestcase(int num ,String testDesc) throws IOException{
		openReport();
		
		int rows=sh.getLastRowNum();
//		System.out.println(rows);
		XSSFRow rw=sh.createRow(rows+1);
		rw.createCell(1).setCellValue(num);
		rw.createCell(2).setCellValue(testDesc);
		saveReport();
	}

public void writeTestcase(String num ,String testDesc,String version) throws IOException{
	openReport();
	int rows=sh.getLastRowNum();
	XSSFRow rw=sh.createRow(rows+1);
	rw.createCell(0).setCellValue(version);
	rw.createCell(1).setCellValue(num);
	rw.createCell(2).setCellValue(testDesc);
	
	saveReport();
}

public void writeTestcase(String num ,String testDesc) throws IOException{
	openReport();
	int rows=sh.getLastRowNum();
	XSSFRow rw=sh.createRow(rows+1);

	rw.createCell(1).setCellValue(num);
	rw.createCell(2).setCellValue(testDesc);
	
	saveReport();
}
	
	public void saveReport() throws IOException{
		FileOutputStream fos = new FileOutputStream(new File(pathf));
		wb.write(fos);
		fos.close();
	}
	
}
