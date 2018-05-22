package tth14110049.vn.edu.hcmute.smartcook.Controller.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Activity.GetFoodByCategoryActivity;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Activity.MainActivity;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.FoodSuggessionFragmentPagerAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.FoodAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Retrofit2.ApiClient;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Retrofit2.ApiInterface;
import tth14110049.vn.edu.hcmute.smartcook.Model.Food;
import tth14110049.vn.edu.hcmute.smartcook.R;

import static tth14110049.vn.edu.hcmute.smartcook.Controller.Activity.MainActivity.internetErrorLayout;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */

public class FoodTab extends Fragment {
    ViewPager foodSuggessionPager;
    private Button btnViewMore;
    private RecyclerView recyclerFood;
    private List<Food> listFood = new ArrayList<>();
    private FoodSuggessionFragmentPagerAdapter pagerAdapter;
    private FoodAdapter foodAdapter;
    ApiInterface apiService;
    private View view;
    SwipeRefreshLayout swipeRefreshLayout;
    private ACProgressFlower loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_food_tab, container, false);

        //set the view
        foodSuggessionPager = view.findViewById(R.id.food_suggesstion_viewpager);
        recyclerFood = view.findViewById(R.id.list_food);
        btnViewMore = view.findViewById(R.id.btn_view_more);
        swipeRefreshLayout =  view.findViewById(R.id.swipeRefreshLayout);

        //SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                prepareData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        //initialize loading dialog
        loadingDialog = new ACProgressFlower.Builder(getContext())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text("Loading data")
                .fadeColor(Color.DKGRAY).build();
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setCancelable(false);

        //recyclerFood init
        recyclerFood.setLayoutManager(new GridLayoutManager(getContext(), 1));
        foodAdapter = new FoodAdapter(getContext(),listFood);
        recyclerFood.setNestedScrollingEnabled(false);
        recyclerFood.setAdapter(foodAdapter);

        //get ApiInterface
        apiService = ApiClient.getClient().create(ApiInterface.class);

        //set data
        prepareData();

        //btnViewMore click
        btnViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), GetFoodByCategoryActivity.class));
            }
        });

        return view;
    }
    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
    private void prepareData() {
        loadingDialog.show();
        Call<List<Food>> call = apiService.getRandomFoods();
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>>call, Response<List<Food>> response) {
                //listFood.clear();
                listFood = response.body();
                //Toast.makeText(getContext(),""+listFood.size(),Toast.LENGTH_LONG).show();
                foodAdapter = new FoodAdapter(getContext(),listFood);
                recyclerFood.setAdapter(foodAdapter);
            }
            @Override
            public void onFailure(Call<List<Food>>call, Throwable t) {
                // Log error here since request failed
                Log.e("GET FOOD ERROR", t.toString());
                internetErrorLayout.setVisibility(View.VISIBLE);
                //Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
        // get suggession food
        Call<List<Food>> callSuggessFood = apiService.getRandomFoods();
        callSuggessFood.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>>call, Response<List<Food>> response) {
                listFood = response.body();
                //food suggession adapter
                pagerAdapter = new FoodSuggessionFragmentPagerAdapter(FoodTab.this.getChildFragmentManager()
                        , dpToPixels(2, getContext()), listFood);
                ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(foodSuggessionPager, pagerAdapter);
                fragmentCardShadowTransformer.enableScaling(true);
                foodSuggessionPager.setPageTransformer(false, fragmentCardShadowTransformer);
                foodSuggessionPager.setAdapter(pagerAdapter);
                loadingDialog.dismiss();
            }
            @Override
            public void onFailure(Call<List<Food>>call, Throwable t) {
                // Log error here since request failed
                Log.e("GET FOOD ERROR", t.toString());
                internetErrorLayout.setVisibility(View.VISIBLE);
                loadingDialog.dismiss();
                //Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
