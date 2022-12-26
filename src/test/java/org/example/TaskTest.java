package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.get("https://ceracare.co.uk");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testSearchResults() {
        WebElement cookies = driver.findElement(By.id("onetrust-accept-btn-handler"));
        cookies.click();

        WebElement jobs = driver.findElement(By.xpath("//a[@href='/jobs']"));
        jobs.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        assertEquals("Jobs – Apply for a care job at Cera", driver.getTitle());

        WebElement search = driver.findElement(By.className("location-search-input"));
        search.sendKeys("Trafford");

        List<WebElement> searchResult = driver.findElements(By.xpath("//div[@class='locationSearchInput_suggestion__QLl_v']/span"));
           boolean found=false;
        for (int i = 0; i <searchResult.size() ; i++) {
            found=searchResult.get(i).getText().contains("Trafford Park");
            if(found){
                break;
            }

        }
        assertEquals(true,found);
       // assertTrue(searchResult.get(0).getText().contains("Manchester"));
    }

    // Add negative test scenario
    @Test
    public void negativeTestSearchResults() {
        WebElement cookies = driver.findElement(By.id("onetrust-accept-btn-handler"));
        cookies.click();

        WebElement jobs = driver.findElement(By.xpath("//a[@href='/jobs']"));
        jobs.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        assertEquals("Jobs – Apply for a care job at Cera", driver.getTitle());

        WebElement search = driver.findElement(By.className("location-search-input"));
        search.sendKeys("Trafford");

        List<WebElement> searchResult = driver.findElements(By.xpath("//div[@class='locationSearchInput_suggestion__QLl_v']/span"));
        boolean found=false;
        for (int i = 0; i <searchResult.size() ; i++) {
            found=searchResult.get(i).getText().contains("Trafford Garden");
            if(found){
                break;
            }
        }
        assertEquals(false,found);

    }
    }

