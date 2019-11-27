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

public class TestDataUtility {
	XSSFSheet sh;
	XSSFWorkbook wb;
	final String path="\\testresults\\Test-Data.xlsx";
	String pathf;
	public void openReport() throws IOException{
		
		 String basePath = new File("").getAbsolutePath();
		  pathf=basePath+path;

		 FileInputStream fis = new FileInputStream(new File(pathf));
		wb=new XSSFWorkbook(fis);
		sh=wb.getSheet("Sheet1");
	}
	
	public void archiveTheFiles(String str){
		try {
			  File dstTR = new File(".\\testresults\\Smoke-Test-Results.xlsx");
//	            File srcDir = new File(".\\testresults");
//	            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
//	    	     Date date = new Date();
//	      	     String[] dt=dateFormat.format(date).split("-");
//	      	     File dtbk=new File("D:\\Archived\\back up("+dt[0]+"-"+dt[1]+"-"+dt[2]+"_h"+dt[3]+"-m"+dt[4]+"-s"+dt[5]+")");
//	    	     FileUtils.copyDirectory(srcDir,dtbk);
	    	     File flbk=new File(".\\testresults\\"+str+"\\"+"Smoke-Test-Results.xlsx");
	    	     FileUtils.moveFile(dstTR, flbk);
	    	     
	         } catch(Exception e) {}
	}
	
	public void moveTheFiles(){
		try {
			  File ExeDir = new File(".\\testresults");
	            File srcTR = new File(".\\Execution\\Smoke-Test-Results.xlsx");
	            File dstTR = new File(".\\testresults\\Smoke-Test-Results.xlsx");
	            if(!dstTR.exists()){
	            	FileUtils.copyFileToDirectory(srcTR, ExeDir);
	            }
	            } catch(Exception e) {}
	}
	
	public void enterTestData(int i,String a1,String a2,String a3) throws IOException{
		openReport();
		
		XSSFRow rw=sh.createRow(i);
		rw.createCell(1).setCellValue(i);
		rw.createCell(2).setCellValue(a1);
		rw.createCell(3).setCellValue(a2);
		rw.createCell(4).setCellValue(a3);
		
		saveReport();
	}
		
	public void saveReport() throws IOException{
		FileOutputStream fos = new FileOutputStream(new File(pathf));
		wb.write(fos);
		fos.close();
	}
	
}
