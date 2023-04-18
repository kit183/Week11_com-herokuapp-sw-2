package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {

        openBrowser(baseUrl);
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

    @Test
    public void verifyTheUsernameErrorMessage() {
        //Find the username field element and type username
        driver.findElement(By.id("username")).sendKeys("tomsmith1");
        // Find the password field element and type password
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        //Find the login button element and click
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Verify the error message
        String expectedMessage = "Your username is invalid!\n×";
        WebElement actualTextMessageElement = driver.findElement(By.xpath("//div[contains(@id, 'flash')]"));
        String actualMessage = actualTextMessageElement.getText();

        //Validate actual and expected
        Assert.assertEquals("Error message not displayed", expectedMessage, actualMessage);
    }

    @Test
    public void verifyThePasswordErrorMessage() {
        //Find the username field element and type username
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        // Find the password field element and type password
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");
        //Find the login button element and click
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Verify the text “Secure Area”
        String actualText = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        String expectedText = "Your password is invalid!\n" + "×";
        Assert.assertEquals("Error message not displayed", expectedText, actualText);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
