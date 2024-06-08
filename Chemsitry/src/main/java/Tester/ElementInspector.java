package Tester;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.List;

public class ElementInspector {
    public static void main(String[] args) {
        WebDriverManager.chromiumdriver().setup();
        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the webpage
        driver.get("https://molview.org/");


        // Close the browser
        driver.quit();
    }
}

