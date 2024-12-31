package utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

import javax.sql.DataSource;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener{
    public ExtentSparkReporter sparkReporter; //UI of the report
    public ExtentReports extent; //populate common info on the report
    public ExtentTest test; //creating test case entries in the report and update status of the test methods

    String repName;

    public void onStart(ITestContext testContext) {
        /*
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = new Date();
        String currentdatetimestamp = df.format(dt);
        */

        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());

        repName = "Test-Report-" + timestamp +".html";
        sparkReporter = new ExtentSparkReporter("D:\\Java\\ecommerceOpenCart\\reports\\" + repName);

        sparkReporter.config().setDocumentTitle("OpenCart Automation Report"); //Title of the Report
        sparkReporter.config().setReportName("OpenCart Functional Testing"); // name of the report
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customer");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()){
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result){
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); // to display groups in reports
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }


    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imgPath = BaseClass.captureScreen(BaseClass.driver, result.getName()); // Pass driver instance here
            test.addScreenCaptureFromPath(imgPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result){
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP,result.getName() + " got Skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context){
        extent.flush();

        String pathOfExtentReport = "D:\\Java\\ecommerceOpenCart\\reports\\" + repName;
        File extentReport = new File(pathOfExtentReport);

        try{
            Desktop.getDesktop().browse(extentReport.toURI());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        /*
        try {
            URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);

            // Create the email message
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("punit16102001@gmail.com", "16102001@gmail.com"));
            email.setSSLOnConnect(true);
            email.setFrom("punitjain@cyntexa.com"); //Sender
            email.setSubject("Test Result");
            email.setMsg("Please find Attached Report ...");
            email.addTo("punit16102001@gmail.com"); //Reciver
            email.attach(url, "extent report", "Please Check Report...");
            email.send(); // send the email
        }
        catch (Exception e){
            e.printStackTrace();
        }
        */
    }
}