package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class ReadConfig {
	
	public static void PopulateSettings() throws IOException {
		ReadConfig reader = new ReadConfig();
        reader.ReadProperty();
    }
    private void ReadProperty() throws IOException {

        Properties p= new Properties();
        InputStream inputStream = new FileInputStream("src\\main\\java\\config\\ConfigReader.properties");
        p.load(inputStream);
        Settings.baseURI=p.getProperty("BaseUri");
    }
}
