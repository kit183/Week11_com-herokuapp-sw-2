package browserfactory;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;

    public void openBrowser(String baseUrl) {
        driver = new ChromeDriver();
        //Launch the URL
        driver.get(baseUrl);
        // Maximise Window
        driver.manage().window().maximize();
        //Implicit Time out
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        //Find the username field element and type username
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        // Find the password field element and type password
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        //Find the login button element and click
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Verify the text “Secure Area”
        String actualText = driver.findElement(By.xpath("//h2[text()=' Secure Area']")).getText();
        String expectedText = "Secure Area";
        Assert.assertEquals("Message not displayed successfully", expectedText, actualText);
    }

    public void closeBrowser() {
        driver.quit();
    }
}
