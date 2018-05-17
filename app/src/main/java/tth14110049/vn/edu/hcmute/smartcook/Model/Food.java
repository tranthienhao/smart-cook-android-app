package tth14110049.vn.edu.hcmute.smartcook.Model;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Food {

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Video")
    @Expose
    private String video;
    @SerializedName("CookingTime")
    @Expose
    private String cookingTime;
    @SerializedName("CategoryName")
    @Expose
    private String categoryName;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("Step")
    @Expose
    private List<Step> step = null;
    @SerializedName("Ingredient")
    @Expose
    private List<Ingredient> ingredient = null;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Step> getStep() {
        return step;
    }

    public void setStep(List<Step> step) {
        this.step = step;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }
}