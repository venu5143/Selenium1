package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EnvironmentUtil {

	public Map getEnvironment(String env) throws IOException{
		Properties prop = new Properties();
		InputStream input = new FileInputStream(new File("src/"+env+".config").getAbsoluteFile());
		
		prop.load(input);
		
		Map m2 = new HashMap();
		Enumeration<?> e = prop.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = prop.getProperty(key);
//			System.out.println("Key : " + key + ", Value : " + value);
			
			 
			m2.put(key,value); 
		
		}
		return m2;
	}
	
}
