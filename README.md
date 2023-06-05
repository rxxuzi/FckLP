# F*ck Lingua Porta

## Version
使用したVersion

+ JDK 20.0.1
+ Google chrome v113.0.5672.64
+ Jsoup v1.15.4
+ Gson v2.10
+ webdrivermanager v5.3.1
+ selenium-java v3.141.59

コマンドラインで次のコマンドを実行してJdkがver20以上であることを確認してください

```shell
javac -version
java -version
```

以下のサイトからChromeのバージョンに合わせたchromedriverをダウンロードしてoptフォルダに解凍して入れてください

[ChromeDriver](https://chromedriver.chromium.org/downloads)

そしたら ./src/main/java/driver/Driver.java内のwebdriverをインスタンス化する際のpathを変更してください

あらかじめ、Chromeで[ログイン](https://w1.linguaporta.jp/user/seibido/index.php)しておいてください。