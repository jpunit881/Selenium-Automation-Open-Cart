package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement linkmyAccount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement linkRegister;

    @FindBy(xpath = "//a[@href='https://tutorialsninja.com/demo/index.php?route=account/login']")
    WebElement linkLogin;


    public void clickMyAccount() {
        linkmyAccount.click();
    }
    public void clickRegister() {
        linkRegister.click();
    }
    public void clickLogin() {
        linkLogin.click();
    }
}
