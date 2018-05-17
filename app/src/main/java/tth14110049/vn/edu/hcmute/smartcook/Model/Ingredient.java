package tth14110049.vn.edu.hcmute.smartcook.Model;

/**
 * Created by Hao Tran Thien on 5/12/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredient {

    @SerializedName("IngredientName")
    @Expose
    private String ingredientName;
    @SerializedName("Amount")
    @Expose
    private float amount;
    @SerializedName("Unit")
    @Expose
    private String unit;

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}