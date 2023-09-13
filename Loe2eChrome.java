package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.mail.EmailException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Loe2eChrome {
    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    private String testUrl = "https://www.laptopoutlet.co.uk/testsku123456789.html"; // Your URL


    @BeforeClass
    public void setup() {
        extent = ExtentManager.getInstance();
        test = ExtentManager.createTest(getClass().getSimpleName());
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testBrowser() {

        test.info("Laptop Outlet Automated End to End Flow");
        driver.get(testUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        test.pass("Test passed"); // Report that the test passed





        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(240));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button"))).click();
        captureScreenshot("001_AddToCart.png");

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(240));
        wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='top-cart-btn-checkout']"))).click();
        captureScreenshot("002_ProceedToCheckout.png");

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(240));
        wait2.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".guest-user"))).click();
        captureScreenshot("003_GuestUser.png");

        driver.manage().timeouts().implicitlyWait(3, java.util.concurrent.TimeUnit.SECONDS);


        driver.findElement(By.xpath("//input[@id='customer-email']")).sendKeys("zeeshan.1101214@gmail.com");

        // Locate the dropdown element
        //Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='QGILQ4K']")));
        // Select a value from the dropdown by visible text
        //dropdown.selectByVisibleText("Mr.");


        driver.findElement(By.name("firstname")).sendKeys("Zeeshan Tester");


        driver.findElement(By.name("lastname")).sendKeys("Hasni");


        driver.findElement(By.name("street[0]")).sendKeys("239 Downing Street");


        driver.findElement(By.name("city")).sendKeys("London");


        driver.findElement(By.name("postcode")).sendKeys("IG8 8HF");


        driver.findElement(By.name("telephone")).sendKeys("7874548745457");
        captureScreenshot("004_DataPopulatedForShipping.png");

        //Element is present but having permanent Overlay.
        //Use JavascriptExecutor to send the click directly on the element.

        WebElement ele = driver.findElement(By.xpath("//div[@class='col-mp mp-12 billing-address-shipping']//span[contains(text(),'Next')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);
        captureScreenshot("005_DeliveryAddressNextPressed.png");

        driver.findElement(By.id("s_method_tablerate_bestway")).click();
        captureScreenshot("006_RadioButtonSelected.png");

        //Element is present but having permanent Overlay.
        //Use JavascriptExecutor to send the click directly on the element.

        WebElement ele2 = driver.findElement(By.xpath("//div[@id='checkout-step-shipping_method']//span[contains(text(),'Next')]"));
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", ele2);
        captureScreenshot("007_ShippingMethodsNextPressed.png");









        //sendEmailNotification();




/*


        //driver.findElement(By.xpath("//input[@id='braintree_paypal']")).click();
        driver.findElement(By.id("braintree_paypal")).click();

        //driver.findElement(By.xpath("//div[@id='terms-and-conditions-below']//input[@id='agreement__1']")).click();
        driver.findElement(By.xpath("//div[@id='terms-and-conditions-below']//input[@id='agreement__1']")).click();

        //driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();
        driver.findElement(By.className("recaptcha-checkbox-border")).click();

        driver.findElement(By.linkText("Place Order")).click();

        captureScreenshot("PlaceButtonClicked.png");

    */


    }





    @AfterClass
    public void teardown() {
        //if (driver != null) {
            //driver.quit();
        //}

        extent.flush(); // Flush the extent report to generate the HTML report

    }














    private void captureScreenshot(String fileName) {
        String screenshotPath = "C:\\Users\\LENOVO\\IdeaProjects\\LOE2E\\Screenshots\\" + fileName; // Specify the complete path
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(screenshotFile, new File(screenshotPath));
            System.out.println("Screenshot saved: " + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }










/*
    private void sendEmailNotification() {
        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("zeeshan.1101214@gmail.com", "ffszmxvzemmejads"));
        email.setStartTLSEnabled(true);
        email.setSSLOnConnect(true);
        email.setFrom("zeeshan.1101214@gmail.com");
        email.setSubject("Test Email");
        email.setMsg("This is a test email.");
        email.addTo("zeeshanhusnainh@gmail.com");
        email.send();

*/








    /*



    }



    // Method to send an email
    private void sendEmailNotification() {
        // Email configuration
        String host = "smtp.gmail.com";
        String port = "465";
        String username = "f80475255@gmail.com";
        String password = "ffszmxvzemmejads";
        //String password = System.getenv("P_W");

        // Use the password in your Selenium script
        //String password = ".....";
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Adjust the timeout as needed
        //WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordFieldId")));
        //passwordField.sendKeys(password);

        String from = "f80475255@gmail.com";
        String to = "f80475255@gmail.com";
        String subject = "Test Automation Completed";
        String message = "Your Selenium test has completed.";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage mimeMessage = new MimeMessage(session);

            // Set From: header field
            mimeMessage.setFrom(new InternetAddress(from));

            // Set To: header field
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            mimeMessage.setSubject(subject);

            // Now set the actual message
            mimeMessage.setText(message);

            // Send the message
            Transport.send(mimeMessage);

            System.out.println("Email notification sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }   */







}
