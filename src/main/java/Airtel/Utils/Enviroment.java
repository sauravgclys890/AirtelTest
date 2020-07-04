package Airtel.Utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Enviroment {

	public static Map<String, String> env= new HashMap<String, String>();
	public static Properties propMain= new Properties();
	
	public static Map<String, String> envAndFile(){
		
		String enviroment= System.getProperty("env");
		
		try {
			if(enviroment.equalsIgnoreCase("server")) {
				FileInputStream fileDev= new FileInputStream(System.getProperty("user.dir")+"/input/server.properties");
				propMain.load(fileDev);
				env.put("serverUrl", propMain.getProperty("ServerUrl"));
				env.put("portNumber", propMain.getProperty("portnumber"));
				env.put("username", propMain.getProperty("username"));
				env.put("password", propMain.getProperty("password"));
			}
		}catch(Exception e) {
			
		}
		return env;
	}
	
	public static Map<String, String> getConfigReader(){
		if(env == null) {
			env = envAndFile();
		}
			
		return env;
		
	}
}
