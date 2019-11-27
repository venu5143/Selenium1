package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReportUtility {
	
	int testjobs_row_start=0;
	int testjobs_row_end=0;
	int ia_row_start=0;
	int ia_row_end=0;
	int dg_row_start=0;
	int dg_row_end=0;
	int sh_row_start=0;
	int sh_row_end=0;
	XSSFWorkbook wb;
	XSSFSheet sh;
	String pathd="/testresults/Smoke.xlsx";
	String path;
	public void openBook() throws IOException{
		 String basePath = new File("").getAbsolutePath();
		 //System.out.println(pathf);
		  path=basePath+pathd;
		FileInputStream fis=new FileInputStream(new File(path));
		wb=new XSSFWorkbook(fis);
		sh=wb.getSheetAt(0);
	}
	
	public void saveBook() throws IOException{
		String basePath = new File("").getAbsolutePath();
		 //System.out.println(pathf);
		  path=basePath+pathd;
		FileOutputStream fos=new FileOutputStream(new File(path));
		wb.write(fos);
		fos.close();
	}
	
	public void getApps(){
		System.out.println(sh.getRow(testjobs_row_start).getCell(2).getStringCellValue());
		System.out.println(sh.getRow(testjobs_row_end).getCell(2).getStringCellValue());
	}
	
	public void assignDGRows(int col_header,String text_header) throws IOException{
		openBook();
		dg_row_end=27;
		dg_row_start=26;
	}
	
	public void assignSHRows(int col_header,String text_header) throws IOException{
		openBook();
		sh_row_end=31;
		sh_row_start=30;
	}
	
	public void assignIARows(int col_header,String text_header) throws IOException{
		openBook();
		int rw=sh.getLastRowNum();
		String str="";
		String str1="";
		for (int i = 0; i <= rw; i++) {
			try {
				str=sh.getRow(i).getCell(col_header).getStringCellValue();
			} catch (Exception e) {

			}
			
			finally{
				System.out.println("I"+i);
				if(str.contains(text_header)){
					ia_row_start=i+1;
					for (int j = ia_row_start; j <= rw; j++) {
						try {
							System.out.println(j+"J");
							str1=sh.getRow(j).getCell(col_header+1).getStringCellValue();
						} catch (Exception e) {
							ia_row_end=j-1;
							System.out.println(ia_row_end+"J-1");
						}
						
						ia_row_end=j-1;
					}
					break;
				}
			}
			
		}
		ia_row_end--;
		System.out.println(ia_row_start+"<><><><"+ia_row_end);
	}
	
	public void assignTestjobsRows(int col_header,String text_header) throws IOException{
		openBook();
		int rw=sh.getLastRowNum();
		String str="";
		String str1="";
		for (int i = 0; i <= rw; i++) {
			try {
				str=sh.getRow(i).getCell(col_header).getStringCellValue();
			} catch (Exception e) {

			}
			finally{
				if(str.contains(text_header)){
					testjobs_row_start=i+1;
					
					for (int j = testjobs_row_start; j <= rw; j++) {
						try {
							str1=sh.getRow(j).getCell(col_header+1).getStringCellValue();
						} catch (Exception e) {
							testjobs_row_end=j-1;
							
						}
						
						
					}
					break;
				}
			}
			
		}
	}

	
	public void updatePreRequesiteStatus(String app, String status) throws IOException {
//		System.out.println("Inside UPDATE PREREQUISITE");
		for (int i = testjobs_row_start; i <= testjobs_row_end; i++) {
//			System.out.println("Inside FOR");
			String appCode=sh.getRow(i).getCell(2).getStringCellValue();
			if(appCode.equalsIgnoreCase(app)){
//				System.out.println("Inside IF");
				sh.getRow(i).createCell(3).setCellValue(status);
				saveBook();
				break;
			}
		}
		
	}
	
	public void updateJobStatus(String app, String status,String jobnumber) throws IOException {
		for (int i = testjobs_row_start; i <= testjobs_row_end; i++) {
			String appCode=sh.getRow(i).getCell(2).getStringCellValue();
			if(appCode.equals(app)){
				sh.getRow(i).createCell(5).setCellValue(jobnumber);
				sh.getRow(i).createCell(4).setCellValue(status);
				saveBook();
				break;
			}
		}
		
	}

	public void updateMonitorStatus(String appCode, String status,String jobs) throws IOException {
		String a[]=jobs.split("@");
		String j="";
		for (int i = 1; i < a.length; i++) {
			if(j.length()==0){
				j=a[i];
			}else{
				j=j+"\n"+a[i];
			}
		}
		for (int i = testjobs_row_start; i <= testjobs_row_end; i++) {
			String app=sh.getRow(i).getCell(2).getStringCellValue();
			if(appCode.equals(app)){
				sh.getRow(i).createCell(6).setCellValue(status);
				sh.getRow(i).createCell(7).setCellValue(j);
				saveBook();
				break;
			}
		}
		
	}
	
	public void updateIAStatus(String appCode, String view2,String monitor) throws IOException {
		System.out.println(ia_row_start+"::::"+ia_row_end);
		for (int i = ia_row_start; i <= ia_row_end; i++) {
			String app=sh.getRow(i).getCell(2).getStringCellValue();
			if(appCode.equals(app)){
				if(view2.contains("NA"))
					sh.getRow(i).createCell(5).setCellValue(view2);
				else{
				sh.getRow(i).createCell(3).setCellValue(view2);
				sh.getRow(i).createCell(4).setCellValue(monitor);
				if(monitor.contains(view2)){
					sh.getRow(i).createCell(5).setCellValue("P");
				}else
				{
					sh.getRow(i).createCell(5).setCellValue("F");
				}
				}
				saveBook();
				break;
			}
		}
		
	}
	
	public void updateDGStatus(String appCode, String view2,String monitor) throws IOException {
		System.out.println(dg_row_start+"::::"+dg_row_end);
		for (int i = dg_row_start; i <= dg_row_end; i++) {
			String app=sh.getRow(i).getCell(2).getStringCellValue();
			if(appCode.equals(app)){
				if(view2.equals("NA")){
					sh.getRow(i).createCell(5).setCellValue("NA");
				}
				else{
				sh.getRow(i).createCell(3).setCellValue(view2);
				sh.getRow(i).createCell(4).setCellValue(monitor);
				if(monitor.contains(view2)){
					sh.getRow(i).createCell(5).setCellValue("P");
				}else
				{
					sh.getRow(i).createCell(5).setCellValue("F");
				}
				}
				saveBook();
				break;
			}
		}
		
	}
	
	public void updateSHStatus(String appCode, String view2,String monitor) throws IOException {
		System.out.println(sh_row_start+"::::"+sh_row_end);
		for (int i = sh_row_start; i <= sh_row_end; i++) {
			String app=sh.getRow(i).getCell(2).getStringCellValue();
			if(appCode.equals(app)){
				sh.getRow(i).createCell(3).setCellValue(view2);
				sh.getRow(i).createCell(4).setCellValue(monitor);
				if(monitor.contains(view2)){
					sh.getRow(i).createCell(5).setCellValue("P");
				}else
				{
					sh.getRow(i).createCell(5).setCellValue("F");
				}
				saveBook();
				break;
			}
		}
		
	}
	
}
