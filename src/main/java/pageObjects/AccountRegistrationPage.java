package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
    WebDriver driver;
    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtfirstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtlastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txtTelephone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txtConfirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkdPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//div[@id='content']//h1")
    WebElement msgConfirmation;

    public void setFirstName(String firstName) {
        txtfirstName.sendKeys(firstName);
    }
    public void setLastName(String lastName) {
        txtlastName.sendKeys(lastName);
    }
    public void setEmail(String email) {
        txtEmail.sendKeys(email);
    }
    public void setTelephone(String telephone) {
        txtTelephone.sendKeys(telephone);
    }
    public void setPassword(String password) {
        txtPassword.sendKeys(password);
    }
    public void setConfirmPassword(String confirmPassword) {
        txtConfirmPassword.sendKeys(confirmPassword);
    }
    public void setPrivacyPolicy() {
        chkdPolicy.click();
    }
    public void clickContinue() {
        //sol1
        btnContinue.click();

        //sol2
        //btnContinue.submit();

        //sol3
        //Actions act = new Actions(driver);
        //act.moveToElement(btnContinue).click().perform();

        //sol4
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].click();",btnContinue);

        //sol5
        //btnContinue.sendKeys(Keys.RETURN);

        //SOL6
        //WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
    }

    public String getConfirmationMsg() {
        try{
            return (msgConfirmation.getText());
        }
        catch(Exception e){
            return (e.getMessage());
        }
    }

}
