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

            // safer selectors (less brittle than name=email)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='email'], input[type='email']")));

            driver.findElement(By.cssSelector("input[name='email'], input[type='email']"))
                    .sendKeys("qasim@malik.com");

            driver.findElement(By.name("password"))
                    .sendKeys("abcdefg");

            driver.findElement(By.id("m_login_signin_submit"))
                    .click();

            String errorText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'Incorrect email or password')]")
                    )
            ).getText();

            assertTrue(errorText.contains("Incorrect email or password"));

        } finally {
            driver.quit();
        }
    }
}
