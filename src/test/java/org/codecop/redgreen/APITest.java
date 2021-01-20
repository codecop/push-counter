package org.codecop.redgreen;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.restassured.RestAssured;
import spark.Spark;

/*
API
Eine einzelne HTML Seite die das Board zeigt
  * seite mit meta refresh drinnen

 */

class APITest {

    private static final int PORT = 4567;
    
    WebDriver browser;

    @BeforeEach
    void startApplication() {
        Main.main(new String[0]);
    }

    @BeforeEach
    void openBrowser() {
        browser = new HtmlUnitDriver();
    }

    @AfterEach
    void closeBrowser() {
        browser.quit();
    }

    @AfterEach
    void stopApplication() {
        Spark.stop();
    }

    @Test
    void shouldRecordEntries() {
        RestAssured. //
            given(). //
                port(PORT). //
            when(). //
                params("build", "green"). //
                get("/record/branch"). //
            then(). //
                assertThat(). //
                    statusCode(201). //
                assertThat(). //
                    body(containsString("OK"));
    }

    @Test
    void shouldClearEntries() {
        RestAssured. //
            given(). //
                port(PORT). //
            when(). //
                get("/clear"). //
            then(). //
                assertThat(). //
                    statusCode(200); 
        // TODO this is not really a test
    }

    @Test @Disabled
    void should_add_2_and_3_and_get_5() {
        // TODO the address should be configurable from environment variables
        browser.get("http://127.0.0.1:" + PORT + "/");

        WebElement firstTextField = browser.findElement(By.id("first"));
        WebElement secondTextField = browser.findElement(By.id("second"));
        WebElement addButton = browser.findElement(By.id("addButton"));

        firstTextField.sendKeys("2");
        secondTextField.sendKeys("3");
        addButton.click();

        Pattern notEmpty = Pattern.compile("=");
        new FluentWait<>(browser) //
                .withMessage("Post not finished") //
                .withTimeout(Duration.ofSeconds(1)) //
                .until(ExpectedConditions.textMatches(By.id("result"), notEmpty));

        WebElement resultMessage = browser.findElement(By.id("result"));
        String result = resultMessage.getText();
        assertEquals("2 + 3 = 5", result, "Result element web page");
    }
}
