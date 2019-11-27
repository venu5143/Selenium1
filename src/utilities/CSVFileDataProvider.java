package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVFileDataProvider {

	public Object[][] getData(String path) throws IOException{
		  String  thisLine = null;
		  ArrayList aList;
		  
		  ReadFileUtility rfu=new ReadFileUtility();
		  int lines=rfu.linesInCSV(path);
		  
		  Object [][] objArray = new Object[lines][];
		  
			      try{

			    	  int i=0;
			         BufferedReader br = new BufferedReader(new FileReader(path));
			         while ((thisLine=br.readLine()) != null) {
		        	 
			             aList= new ArrayList(Arrays.asList(thisLine.split(",")));

			             
			            
			             objArray[i] = new Object[aList.size()];
			      
			             for(int j=0;j< aList.size();j++){
			            	 
			                objArray[i][j] = aList.get(j);
			             } 

			            i++;
			            
			         }       
			         
			      }catch(Exception e){
			         e.printStackTrace();
			      }
			      
			      return objArray;
	}
	
}
