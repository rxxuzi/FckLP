package check;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.Scanner;

public class CheckAccess {

    private static final String[] website = {
            "https://betabooru.donmai.us/",
            "https://google.com/",
            "https://www.pixiv.net/",
    };

    //偽装User-Agent
    public final static String userAgent  = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393";
    public final static String userAgent2 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36";
    //偽装プロキシサーバー
    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.example.com", 8080));
    //停止時間
    public final static int sleepTime = 300;
    //フラグ
    public static boolean Accessible = false;
    public static boolean ProxyAccessible = false;
    public static boolean usingJsoup = true;
    public static boolean usingProxy = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q;
        do{
            System.out.println("0:danbooru 1:Google 2:Pixiv");
            q = scanner.nextInt();
        }while (!(q < 3));
        if(usingProxy){
            //プロキシ追加
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("{ProxyServerUser ID}", "{ProxyServerUser Password}".toCharArray());
                }
            });
        }


        if(usingJsoup){
            try {
                Connection.Response response = Jsoup.connect(website[q]).ignoreHttpErrors(true).execute();
                int statusCode = response.statusCode();
                AccessChecker(statusCode, response);
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!Accessible) {
                //User Agentを偽装して叩けるかチェック
                try {
                    Connection.Response response = Jsoup.connect(website[q]).userAgent(userAgent2).ignoreHttpErrors(true).execute();
                    System.out.println("Set User Agent ->" + userAgent);
                    int statusCode = response.statusCode();
                    AccessChecker(statusCode, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void AccessChecker(int statusCode, Connection.Response response) {
        if (statusCode >= 200 && statusCode < 300) {
            System.out.println("アクセス可能なURLです。");
            Accessible = true;
        } else if (statusCode >= 300 && statusCode < 400) {
            System.out.println("リダイレクトが発生しました。");
            System.out.println("リダイレクト先: " + response.header("Location"));
        } else if (statusCode >= 400 && statusCode < 500) {
            System.out.println("アクセス権がありません。");
        } else if (statusCode >= 500 && statusCode < 600) {
            System.out.println("サーバ側でエラーが発生しました。");
        } else {
            System.out.println("不明なエラーが発生しました。ステータスコード: " + statusCode);
        }
    }

    /**
     * statusCodeのチェック
     * アクセス可能かどうかを返す
     * @param statusCode 投げられたステータスコード [200 ~ 600]
     */
    public static boolean SimpleAccessChecker(int statusCode) {
        if (statusCode >= 200 && statusCode < 300) {
            System.out.println("アクセス可能なURLです。");
            return true;
        } else if (statusCode >= 300 && statusCode < 400) {
            System.out.println("リダイレクトが発生しました。");
            return false;
        } else if (statusCode >= 400 && statusCode < 500) {
            System.out.println("アクセス権がありません。");
            return false;
        } else if (statusCode >= 500 && statusCode < 600) {
            System.out.println("サーバ側でエラーが発生しました。");
            return false;
        } else {
            System.out.println("不明なエラーが発生しました。ステータスコード: " + statusCode);
            return false;
        }
    }

}
