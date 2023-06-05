package check;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

public class CheckJsonRead {
    private static final String PATH = "./rsc/Data/sample.json";
    public static int id = 0;
    public static void main(String[] args) {
        // 1. JSONファイルを読み込む
        File file = new File(PATH);
        FileReader fr;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader;
        reader = new BufferedReader(fr);

        // 2. JSONファイルをパースして、JSON配列に変換する
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
        System.out.println("Array Size -> " + jsonArray.size());
        Random random = new Random();
        id = random.nextInt(jsonArray.size());

        // 3. JSON配列からid : 1のオブジェクトを取得する
        JsonObject jsonObject = null;
        for (JsonElement element : jsonArray) {
            if (element.getAsJsonObject().get("id").getAsInt() == id) {
                jsonObject = element.getAsJsonObject();
                break;
            }
        }

        // 4. id : 1のオブジェクトからtagの値を取得する
        if(jsonObject != null){
            String tag = jsonObject.get("tag").getAsString();
            System.out.println("id" + id + ":" +tag); // example2
        }

    }
}
