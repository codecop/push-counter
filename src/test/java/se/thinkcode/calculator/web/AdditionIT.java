package se.thinkcode.calculator.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.regex.Pattern;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdditionIT {
    
    private WebDriver browser;

    @BeforeEach
    void createBrowser() {
        browser = new HtmlUnitDriver();
    }

    @AfterEach
    void closeBrowser() {
        browser.quit();
    }

    @Test
    void should_add_2_and_3_and_get_5() {
        // TODO the address should be configurable from environment variables
        browser.get("http://127.0.0.1:4567/");

        WebElement firstTextField = browser.findElement(By.id("first"));
        WebElement secondTextField = browser.findElement(By.id("second"));
        WebElement addButton = browser.findElement(By.id("addButton"));

        firstTextField.sendKeys("2");
        secondTextField.sendKeys("3");
        addButton.click();

        Pattern notEmpty = Pattern.compile("=");
        new FluentWait<>(browser) //
                .withMessage("Post not finished") //
                .withTimeout(1, SECONDS) //
                .until(ExpectedConditions.textMatches(By.id("result"), notEmpty));

        WebElement resultMessage = browser.findElement(By.id("result"));
        String result = resultMessage.getText();
        assertEquals("2 + 3 = 5", result, "Result element web page");
    }
}
