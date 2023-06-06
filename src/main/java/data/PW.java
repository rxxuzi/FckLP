package data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PW {
    private static final String path = "./data/PassWord.json";
    public static String getId(){
        File file = new File(path);
        System.out.println("Json -> " + file.getPath());
        FileReader fr;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader;
        reader = new BufferedReader(fr);
        //Parses a JSON file and converts it to a JSON array
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);

        System.out.println("Array Size -> " + jsonArray.size());
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //Retrieve the corresponding id: object from the JSON array
        int id = 0;
        JsonObject jsonObject = null;
        for (JsonElement element : jsonArray) {
            if (element.getAsJsonObject().get("id").getAsInt() == id) {
                jsonObject = element.getAsJsonObject();
                break;
            }
        }
        String name = "example";
        //Get the value of tag from the object with the corresponding id
        if(jsonObject != null){
            name = jsonObject.get("id").getAsString();
            String category = jsonObject.get("category").getAsString();
        }
        return name;
    }
    public static String getPw(){
        return "admin";
    }
}
