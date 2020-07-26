package suitea;

import static org.testng.Assert.fail;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import testbase.TestBase;

public class SakraWorldTest extends TestBase{
	// Explicit wait
	// Validate the options in Gender field
	// isElementPresent + identifying elements with different locators dynamically
	// screenshots
	@Test
	public void appointmentTest() throws InterruptedException {

		launchBrowser("Chrome"); // prop init
		log("Opened browser Chrome");
		driver.get(prop.getProperty("url")); // 100%
		//driver.findElement(By.cssSelector("xxxxx"));
		waitForPageToLoad();
	
		driver.findElement(By.linkText(prop.getProperty("doctor_name"))).click();
		// validate the presence and visibility
		// *********************************Explicit wait*********************************
		
		
		//Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(getObjectLocator("name_xpath")));
		
		if(! isElementPresent("name_xpath"))
			failAndStopTest("Name field is not present/visible");
		log("Name field is present");
		
		
		driver.findElement(getObjectLocator("name_xpath")).sendKeys(prop.getProperty("first_name"));
		driver.findElement(getObjectLocator("email_css")).sendKeys(prop.getProperty("email_id"));
		driver.findElement(getObjectLocator("mobile_number_id")).sendKeys(prop.getProperty("phone_number"));
		// ******************************Validate the options in Gender field**************************
		String expectedGenderValues[] = prop.getProperty("expectedGenderOptions").split(",");
		
		WebElement gender = driver.findElement(getObjectLocator("gender_id"));
		Select s = new Select(gender);
		s.selectByVisibleText("Male");
		List<WebElement> genders = s.getOptions();
		if(genders.size() != expectedGenderValues.length)
			fail("Gender list not OK");
		
		for(int i=0;i<genders.size();i++) {
			System.out.println(expectedGenderValues[i]+ " --- "+genders.get(i).getText());
		}
		
		
		if(! isElementPresent("dob_id"))
			failAndStopTest("DOB field is not present/visible");
		
		driver.findElement(getObjectLocator("dob_id")).click();
		selectDate(prop.getProperty("dob_val"));
		log("Selected DOB Successfully");
		
		if(! isElementPresent("prefer_date_visit1_id"))
			failAndStopTest("DOB field is not present/visible");
		
		driver.findElement(getObjectLocator("prefer_date_visit1_id")).click();
		selectDate(prop.getProperty("preffered_date_1"));
		
		log("Selected preferred Date 1 Successfully");
		//preferred date 2
		
		if(driver.findElement(getObjectLocator("uhid_id")).isDisplayed())
			failAndStopTest("UHID is displayed");
		log("UHID is not displayed. Clickng on Yes");
		driver.findElement(getObjectLocator("yes_radio_css")).click();
		
		if(!driver.findElement(getObjectLocator("uhid_id")).isDisplayed())
			failAndStopTest("UHID is not displayed");
		
		driver.findElement(getObjectLocator("uhid_id")).sendKeys(prop.getProperty("uhild_val"));
		driver.findElement(getObjectLocator("no_raido_css")).click();
		if(driver.findElement(getObjectLocator("uhid_id")).isDisplayed())
			failAndStopTest("UHID is displayed");
		
		driver.findElement(getObjectLocator("yes_radio_css")).click();
		String val = driver.findElement(getObjectLocator("uhid_id")).getAttribute("value");
		if(!val.equals(prop.getProperty("uhild_val")))
			failAndStopTest("text field value is not ok");
		log("UHID functionality is OK");
		
		
	}
	// true - present and not hidden
	// false - not present or hidden
	public boolean isElementPresent(String locatorKey) {
		WebElement e = null;
		// presence
		/*
		try {
			e = driver.findElement(By.id(locator));
		}catch(Exception ex) {
			log("Exception while extracting object" + ex.getMessage());
			return false;
		}
		*/
		By locator = getObjectLocator(locatorKey);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
		// visibility
		//log("Element visibility status" + e.isDisplayed());
		//if(!e.isDisplayed())
		//	return false;
		
		// reach here - - present and not hidden
		return true;
		
		
	}
	
	
	public void waitForPageToLoad(){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int i=0;
		
		while(i!=10){
		String state = (String)js.executeScript("return document.readyState;");
		System.out.println(state);

		if(state.equals("complete"))
			break;
		else
			wait(2);

		i++;
		}
		// check for jquery status
		i=0;
		while(i!=10){
	
			Long d= (Long) js.executeScript("return jQuery.active;");
			System.out.println(d);
			if(d.longValue() == 0 )
			 	break;
			else
				 wait(2);
			 i++;
				
			}
		
		}
	
	public void wait(int time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectDate(String dateVal) {
		// dynamic - date selection
				String monthYearDisplayed = driver.findElement(getObjectLocator("monthyear_css")).getText();
				System.out.println("monthYearDisplayed -"+monthYearDisplayed);
				SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
				try {
					Date dateToBeSelected = sd.parse(dateVal);
					Date currentDate = new Date();
					String day = new SimpleDateFormat("d").format(dateToBeSelected);
					System.out.println(day);
					String month= new SimpleDateFormat("MMMM").format(dateToBeSelected);
					System.out.println(month);
					String year= new SimpleDateFormat("yyyy").format(dateToBeSelected);
					System.out.println(year);
					String monthYearToBeSelected = month+" "+year;
					System.out.println("monthYearToBeSelected -"+monthYearToBeSelected);
					
					while(!monthYearToBeSelected.equals(monthYearDisplayed)) {
						// click on forward or back icon
						if(dateToBeSelected.compareTo(currentDate) == 1) {//
							//forward icon
							driver.findElement(getObjectLocator("calendar_forward_xpath")).click();
						}else if(dateToBeSelected.compareTo(currentDate) == -1){
							// back icon
							driver.findElement(getObjectLocator("calendar_back_xpath")).click();
						}
						 monthYearDisplayed = driver.findElement(getObjectLocator("monthyear_css")).getText();
					     System.out.println("monthYearDisplayed -"+monthYearDisplayed);
					     
					}
					
					 //day
				     driver.findElement(By.xpath("//a[text()='"+day+"']")).click();

				}catch (Exception e) {
					
					e.printStackTrace();
				}
				
	}
	
	public By getObjectLocator(String locatorKey) {
		if(locatorKey.endsWith("_id"))
			return By.id(prop.getProperty(locatorKey));
		else if(locatorKey.endsWith("_name"))
			return By.name(prop.getProperty(locatorKey));
		else if(locatorKey.endsWith("_css"))
			return By.cssSelector(prop.getProperty(locatorKey));
		else
			return By.xpath(prop.getProperty(locatorKey));
	}
	
	

}
