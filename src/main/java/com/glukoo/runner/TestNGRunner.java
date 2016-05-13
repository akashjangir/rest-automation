package com.glukoo.runner;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import com.atmecs.falcon.automation.dataprovider.TestDataProvider;
import com.atmecs.falcon.automation.util.main.AbstractTestNGEngine;
import com.atmecs.falcon.automation.util.main.TestNGEngineFactory;
import com.atmecs.falcon.automation.util.main.TestNGEngineTemplateType;


/**
 * TestNGRunner is the Main Class of MOJO type generates new Xml Suites on
 * runtime for each client for each child suite in the existing parent Xml Suite
 * and executes
 * 
 * @author nv092106
 * 
 */
public class TestNGRunner {
	private static AbstractTestNGEngine testNGEngine = new TestNGEngineFactory()
			.getTestNGEngine(TestNGEngineTemplateType.COMMON_PARAMS_FOR_ALL_SUITES);
	private static String filename = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "main" + File.separator
			+ "resources" + File.separator + "config.properties";
	private static TestNG testng = new TestNG();
	private static List<XmlSuite> suitesToRun = null;
	private static Properties props = null;
	private static TestDataProvider dataProvider = TestDataProvider
			.getInstance();

	/**
	 * Purpose: The main method invoked by the Maven plugin that uses the
	 * services of TestNGEngine to create new Xml Suites on runtime and executes
	 * them
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		initialize();
		suitesToRun = testNGEngine.getSuitesToRunFor(
				props.getProperty("SuiteFileName"),
				props.getProperty("ParamName"));
		testng.setXmlSuites(suitesToRun);
		testng.run();

	}

	private static void initialize() throws Exception {

		// Listener Classes are added to TestNG object for Report View
		//listenerClasses.add(com.example.rest_example.reportengine.CustomListener.class);
		//listenerClasses.add(com.example.rest_example.reportengine.ReportListener.class);
		//testng.setListenerClasses(listenerClasses);
		// Loading properties file
		props = dataProvider.loadProperties(filename);

	}
}
