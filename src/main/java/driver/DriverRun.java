package driver;

import fast.Log;
import fast.Tag;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DriverRun {
    private static boolean isSuccess = true;
    public static String tag = "999_(hansode)";
    public static String url = "https://w1.linguaporta.jp/user/seibido/index.php";
    private static int totalImage = 0;
    private static WebElement we = null;


    public static void main(String[] args) {

        System.out.println(Tag.translate(tag));

        Log.clean();

        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver","./opt/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://w1.linguaporta.jp/user/seibido/index.php");
        //ウィンドウのサイズを最小化する
//        driver.manage().window().setSize(new Dimension(0, 0));

        Log.write("Nc ->");

        WebElement textBox = driver.findElement(By.name("id"));
        textBox.sendKeys("4534fukuda");

        WebElement passwordBox = driver.findElement(By.name("password"));
        passwordBox.sendKeys("vFe8drF9");

        WebElement loginButton = driver.findElement(By.id("btn-login"));
        loginButton.click();

        WebElement studyForm = driver.findElement(By.name("Study"));
        studyForm.click();

        WebElement unitList = driver.findElement(By.className("btn-reference-select"));
        unitList.click();

        WebElement list = driver.findElement(By.id("cn1106cn20cn30"));
        list.click();

        var we = driver.findElements(By.className("col-unitname"));

        var webElement = driver.findElements(By.className("btn-study"));
        System.out.println(webElement.size());
        webElement.get(0).click();


//        WebElement study = driver.findElement(By.className("btn-study"));
//        study.click();
//        System.out.println(study.getTagName());

        driver.get(url);


        System.out.println(driver.getTitle());

        wait(driver,700);

        System.gc();
        boolean Ans = true;
        int page = 0;
        for(int i = 0 ; i < 10 ; i++){
            page++;
        }

        WebElement txtBox = driver.findElement(By.id("tabindex1"));
        WebElement ansButton = driver.findElement(By.id("ans_submit"));
//        WebElement ansButton = driver.findElement(By.id("btn-logout"));
        wait(driver,10);
        WebElement que = driver.findElement(By.id("qu02"));
        System.out.println("Question : " + que.getText());
        WebElement a = driver.findElement(By.tagName("a"));
        Log.write(a.getText() + que.getText());
        txtBox.sendKeys("t");
//        page ++;
        wait(driver,1200);
//        ansButton.click();

        wait(driver,2200);
        System.out.println(driver.getTitle() + " count : " + page);


    }

    /**
     *
     * @param webDriver WebDriverからの変数
     * @param time　ブラウザ操作の待ち時間
     */
    private static void wait(WebDriver webDriver , long time) {
        Duration duration = Duration.ofMillis(time);
        webDriver.manage().timeouts().implicitlyWait(duration.toMillis(), TimeUnit.MILLISECONDS);
    }

    public static boolean getIsSuccess() {
        return isSuccess;
    }


    public static void search(String tag){
        DriverRun.tag = tag;
    }
}