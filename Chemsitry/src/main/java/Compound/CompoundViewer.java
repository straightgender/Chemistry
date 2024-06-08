package Compound;

import Tester.Viewer;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.io.PrintStream;

public class CompoundViewer {


    private static void setup(){
        System.setOut(new PrintStream(new OutputStream() {@Override public void write(int b){}}));
        System.setErr(new PrintStream(new OutputStream() {@Override public void write(int b) {}}));
        WebDriverManager.chromedriver().setup();
        System.setErr(System.err);
        System.setOut(System.out);
    }

    private static ImageIcon get(String name){
        setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://molview.org/");
        driver.findElement(By.cssSelector("#welcome-button-bar > button")).click();
        WebElement search = driver.findElement(By.cssSelector("input[name=q]"));
        search.sendKeys(name);
            search.submit();
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                System.out.println("Error: "+ e.getMessage());
            }
            driver.findElement(By.id("action-mp-skeletal-formula")).click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Error: "+ e.getMessage());
            }
        return new ImageIcon(
                ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
    }

    private static ImageIcon fromRegion(ImageIcon img, int x, int y, int width, int height){
        BufferedImage image = new BufferedImage(
                img.getImage().getWidth(null),
                img.getImage().getHeight(null),
                BufferedImage.TYPE_INT_ARGB
        );
        image.getGraphics().drawImage(img.getImage(), 0,0, null);
        return new ImageIcon(
                image.getSubimage(x, y, width, height)
        );
    }

    public static ImageIcon resize(ImageIcon image){
        BufferedImage resize = new BufferedImage(600, 500, BufferedImage.TYPE_INT_ARGB);
        resize.createGraphics().drawImage(image.getImage(), 0, 0, 600, 500, null);
        return new ImageIcon(resize);
    }

    protected static ImageIcon[] viewOf(String compoundName){
        ImageIcon ss = get(compoundName);
        return new ImageIcon[]{get2D(ss), get3D(ss)};
    }

    private static ImageIcon get2D(ImageIcon imageIcon){
        return resize(fromRegion(
                imageIcon, 82, 145, 600, 628));
    }

    private static ImageIcon get3D(ImageIcon imageIcon){
        return resize(fromRegion(
                imageIcon, 780, 65, 700, 708
        ));
    }

    public static void summon(){
        SwingUtilities.invokeLater(GUI::new);
    }

}
