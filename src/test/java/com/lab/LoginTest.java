package com.lab;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class AppTest {

    @Test
    public void test_login_with_incorrect_credentials() {

        // ChromeDriver path inside Docker image
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("http://103.139.122.250:4000/");

            driver.findElement(By.name("email")).sendKeys("qasim@malik.com");
            driver.findElement(By.name("password")).sendKeys("abcdefg");
            driver.findElement(By.id("m_login_signin_submit")).click();

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            String errorText = driver.findElement(
                    By.xpath("/html/body/div/div/div[1]/div/div/div/div[2]/form/div[1]")
            ).getText();

            assertTrue(errorText.contains("Incorrect email or password"));

        } finally {
            driver.quit();
        }
    }
}
