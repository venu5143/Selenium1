package utilities;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertyFileUtil {
	
	private ResourceBundle properties = null;
	private HashMap<String,String> objectMapping = null;
	
	public PropertyFileUtil(String filename){
		properties = ResourceBundle.getBundle(filename);
	}

	public HashMap<String, String> getWebElementMapping(){
		objectMapping = new HashMap<String,String>();
		for(String key : properties.keySet()){
			objectMapping.put(key, properties.getString(key));
		}
		return objectMapping;
		
	}
	
	public void updatePropertiesFile(String Key,String Value) throws IOException{
		Properties pop = new Properties();
		pop.load(new FileInputStream("TestJobsQA.properties"));
		pop.put(Key, Value);
		FileOutputStream output = new FileOutputStream("TestJobsQA.properties");
		pop.store(output, "This is overwrite file");
	}
	
}