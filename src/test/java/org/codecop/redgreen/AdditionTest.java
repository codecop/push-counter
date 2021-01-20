package org.codecop.redgreen;

import org.codecop.redgreen.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import spark.Spark;

import java.time.Duration;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Eine Datenbank mit 1 Tabelle und 2 Spalten
  Branch - Green Pushes
  ev, red pushes auch

API
Eine einzelne HTML Seite die das Board zeigt
  * seite mit meta refresh drinnen

Eine Post URL wo ich Branch und rot/grün pushed kann
Eine Post URL wo ich DB löschen kann

Ein Curl in den Builds

 */

class AdditionTest {
    
    private WebDriver browser;
    
    @BeforeEach
    void startApp() {
        Main.main(new String[0]);
    }

    @BeforeEach
    void createBrowser() {
        browser = new HtmlUnitDriver();
    }

    @AfterEach
    void closeBrowser() {
        browser.quit();
    }
    
    @AfterEach
    void stopApp() {
        Spark.stop();
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
                .withTimeout(Duration.ofSeconds(1)) //
                .until(ExpectedConditions.textMatches(By.id("result"), notEmpty));

        WebElement resultMessage = browser.findElement(By.id("result"));
        String result = resultMessage.getText();
        assertEquals("2 + 3 = 5", result, "Result element web page");
    }
}
