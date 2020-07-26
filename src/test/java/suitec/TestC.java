package suitec;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testbase.TestBase;

public class TestC  extends TestBase{

	//@Parameters("browser")
	@Test(groups = {"smoke","browsergroup1"})
	public void testC() throws InterruptedException {
		log("Executing the test in browser "+ browser);
		log("Starting C "+browser );
		Thread.sleep(2000);
		log("Ending C");
	}
}
