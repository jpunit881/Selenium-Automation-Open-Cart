package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties ;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Regression", "Sanity", "Master", "DataDriven"})
    @Parameters({"os", "browser"})
    public void setup(String os, String browser) throws IOException {
        //Loding config.properties file
        FileReader file = new FileReader("./src//test//resources//config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
            String huburl ="http://192.168.8.165:4444/wd/hub";

            DesiredCapabilities cap = new DesiredCapabilities();

            //os
            if(os.equalsIgnoreCase("windows")){
                cap.setPlatform(Platform.WIN10);
            }
            else if(os.equalsIgnoreCase("mac")){
                cap.setPlatform(Platform.MAC);
            }
            else if(os.equalsIgnoreCase("linux")){
                cap.setPlatform(Platform.LINUX);
            }
            else{
                System.out.println("No matching os");
                return;
            }

            //browser
            switch (browser.toLowerCase()) {
                case "chrome": cap.setBrowserName("chrome");
                    break;
                case "firefox": cap.setBrowserName("firefox");
                    break;
                case "edge": cap.setBrowserName("edge");
                    break;
                case "safari": cap.setBrowserName("safari");
                    break;
                default:
                    System.out.println("Invalid browser: " + browser);
                    driver = new ChromeDriver();
                    break;
            }
            WebDriver driver = new RemoteWebDriver(new URL("http://192.168.8.165:4444/wd/hub"), cap);
        }


        if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
            switch (browser.toLowerCase()) {
                case "chrome": driver = new ChromeDriver();
                    break;
                case "firefox": driver = new FirefoxDriver();
                    break;
                case "edge": driver = new EdgeDriver();
                    break;
                case "safari": driver = new SafariDriver();
                    break;
                default:
                    System.out.println("Invalid browser: " + browser);
                    driver = new ChromeDriver();
                    break;
            }
        }


        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//        driver.get("https://tutorialsninja.com/demo/");
        driver.get(p.getProperty("appURL")); //reading url from properties file
        driver.manage().window().maximize();
    }
    @AfterClass(groups = {"Regression", "Sanity", "Master", "DataDriven"})
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    public String randomString(){
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }
    public String randomNumber(){
        String generatedNumber= RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }
    public String randomAlphaNumber(){
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        String generatedNumber= RandomStringUtils.randomNumeric(10);
        return (generatedString+"@"+generatedNumber);
    }
    public static String captureScreen(WebDriver driver, String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        String targetFilePath = "D:\\Java\\ecommerceOpenCart\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }
}
