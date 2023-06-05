package data;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Deprecated
public class ID {
    private int id;
    private String tag;
    private int post;
    private String category;
    JsonTypeInfo.Id idType;

    public ID(int id, String tag){
        this.id = id;
        this.tag = tag;
    }

    public ID(int id, String tag, int post){
        this.id = id;
        this.tag = tag;
        this.post = post;
    }

    public ID(int id, int post ,String tag, String category){
        this.id = id;
        this.post = post;
        this.tag = tag;
        this.category = category;
    }

    public ID(int id, String tag, JsonTypeInfo.Id idType) {
        this.id = id;
        this.tag = tag;
        this.idType = idType;
    }
}
