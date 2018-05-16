package tth14110049.vn.edu.hcmute.smartcook.Controller.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.FoodAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Model.Food;
import tth14110049.vn.edu.hcmute.smartcook.R;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */

public class GetFoodByCategoryActivity extends AppCompatActivity{
    ImageButton btnBack;
    TextView tvCategoryName;
    private RecyclerView recyclerFood;
    private List<Food> listFood = new ArrayList<>();
    private FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_food_by_category);

        //set the view
        btnBack = findViewById(R.id.btn_back);
        tvCategoryName = findViewById(R.id.tv_toolbar_category_name);
        recyclerFood = findViewById(R.id.list_food);

        //setdata

        prepareData();

        //btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void prepareData() {
        Food food = new Food();
        listFood.add(food);
        listFood.add(food);
        listFood.add(food);
        recyclerFood.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
        foodAdapter = new FoodAdapter(getBaseContext(),listFood);
        recyclerFood.setNestedScrollingEnabled(false);
        recyclerFood.setAdapter(foodAdapter);
    }
}
