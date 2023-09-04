package Tests;

import org.apache.commons.mail.Email;
import org.openqa.selenium.*;
import java.time.Duration;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import javax.mail.*;
//import javax.mail.internet.*;
import java.util.Properties;



public class DepChrome {
    private WebDriver driver;
    private String testUrl = "https://www.laptopoutlet.co.uk/testsku123456789.html"; // Your URL


    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testBrowser() {

        //driver.get("https://laptopoutlet.co.uk");
        driver.get(testUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        //cookies accept
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        //sendEmailNotification();


        //add to cart button
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button"))).click();

        
        //proceed to checkout
        WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(60));
        wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='top-cart-btn-checkout']"))).click();


        // Capture a screenshot
        //captureScreenshot("ProceedToCheckout.png");

        
        //click on guest user
        WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(240));
        wait2.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".guest-user"))).click();

        driver.manage().timeouts().implicitlyWait(3, java.util.concurrent.TimeUnit.SECONDS);

        //populate field with email
        driver.findElement(By.xpath("//input[@id='customer-email']")).sendKeys("zeeshan@fivetech.co.uk");

        // Locate the dropdown element
        //Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='QGILQ4K']")));
        // Select a value from the dropdown by visible text
        //dropdown.selectByVisibleText("Mr.");

        //populate field with First Name
        driver.findElement(By.name("firstname")).sendKeys("Zeeshan Tester");

        //populate field with Last name
        driver.findElement(By.name("lastname")).sendKeys("Hasni");

        //populate field with Street Address    
        driver.findElement(By.name("street[0]")).sendKeys("239 Downing Street");

        //populate field with City
        driver.findElement(By.name("city")).sendKeys("London");

        ////populate field with Postcode
        driver.findElement(By.name("postcode")).sendKeys("IG8 8HF");

        //populate field with Telephone
        driver.findElement(By.name("telephone")).sendKeys("7874548745457");


        //Element is present but having permanent Overlay.
        //Use JavascriptExecutor to send the click directly on the element.

        
        //Click on Next Button for delivery address
        WebElement ele = driver.findElement(By.xpath("//div[@class='col-mp mp-12 billing-address-shipping']//span[contains(text(),'Next')]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", ele);

        
        // click on radio button of shipment fee
        driver.findElement(By.id("s_method_tablerate_bestway")).click();


        //Element is present but having permanent Overlay.
        //Use JavascriptExecutor to send the click directly on the element.

        
        // click on button of Shipment Method
        WebElement ele2 = driver.findElement(By.xpath("//div[@id='checkout-step-shipping_method']//span[contains(text(),'Next')]"));
        JavascriptExecutor executor1 = (JavascriptExecutor)driver;
        executor1.executeScript("arguments[0].click();", ele2);

/*


        // Click on button of Paypal
        //driver.findElement(By.xpath("//input[@id='braintree_paypal']")).click();
        driver.findElement(By.id("braintree_paypal")).click();


        // Click on checkbox of Terms and Conditions
        //driver.findElement(By.xpath("//div[@id='terms-and-conditions-below']//input[@id='agreement__1']")).click();
        driver.findElement(By.xpath("//div[@id='terms-and-conditions-below']//input[@id='agreement__1']")).click();


        // Click on Recaptcha Checkbox
        //driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();
        driver.findElement(By.className("recaptcha-checkbox-border")).click();


        // Click on Place Order Button
        driver.findElement(By.linkText("Place Order")).click();

        captureScreenshot("PlaceButtonClicked.png");

    */

    }

    //@AfterClass
    //public void teardown() {
    //  if (driver != null) {
    //     driver.quit();
    //  }
    //}

/*
    private void captureScreenshot(String fileName) {
        String screenshotPath = "F:\\Projects\\LO\\LOE2E\\Screenshots\\" + fileName; // Specify the complete path
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(screenshotFile, new File(screenshotPath));
            System.out.println("Screenshot saved: " + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


*/

/*


    // Method to send an email
    private void sendEmailNotification() {
        // Email configuration
        String host = "smtp.office365.com";
        String port = "587";
        String username = "UN: zeeshan@fivetech.co.uk";
        String password = "Password of email";

        String from = "zeeshan@fivetech.co.uk";
        String to = "zeeshan@fivetech.co.uk";
        String subject = "Test Automation Completed";
        String message = "Your Selenium test has completed.";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

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

 */





}
