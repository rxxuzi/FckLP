package check;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CheckDriver2 {

    private static String user = "Hello World";
    private static String url = "https://www.twitter.com/";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search : " );
        user = scanner.nextLine();

        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver","./opt/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get(url + user);
        System.out.println(driver.getTitle());
        Duration duration = Duration.ofMillis(1300);
        driver.manage().timeouts().implicitlyWait(duration.toMillis(), TimeUnit.MILLISECONDS);
        WebElement tweet = driver.findElement(By.tagName("span"));
        tweet.click();
//        driver.quit();

        System.out.println("Done");
    }
}