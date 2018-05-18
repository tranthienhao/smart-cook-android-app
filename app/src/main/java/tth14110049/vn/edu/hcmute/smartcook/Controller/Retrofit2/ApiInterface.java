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

    @GET("get_foods.php")
    Call<List<Food>> getFoods();

    @GET("get_menus.php")
    Call<List<Menu>> getMenus();

    @FormUrlEncoded
    @POST("get_food_by_category.php")
    Call<List<Food>> getFoodByCategory(@Field("CategoryId") int CategoryId);

//    @GET("movie/{id}")
//    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}