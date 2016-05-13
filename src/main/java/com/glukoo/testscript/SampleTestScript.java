package com.glukoo.testscript;



import java.io.File;

import com.atmecs.falcon.automation.rest.endpoint.RequestBuilder;
import com.atmecs.falcon.automation.rest.endpoint.ResponseOptions;
import com.atmecs.falcon.automation.rest.endpoint.ResponseService;
import com.atmecs.falcon.automation.util.parser.JsonParser;
import com.atmecs.falcon.automation.verifyresult.VerificationManager;
import com.glukoo.testsuite.SampleTestSuiteBase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * 
 *         Sample Test Script class is to test the services using Endpoint.
 * 
 *         Sample TestSuiteBase is extended. The common functions required for
 *         all the scripts under this suite to be defined in the TestSuiteBase.
 * 
 */
public class SampleTestScript extends SampleTestSuiteBase {
	
	@BeforeTest public void setup() {
		try {
			expectedJsonData = new JsonParser().getJsonObject(new File(pathToTestDataFile));
		} catch (Exception e) {
			e.printStackTrace();
			logService.info("Could not parse json file");
		}
	}
	
	@Test
	public void testScenario1() {
		RequestBuilder requestBuilder = new RequestBuilder();
		ResponseOptions responseOptions = requestBuilder.build();
		ResponseService responseService = responseOptions.get(CONFIG.getProperty("url"));
		String actualResponseBody = responseService.getResponseBody();
		logService.info(actualResponseBody);
		try {
			VerificationManager.verifyJsonString(expectedJsonData.toJSONString(), actualResponseBody, false);
			Assert.assertEquals(String.valueOf(responseService.getStatusCode()), CONFIG.getProperty("status_code"));
		} catch (Exception e) {
			logService.info("Response Body does not Matched");
			e.printStackTrace();
		}

	}

	/*@Test
	public void testScenario2() {
		RequestBuilder requestBuilder = new RequestBuilder();
		ResponseOptions responseOptions = requestBuilder.build();
		ResponseService responseService = responseOptions.get("http://jsonplaceholder.typicode.com/photos/2");
		String body = responseService.getResponseBody();
		System.out.println(body);

	}*/
	
	@AfterTest public void tearDown() {
		expectedJsonData = null;
	}
}
