package com.lab;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    @Test
    public void testLoginInvalid() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        driver.get("http://103.139.122.250:4000/");

        driver.findElement(By.name("email"))
                .sendKeys("test@example.com");

        driver.findElement(By.name("password"))
                .sendKeys("wrongpassword");

        driver.findElement(By.id("m_login_signin_submit"))
                .click();

        String pageSource = driver.getPageSource();

        assertTrue(pageSource.contains("Incorrect"));

        driver.quit();
    }
}