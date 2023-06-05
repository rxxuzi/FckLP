package driver;

import fast.Log;
import fast.Tag;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DriverRun {
    private static final String Path = "./rsc/pic/";
    private static final int maxPage = 3;
    public static int maxImage = 5;
    private static int imageCounter = 0;
    private static boolean blockNSFW  = false;
    private static boolean isSuccess = true;
    public static String tag = "999_(hansode)";
    public static String url = "https://betabooru.donmai.us/posts?tags=wsfw&z=1";
    private static int totalImage = 0;


    public static void main(String[] args) {

        System.out.println(Tag.translate(tag));

        url = "https://betabooru.donmai.us/posts?tags=" + Tag.translate(tag) + "&z=1";
        Log.clean();


        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver","./opt/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //ウィンドウのサイズを最小化する
//        driver.manage().window().setSize(new Dimension(0, 0));



        driver.get(url);

        System.out.println(driver.getTitle());

        wait(driver,700);

        //カウント用変数
        int counter = 0;
        //重複しないように設定
        String nowUrl;

        //ページの切り替え
        for(int i = 0 ; i < maxPage ; i ++ ){
            if(imageCounter >= maxImage){
                break;
            }

            nowUrl = driver.getCurrentUrl();

            //imgタグのついているものをListにまとめる
            List<WebElement> imageLinks = driver.findElements(By.className("post-preview-link"));
            System.out.println("Link Size" + imageLinks.size());

            //それぞれのimageUrlに対してスクレイピング
            for (WebElement link : imageLinks) {
                if(imageCounter >= maxImage){
                    break;
                }
                //プレビュー画像に付いていたリンクを抜き取る
                String linkUrl = link.getAttribute("href");

                if (linkUrl != null && linkUrl.startsWith("http")) {
                    //リンク先へ移動
                    driver.navigate().to(linkUrl);
                    WebElement preview = driver.findElement(By.id("post-info-rating"));
                    String rating = preview.getText().split(":")[1].trim();

                    System.out.print(rating+", ");

                    WebElement image = driver.findElement(By.id("image"));
                    //src属性からurlを取得
                    String imageUrl = image.getAttribute("src");

                    System.out.println(imageUrl);
                    String fileName;

                    boolean isVideo = false;
                    //set file name
                    if(imageUrl.endsWith("gif")) {
                        fileName = totalImage + Tag.translate(tag) + ".gif";
                    }else if(imageUrl.endsWith("webm")){
                        fileName = totalImage + Tag.translate(tag) + ".webm";
                        isVideo = true;
                    }else{
                        fileName = totalImage + Tag.translate(tag) + ".png";
                    }

                    if(rating.equals("Explicit") && blockNSFW){
                        System.out.println("Blocked");
                        Log.write(fileName + " ("  + rating  +") : " + Log.getTime() + " : Blocked");
                    }else{
                        //ログに保存
                        Log.write(fileName + " ("  + rating  +") : " + Log.getTime() + " : " + imageUrl);
                        //もし保存する内容がgifだったらcontinue

                        if(isVideo){
                            saveVideo(imageUrl,fileName);
                        }else{
                            saveImage(imageUrl, fileName);
                        }
                        //保存
                        totalImage ++;
                    }

                    //前のページに戻る
                    driver.navigate().back();
                    wait(driver,50);

                }
            }

            wait(driver,750);

            try {
                WebElement nextPageBtn = driver.findElement(By.className("paginator-next"));
                nextPageBtn.click();
            }catch (Exception e){
                System.out.println("No Next Page");
                break;
            }


            if(nowUrl.equals(driver.getCurrentUrl())){
                System.out.println("Same Page");
                break;
            }
        }


        wait(driver, 700);
        //終了
        driver.quit();

        isSuccess = maxImage == imageCounter;

        System.out.println("MaX Picture -> " + maxImage);
        System.out.println("GeT Picture -> " + imageCounter);

    }

    /**
     * 画像を保存するメソッド
     * 保存先のディレクトリ指定はこのclassのstatic変数Pathで行う
     * @param imgURL スクレイプするURL
     * @param fileName 保存するファイル名
     */
    private static void saveImage(String imgURL , String fileName){
        try{
            URL url = new URL(imgURL);
            InputStream inputStream = url.openStream();
            BufferedInputStream in = new BufferedInputStream(inputStream);
            FileOutputStream fos = new FileOutputStream(Path + fileName);
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fos.write(dataBuffer, 0, bytesRead);
            }
            Thread.sleep(10);

            in.close();
            fos.close();
        }catch (IOException e){
            Log.error(e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        imageCounter ++;
    }

    /**
     * 画像を保存するメソッド
     * 保存先のディレクトリ指定はこのclassのstatic変数Pathで行う
     * @param imgURL スクレイプするURL
     * @param fileName 保存するファイル名
     */
    private static void saveVideo(String imgURL , String fileName){
        try{
            System.out.println("Get Video");
            URL url = new URL(imgURL);
            InputStream inputStream = url.openStream();
//            OutputStream outputStream = new FileOutputStream(Path + fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(Path + fileName);
            byte[] buffer = new byte[1_048_576];
            int bytesRead ;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer,0,bytesRead);
//                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
//            outputStream.close();
            fileOutputStream.close();
        }catch (IOException e){

            Log.error(e.getMessage());
            e.printStackTrace();
        }
        imageCounter ++;
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

    public static void blockExplicit(boolean blockNSFW) {
        DriverRun.blockNSFW = blockNSFW;
    }

    public static void search(String tag){
        DriverRun.tag = tag;
    }
}