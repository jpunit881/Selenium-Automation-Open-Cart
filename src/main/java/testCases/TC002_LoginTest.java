package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
    @Test(groups = {"Sanity","Master"})
    public void verify_Login() throws InterruptedException {
        logger.info("***** Staring TC_002_LoginTest *****");

        try {
            //Home Page
            HomePage hp = new HomePage(driver);
            logger.info("** Open Home Page **");
            hp.clickMyAccount();
            Thread.sleep(2000);
            logger.info("** Home Page is opened **");

            logger.info("** Click Login Page **");
            hp.clickLogin();
            Thread.sleep(2000);
            logger.info("** Login Page is opened **");

            //Login Page
            logger.info("** Set Login Page Details **");
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(p.getProperty("email"));
            lp.setPassword(p.getProperty("password"));
            lp.clickLogin();
            Thread.sleep(2000);
            logger.info("** User id Logged IN **");

            //My Account Page
            logger.info("** My Account page is opening **");
            MyAccountPage accp = new MyAccountPage(driver);
            boolean targetPage = accp.isMyAccountPageExists();
            //        Assert.assertEquals(targetPage, true, "Login Failed");
            Assert.assertTrue(targetPage, "Login Failed");
            logger.info("** verify that the My Account page is opened or not **");
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("***** Finished TC_002_LoginTest *****");
    }
}
