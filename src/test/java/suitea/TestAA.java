package suitea;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import dataprovider.TestDataProvider;
import reports.ExtentManager;
import testbase.TestBase;

public class TestAA  extends TestBase{
	
	@Test(groups = {"sanity","browsergroup2"},dataProviderClass = TestDataProvider.class , dataProvider = "dataSuiteA")
	public void testAA(String browserName,String username, String password) throws InterruptedException {
		System.out.println("Starting AA ");
		log(username+" -- "+password);
		System.out.println("Starting AA x");
		Thread.sleep(2000);
		System.out.println("Ending AA");
	}
}
