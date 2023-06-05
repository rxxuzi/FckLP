package check;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static check.CheckAccess.SimpleAccessChecker;

public class CheckHTTPRequest {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36";
    protected static final String[] REQUEST_METHOD = {"GET", "POST", "PUT", "DELETE", "PATCH", "HEAD"};
    protected static final String web_URL = "https://www.google.com/search?q=villV&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjR6eyKpd7-AhVrnFYBHU61DDMQ_AUoAXoECAEQAw&biw=1504&bih=860&dpr=1.5";
    protected static final String web_URL2 = "https://betabooru.donmai.us/";

    /**
     * @Version alpha
     * @since 1.0
     */
    public static void main(String[] args) {
        URL url;
        try {
            url = new URL(web_URL);
            //HttpURLConnectionオブジェクトを生成
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //リクエストメソッドを設定する。
            con.setRequestMethod(REQUEST_METHOD[0]); // GET、POST、PUTなど
            //リクエストヘッダーを設定する。
            con.setRequestProperty("User-Agent", USER_AGENT); // ユーザーエージェントの設定
            con.setRequestProperty("Custom-Header", "value");
            //接続
            con.connect();

            int statusCode = con.getResponseCode(); // ステータスコードの取得
            // レスポンスボディの取得
            System.out.print("ステータスコード: " + statusCode);
            //ステータスコードが200の時に実行
            if(SimpleAccessChecker(statusCode)){
                System.out.println("getResponseMessage -> " + con.getResponseMessage());
                System.out.println("getContentLength   -> " + con.getContentLength());
                System.out.println("getContentType     -> "  + con.getContentType());
                System.out.println("getContentEncoding -> " + con.getContentEncoding());
                System.out.println("getURL             -> " + con.getURL());
                System.out.println("getRequestMethod   -> " + con.getRequestMethod());
                System.out.println("getRequestProperty -> " + con.getRequestProperty("User-Agent"));
                System.out.println("getHeaderFields    ->\r\n" + con.getHeaderFields());
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
