package tth14110049.vn.edu.hcmute.smartcook.Controller.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.FoodAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Retrofit2.ApiClient;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Retrofit2.ApiInterface;
import tth14110049.vn.edu.hcmute.smartcook.Model.Food;
import tth14110049.vn.edu.hcmute.smartcook.R;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */

public class GetFoodByCategoryActivity extends AppCompatActivity{
    ImageButton btnBack;
    TextView tvCategoryName;
    int categoryId;
    String categoryName;
    private RecyclerView recyclerFood;
    private List<Food> listFood = new ArrayList<>();
    private FoodAdapter foodAdapter;
    ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_food_by_category);

        //set the view
        btnBack = findViewById(R.id.btn_back);
        tvCategoryName = findViewById(R.id.tv_toolbar_category_name);
        recyclerFood = findViewById(R.id.list_food);

        //get extra
        Intent intent = getIntent();
        categoryId = intent.getIntExtra("CategoryId",-1);
        categoryName = intent.getStringExtra("CategoryName");

        //get ApiInterface
        apiService = ApiClient.getClient().create(ApiInterface.class);

        //recyclerFood init
        recyclerFood.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
        foodAdapter = new FoodAdapter(getBaseContext(),listFood);
        recyclerFood.setNestedScrollingEnabled(false);
        recyclerFood.setAdapter(foodAdapter);


        //btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //set data
        prepareData();
    }

    private void prepareData() {
        Call<List<Food>> call;
        if(categoryId > 0){
            //set data with food by category
            tvCategoryName.setText(categoryName);
            call = apiService.getFoodByCategory(categoryId);
        }else{
            //set data with all food
            tvCategoryName.setText("Tất cả món ăn");
            call = apiService.getFoods();
        }
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                listFood = response.body();
                //Toast.makeText(getContext(),""+listFood.size(),Toast.LENGTH_LONG).show();
                foodAdapter = new FoodAdapter(getBaseContext(),listFood);
                recyclerFood.setAdapter(foodAdapter);
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                // Log error here since request failed
                Log.e("Error:______________", t.toString());
                Toast.makeText(getBaseContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
