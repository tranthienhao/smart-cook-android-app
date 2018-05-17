package tth14110049.vn.edu.hcmute.smartcook.Model;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Step implements Serializable{

    @SerializedName("Number")
    @Expose
    private int number;
    @SerializedName("Content")
    @Expose
    private String content;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}