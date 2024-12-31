package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
Data is valid - login success - test passed -- logout
Data is valid - login fail - test fail

Data is invalid - login success - test fail -- logout
Data is invalid - login fail - test pass
*/

public class TC003_LoginDDT extends BaseClass {
    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven") //getting data provider form different class
    public void verify_loginDDT(String email, String password, String exp) throws InterruptedException {
        logger.info("***** Staring TC_002_LoginDDT *****");
        try{
            //Home Page
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            Thread.sleep(2000);

            hp.clickLogin();
            Thread.sleep(2000);

            //Login Page
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(password);
            lp.clickLogin();
            Thread.sleep(2000);

            //My Account Page
            MyAccountPage accp = new MyAccountPage(driver);
            boolean targetPage = accp.isMyAccountPageExists();
            //        Assert.assertEquals(targetPage, true, "Login Failed");
//            Assert.assertTrue(targetPage, "Login Failed");

            if (exp.equalsIgnoreCase("Valid")) {
                if (targetPage == true) {
                    Assert.assertTrue(true);
                    accp.clickLogoutLink();
                } else {
                    Assert.assertTrue(false);
                }
            }
            if (exp.equalsIgnoreCase("Invalid")) {
                if (targetPage == true) {
                    accp.clickLogoutLink();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
            logger.info("***** Finished TC_002_LoginDDT *****");
    }
        catch(Exception e){
            Assert.fail();
        }
    }
}
