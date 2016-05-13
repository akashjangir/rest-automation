/****
 * This is the TestSuite Base for Company Setup configuration.
 * This Class extends the Test Base Class
 * Class has Before & After Suite method to connect/Disconnect Database
 * Class has Before Suite method to get Company setup jersey Client.
 * This is a must file for Company setup testNg script to execute & should not be deleted.
 */
package com.glukoo.testsuite;

import java.io.File;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;




import com.atmecs.falcon.automation.util.parser.PropertyParser;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;

/**
 * 
 *         SampletestSuiteBase class holds the services common for all the
 *         scripts in the suite
 */
public class SampleTestSuiteBase {

	/**
	 * Properties File holds common values which are used across all the scripts
	 */
	public static Properties CONFIG = null;
	public JSONObject expectedJsonData ;
	public ReportLogService logService = new ReportLogServiceImpl(SampleTestSuiteBase.class);
	private String saparator = File.separator;
	private String pathToConfigFile = System.getProperty("user.dir")+saparator+"src"+saparator+"main"+saparator+"resources"+saparator+"config.properties";
	protected String pathToTestDataFile = System.getProperty("user.dir")+saparator+"src"+saparator+"main"+saparator+"java"+saparator+"com"+saparator+
			"glukoo"+saparator+"testdata"+saparator+"SampleTestData.json";
	/*
	 * Any instantiation is required may be done using constructor
	 */
	public SampleTestSuiteBase() {

	}

	/*
	 * BeforeSuite process identified here Passing following parameters is must
	 * in the suite execution process
	 * 
	 * Sample code provided
	 */

	@BeforeSuite
	public void preSetup() {
		try {
			CONFIG = new PropertyParser().loadProperty(pathToConfigFile);
		} catch (Exception e) {
			e.printStackTrace();
			logService.info("Properties file not found");
		}
	}

	@AfterSuite
	public void teardown() {
		// USE THIS METHOD TO WRITE OPERATIONS IF ANY TO DO AFTER SUITE
		CONFIG.clear();
	}

}