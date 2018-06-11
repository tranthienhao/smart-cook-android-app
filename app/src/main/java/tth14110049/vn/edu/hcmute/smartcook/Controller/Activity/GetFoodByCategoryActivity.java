package tth14110049.vn.edu.hcmute.smartcook.Controller.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
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

public class GetFoodByCategoryActivity extends AppCompatActivity {
    ImageButton btnBack;
    TextView tvCategoryName;
    int categoryId;
    String categoryName;
    private RecyclerView recyclerFood;
    private List<Food> listFood = new ArrayList<>();
    private FoodAdapter foodAdapter;
    ApiInterface apiService;
    SwipeRefreshLayout swipeRefreshLayout;
    int skip = 0; // giá trị ban đầu để skip
    boolean isNoMoreFood = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_food_by_category);

        //set the view
        btnBack = findViewById(R.id.btn_back);
        tvCategoryName = findViewById(R.id.tv_toolbar_category_name);
        recyclerFood = findViewById(R.id.list_food);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        //SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                skip = 0;
                isNoMoreFood = false;
                listFood.clear();
                prepareData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        //get extra
        Intent intent = getIntent();
        categoryId = intent.getIntExtra("CategoryId", -1);
        categoryName = intent.getStringExtra("CategoryName");

        //get ApiInterface
        apiService = ApiClient.getClient().create(ApiInterface.class);

        //recyclerFood init
        recyclerFood.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
        foodAdapter = new FoodAdapter(getBaseContext(), listFood);
        recyclerFood.setNestedScrollingEnabled(false);
        recyclerFood.setAdapter(foodAdapter);
        recyclerFood.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // kiểm tra recycler đã kéo tới item cuối chưa
                if (!recyclerView.canScrollVertically(1)) {
                    skip = skip + 5;
                    // nếu đã không còn món ăn. không load nữa
                    if (!isNoMoreFood) {
                        prepareData();
                    }
                }
            }
        });


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
        //initialize loading dialog
        final ACProgressFlower loadingDialog;
        loadingDialog = new ACProgressFlower.Builder(GetFoodByCategoryActivity.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text("Loading data")
                .fadeColor(Color.DKGRAY).build();
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setCancelable(false);
        loadingDialog.show();

        Call<List<Food>> call;
        if (categoryId > 0) {
            //set data with food by category
            tvCategoryName.setText(categoryName);
            call = apiService.getFoodByCategory(categoryId, skip);
        } else {
            //set data with all food
            tvCategoryName.setText("Tất cả món ăn");
            call = apiService.getFoods(skip);
        }
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                if (!response.body().isEmpty()) {
                    listFood.addAll(response.body());
                    foodAdapter = new FoodAdapter(getBaseContext(), listFood);
                    recyclerFood.setAdapter(foodAdapter);
                    //scroll to bottom
                    int position = recyclerFood.getAdapter().getItemCount() - 1 - response.body().size();
                    if (skip != 0)
                        recyclerFood.scrollToPosition(position);
                } else {
                    Toast.makeText(getBaseContext(), "Không còn món ăn", Toast.LENGTH_LONG).show();
                    isNoMoreFood = true;
                }
                //dismiss loading dialog
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                // Log error here since request failed
                Log.e("Error:______________", t.toString());
                Toast.makeText(getBaseContext(), t.toString(), Toast.LENGTH_LONG).show();
                //dismiss loading dialog
                loadingDialog.dismiss();
            }
        });
    }
}