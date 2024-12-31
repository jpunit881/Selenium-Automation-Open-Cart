package SeleniumGrid;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGrid {
    public static void main(String args[]) throws MalformedURLException, InterruptedException {

        //The URL will be IP Address of HUB Machine + HUB Port + /wd/hub
        //http://192.168.8.165:4444/wd/hub or http://localhost:4444/wd/hub


        String huburl ="http://192.168.8.165:4444/wd/hub";

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setPlatform(Platform.WIN10); //cap.setPlatform(Platform.MAC);
        cap.setBrowserName("chrome"); //cap.setBrowserName("MicrosoftEdge");

        WebDriver driver = new RemoteWebDriver(new URL(huburl), cap);

        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
