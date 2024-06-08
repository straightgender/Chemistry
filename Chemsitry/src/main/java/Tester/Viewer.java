package Tester;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.io.PrintStream;

public class Viewer {

    public static ImageIcon[] view(String name, WebDriver driver){
        openStructure(name, driver);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return getStructures(takeScreenshot(driver));
    }
    public static WebDriver setup(){
        System.setOut(new PrintStream(new OutputStream(){@Override public void write(int b){}}));
        System.setErr(new PrintStream(new OutputStream(){@Override public void write(int b){}}));
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(-2000, 0));
        driver.get("https://molview.org/");
        driver.findElement(By.xpath("//*[@id=\"welcome-button-bar\"]/button")).click();
        driver.findElement(By.cssSelector("#action-mp-skeletal-formula")).click();
        System.setOut(System.out);
        System.setErr(System.err);
        return driver;
    }

    static void openStructure(String name, WebDriver driver){
        WebElement searchBar = driver.findElement(By.cssSelector("#search-input"));
        searchBar.clear();
        searchBar.sendKeys(name);
        searchBar.submit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static ImageIcon takeScreenshot(WebDriver driver){
        return new ImageIcon(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
    }

    static ImageIcon getImageFrom(ImageIcon image, int x, int y, int width, int height){
        BufferedImage bufferedImage = new BufferedImage(
                image.getIconWidth(),
                image.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB
        );
        bufferedImage.createGraphics().drawImage(image.getImage(), 0, 0, null);
        return new ImageIcon(bufferedImage.getSubimage(x, y, width, height));
    }

    private static ImageIcon[] getStructures(ImageIcon ss){
        return new ImageIcon[]{get2D(ss), get3D(ss)};
    }

    static ImageIcon get2D(ImageIcon ss){
        return getImageFrom(ss, 82, 145, 600, 613);
    }
    static ImageIcon get3D(ImageIcon ss){
        return getImageFrom(ss, 780, 65, 700, 693);
    }

}
