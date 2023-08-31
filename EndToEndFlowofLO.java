package Tests;

import org.apache.commons.mail.Email;
import org.openqa.selenium.*;
import java.time.Duration;
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
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;



public class DepChrome {
    private WebDriver driver;
    private String testUrl = "https://www.laptopoutlet.co.uk/special-bundle-offer-free-docking-station-backpack-with-msi-prestige-14-evo-a12m-043uk-laptop4090-9s7-14c612-043-docking-station-backpack.html"; // Your URL


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
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        sendEmailNotification();

        /*

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button"))).click();
        //WebElement addToBasketButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
        //addToBasketButton.click();

        WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(60));
        wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='top-cart-btn-checkout']"))).click();
        // Capture a screenshot
        captureScreenshot("ProceedToCheckout.png");




        WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(240));
        wait2.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".guest-user"))).click();


        driver.findElement(By.xpath("//input[@id='customer-email']")).sendKeys("zeeshan@fivetech.co.uk");

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



        //driver.findElement(By.xpath("//div[@class='col-mp mp-12 billing-address-shipping']//span[contains(text(),'Next')]")).click();
        //driver.findElement(By.cssSelector("#checkoutSteps > div.col-mp.mp-6.mp-sm-5.mp-xs-12 > div.row-mp > div.col-mp.mp-12.billing-address-shipping > button")).click();



        //driver.findElement(By.className("action primary next-step-checkout")).click();
        driver.findElement(By.cssSelector("div[class='col-mp mp-12 billing-address-shipping'] button[value='Next'] span")).click();

       driver.findElement(By.id("s_method_tablerate_bestway")).click();

        //driver.findElement(By.className("action primary next-step-checkout")).click();
        driver.findElement(By.cssSelector("div[id='checkout-step-shipping_method'] button[value='Next']")).click();

        //driver.findElement(By.xpath("//div[@id='checkout-step-shipping_method']//span[contains(text(),'Next')]")).click();

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

    //@AfterClass
    //public void teardown() {
      //  if (driver != null) {
       //     driver.quit();
      //  }
    //}

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

    // Method to send an email
    private void sendEmailNotification() {
        // Email configuration
        String host = "smtp.office365.com";
        String port = "587";
        String username = "zeeshan@example.com";
        String password = "your password here";

        String from = "zeeshan@example.com";
        String to = "zeeshan@example.com";
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
    }





}
