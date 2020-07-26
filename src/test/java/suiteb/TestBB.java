package suiteb;

import org.testng.annotations.Test;

import testbase.TestBase;

public class TestBB  extends TestBase{

	
	@Test(groups = {"smoke","browsergroup2"})
	public void testBB() throws InterruptedException {
		log("Starting BB");
		Thread.sleep(2000);
		log("Ending BB");
	}
}
