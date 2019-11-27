package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ReadFileUtility {
	BufferedReader in;
	File tempFile;
	PrintWriter pw;
	public String readDatainLine(String path,int lineNum) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(path));
		String line;
		int count = 0;
		while ((line = in.readLine()) != null) {
			count++;
			if(count==lineNum)
				break;
		}
		in.close();
		return line;
	}

	public int linesInCSV(String path) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(path));
		String line;
		int count = 0;
		while ((line = in.readLine()) != null) {
			count++;
		}
		in.close();
		return count;
	}
	public void readData(String path) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(path));
		String line;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
		in.close();
	}
	
	
	
	public void openTempFile(String path) throws IOException{
		in = new BufferedReader(new FileReader(path));
		tempFile = new File("D:\\ECWISELATESTSCRIPTS\\ECWISE LATEST SCRIPTS\\TCT\\DATA\\Test_Data1.py");
		 pw = new PrintWriter(new FileWriter(tempFile));
	}
	
	
	public void readAppendData(String expv,String val) throws IOException {
		
		String line;
		while ((line = in.readLine()) != null) {
//			System.out.println(line);
			if(line.contains(expv)){
				line=expv+" = '"+val+"'";
			}
			pw.println(line);
           
		}
	     
	}
	
	public void closeTempFile() throws IOException{
		 pw.flush();
		pw.close();
		in.close();
	}
	
	public void readAppendData1(String path,String expv,String val) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(path));
		File tempFile = new File("D:\\ECWISELATESTSCRIPTS\\ECWISE LATEST SCRIPTS\\TCT\\DATA\\Test_Data2.py");
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		String line;
		while ((line = in.readLine()) != null) {
//			System.out.println(line);
			if(line.contains(expv)){
				line=expv+" = '"+val+"'";
			}
			pw.println(line);
            pw.flush();
		}
	     pw.close();
		in.close();
	}
	public void readAppendData12(String path,String expv,String val) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(path));
		File tempFile = new File("D:\\ECWISELATESTSCRIPTS\\ECWISE LATEST SCRIPTS\\TCT\\DATA\\Test_Data2.py");
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		String line;
		while ((line = in.readLine()) != null) {
//			System.out.println(line);
			if(line.contains(expv)){
				line=expv+" = "+val;
			}
			pw.println(line);
            pw.flush();
		}
	     pw.close();
		in.close();
	}
	public void readAppendData2(String path,HashMap<String, String> hm1) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(path));
		File tempFile = new File("D:\\ECWISELATESTSCRIPTS\\ECWISE LATEST SCRIPTS\\TCT\\DATA\\Test_Data3.py");
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		String line;
		for (Map.Entry<String, String> entry : hm1.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		
		while ((line = in.readLine()) != null) {
//			System.out.println(line);
			if(line.contains(entry.getKey())){
				line=entry.getKey()+" = '"+entry.getValue()+"'";
			}
			pw.println(line);
            
		}
	   
	}
		pw.flush();
		  pw.close();
				in.close();
	}
	
}
