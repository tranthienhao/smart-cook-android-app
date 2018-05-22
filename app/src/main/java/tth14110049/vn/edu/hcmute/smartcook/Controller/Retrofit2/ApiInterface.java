package tth14110049.vn.edu.hcmute.smartcook.Controller.Retrofit2;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tth14110049.vn.edu.hcmute.smartcook.Model.Category;
import tth14110049.vn.edu.hcmute.smartcook.Model.Food;
import tth14110049.vn.edu.hcmute.smartcook.Model.Menu;


public interface ApiInterface {
    @GET("get_categories.php")
    Call<List<Category>> getCategories();

    @GET("get_random_foods.php")
    Call<List<Food>> getRandomFoods();

    @GET("get_random_menus.php")
    Call<List<Menu>> getRandomMenus();

    @FormUrlEncoded
    @POST("get_foods.php")
    Call<List<Food>> getFoods(@Field("Skip") int skip);

    @FormUrlEncoded
    @POST("get_menus.php")
    Call<List<Menu>> getMenus(@Field("Skip") int skip);

    @GET("get_weekly_menu.php")
    Call<List<Menu>> getWeeklyMenus();

    @FormUrlEncoded
    @POST("get_food_by_id.php")
    Call<List<Food>> getFoodById(@Field("FoodId") int FoodId);

    @FormUrlEncoded
    @POST("get_food_by_category.php")
    Call<List<Food>> getFoodByCategory(@Field("CategoryId") int CategoryId,
                                       @Field("Skip") int skip);

}