package tth14110049.vn.edu.hcmute.smartcook.Controller;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tth14110049.vn.edu.hcmute.smartcook.Model.Category;


public interface ApiInterface {
    @GET("get_categories.php")
    Call<List<Category>> getCategories();

//    @GET("movie/{id}")
//    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}