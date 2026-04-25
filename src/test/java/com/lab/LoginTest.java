package com.lab;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    @Test
    public void test_login_with_incorrect_credentials() {

        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("http://103.139.122.250:4000/");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait for the email field by ID
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));

            // Enter email
            driver.findElement(By.id("email"))
                    .sendKeys("qasim@malik.com");

            // Enter password
            driver.findElement(By.id("password"))
                    .sendKeys("abcdefg");

            // Click the submit button using CSS Selector
            driver.findElement(By.cssSelector("button[type='submit']"))
                    .click();

            // Wait for the "Failed to fetch" error message to appear
            String errorText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'Failed to fetch')]")
                    )
            ).getText();

            // Assert that the error text is what we expect
            assertTrue(errorText.contains("Failed to fetch"));

        } finally {
            driver.quit();
        }
    }
}
