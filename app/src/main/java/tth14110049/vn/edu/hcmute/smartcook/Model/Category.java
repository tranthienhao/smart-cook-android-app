package tth14110049.vn.edu.hcmute.smartcook.Model;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */
import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("Id")
    private int id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Image")
    private String image;

    public Category() {
    }

    public Category(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
