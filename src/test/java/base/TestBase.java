package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import reports.ExtentManager;

public class TestBase {
	
	public ExtentReports rep = null;
	public ExtentTest test = null;
	public WebDriver driver = null;
	
	@BeforeMethod
	public void init(ITestResult result) {
		System.out.println("Before method");
		rep = ExtentManager.getReports();
		test = rep.createTest(result.getMethod().getMethodName().toUpperCase());
	}
	
	@AfterMethod
	public void quit() {
		System.out.println("After method");
		rep.flush();
	}
	
	public void log(String msg) {
		test.log(Status.INFO, msg);
	}
	
	
	
}
