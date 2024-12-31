package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression","Master"})
    public void verify_account_registration(){

        logger.info("***** Starting TC_0001_Account_Registration_Test *****");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("* Click on My Account Link *");
            hp.clickRegister();
            logger.info("* Click on Register Link *");

            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

            logger.info("* Providing Customer Details *");
            regpage.setFirstName(randomString().toUpperCase());
            regpage.setLastName(randomString().toUpperCase());
            regpage.setEmail(randomString() + "@gmail.com");
            regpage.setTelephone(randomNumber());
            String password = randomAlphaNumber();
            regpage.setPassword(password);
            regpage.setConfirmPassword(password);
            regpage.setPrivacyPolicy();
            regpage.clickContinue();

            logger.info("* Validating Expected Message.. *");
            String confmsg = regpage.getConfirmationMsg();
            if(confmsg.equals("Your Account Has Been Created!")){
                Assert.assertTrue(true);
            }
            else {
                Assert.assertTrue(false);
                logger.error("Test failed");
                logger.debug("Debug logs..");
            }
        }
        catch (Exception e){

            Assert.fail();
        }
        logger.info("***** Finish TC_0001_Account_Registration_Test *****");

    }

}
