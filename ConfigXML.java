package com.Connect;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConfigXML 
{
	public String[] getConfig()
	{
		String Aconfig[] = null;
		//File fileName = new File("C:\\NSEC\\Java.Projects\\EclipseProjects\\eclipse-workspace\\UserManagementProject\\Java Resources\\src\\main\\java\\XML");

		//String directory = new File("Config.xml").getAbsolutePath();
		
		//String localDir = directory.getAbsolutePath() ; ///System.getProperty("user.dir");
		//String fileName = (directory + "\\Config.xml"); //"\\webapp\\WEB-INF\\Config.xml");
		
		//File fileName = new File("/UserManagementProject/src/main/webapp/WEB-INF/Config.xml");
		File fileName = new File("C:\\NSEC\\Java.Projects\\EclipseProjects\\eclipse-workspace\\UserManagementProject\\src\\main\\webapp\\WEB-INF\\Config.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder builder = factory. newDocumentBuilder();
			Document doc = builder.parse(fileName);
			Element root = doc.getDocumentElement();
			
			Aconfig = new String[] { root.getElementsByTagName("driver").item(0).getTextContent().toString(),
									root.getElementsByTagName("url").item(0).getTextContent().toString(),
									root.getElementsByTagName("username").item(0).getTextContent().toString(),
									root.getElementsByTagName("password").item(0).getTextContent().toString()
							   };
		}
		catch(Exception ex) 
		{
			ex.getMessage();
		}
		return Aconfig;
	}

}
