package utilities;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertyFileUtil1 {
	
	public void updatePropertiesFile(String Key,String Value) throws IOException{
		Properties pop = new Properties();
		pop.load(new FileInputStream("TestJobsQA.properties"));
		pop.put(Key, Value);
		FileOutputStream output = new FileOutputStream("TestJobsQA.properties");
		pop.store(output, "This is overwrite file");
	}
	
}