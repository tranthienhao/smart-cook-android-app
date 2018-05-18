package tth14110049.vn.edu.hcmute.smartcook.Model;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Menu implements Serializable{

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("CookingTime")
    @Expose
    private String cookingTime;
    @SerializedName("FoodId1")
    @Expose
    private int foodId1;
    @SerializedName("FoodId2")
    @Expose
    private int foodId2;
    @SerializedName("FoodId3")
    @Expose
    private int foodId3;
    @SerializedName("FoodImage1")
    @Expose
    private String foodImage1;
    @SerializedName("FoodImage2")
    @Expose
    private String foodImage2;
    @SerializedName("FoodImage3")
    @Expose
    private String foodImage3;
    @SerializedName("Meal")
    @Expose
    private String meal;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getFoodId1() {
        return foodId1;
    }

    public void setFoodId1(int foodId1) {
        this.foodId1 = foodId1;
    }

    public int getFoodId2() {
        return foodId2;
    }

    public void setFoodId2(int foodId2) {
        this.foodId2 = foodId2;
    }

    public int getFoodId3() {
        return foodId3;
    }

    public void setFoodId3(int foodId3) {
        this.foodId3 = foodId3;
    }

    public String getFoodImage1() {
        return foodImage1;
    }

    public void setFoodImage1(String foodImage1) {
        this.foodImage1 = foodImage1;
    }

    public String getFoodImage2() {
        return foodImage2;
    }

    public void setFoodImage2(String foodImage2) {
        this.foodImage2 = foodImage2;
    }

    public String getFoodImage3() {
        return foodImage3;
    }

    public void setFoodImage3(String foodImage3) {
        this.foodImage3 = foodImage3;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

}