package com.scicom.esol.tests.tmp;

import java.util.concurrent.TimeUnit;

import com.scicom.esol.pageobjects.tmp.TestHome;
import com.scicom.esol.pageobjects.tmp.TestLogin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestLoginFlow {

    WebDriver driver;
    TestLogin objLogin;
    TestHome objHomePage;
    String driverPath = "C:\\\\MyWork\\geckodriver-v0.21.0-win64\\geckodriver.exe";

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://demo.guru99.com/V4/");
    }

    /**
     * This test case will login in http://demo.guru99.com/V4/
     * Verify login page title as guru99 bank
     * Login to application
     * Verify the home page using Dashboard message
     */
    @Test(priority=0)
    public void test_Home_Page_Appear_Correct() {

        //Create Login Page object
        objLogin = new TestLogin(driver);

        //Verify login page title
        String loginPageTitle = objLogin.getLoginTitle();
        Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

        //login to application
        objLogin.loginToTest("mgr123", "mgr!23");

        // go the next page
        objHomePage = new TestHome(driver);

        //Verify home page
        Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mgr123"));
    }

    @AfterTest
    public  void endSession(){
        driver.close();
    }
}
