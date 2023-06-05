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

public class CheckDriver {

    private static String word = "Hello World";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search :" );
        word = scanner.nextLine();

        //ドライバーをセットアップ
        WebDriverManager.chromedriver().setup();
        //ドライバーのpathを指定
        System.setProperty("webdriver.chrome.driver","./opt/chromedriver_win32/chromedriver.exe");
        //ドライバーのインスタンス化
        WebDriver driver = new ChromeDriver();

        // googleの検索ページに遷移します。
        driver.get("https://www.google.com/?hl=ja");
        //タイトルを取得
        System.out.println(driver.getTitle());

        // ブラウザ操作の待ち時間を指定します。
        Duration duration = Duration.ofMillis(300);
        driver.manage().timeouts().implicitlyWait(duration.toMillis(), TimeUnit.MILLISECONDS);

        // クラス属性で検索し、検索ボックスを特定
        WebElement textBox = driver.findElement(By.className("gLFyf"));

        // 検索ボックスに入力し、検索を実行
        textBox.sendKeys(word);
        textBox.sendKeys(Keys.ENTER);


        // セッションを終了します。
        // ※コメントアウトしていますが、外すと最後にブラウザを閉じるようになります。
//        driver.quit();

        System.out.println("Done");
    }
}