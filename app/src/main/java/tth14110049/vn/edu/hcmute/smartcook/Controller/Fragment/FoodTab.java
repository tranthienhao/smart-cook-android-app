package tth14110049.vn.edu.hcmute.smartcook.Controller.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.CardFragmentPagerAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.FoodAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.ApiClient;
import tth14110049.vn.edu.hcmute.smartcook.Controller.ApiInterface;
import tth14110049.vn.edu.hcmute.smartcook.Model.Food;
import tth14110049.vn.edu.hcmute.smartcook.R;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */

public class FoodTab extends Fragment {
    private RecyclerView recyclerFood;
    private List<Food> listFood = new ArrayList<>();
    private CardFragmentPagerAdapter pagerAdapter;
    private FoodAdapter foodAdapter;
    ApiInterface apiService;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_food_tab, container, false);

        //set the view
        ViewPager viewPager = view.findViewById(R.id.food_suggesstion_viewpager);
        recyclerFood = view.findViewById(R.id.list_food);

        //recyclerFood init
        recyclerFood.setLayoutManager(new GridLayoutManager(getContext(), 1));
        foodAdapter = new FoodAdapter(getContext(),listFood);
        recyclerFood.setNestedScrollingEnabled(false);
        recyclerFood.setAdapter(foodAdapter);

        //
        pagerAdapter = new CardFragmentPagerAdapter(this.getChildFragmentManager(), dpToPixels(2, getContext()));
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setAdapter(pagerAdapter);


        //get ApiInterface
        apiService = ApiClient.getClient().create(ApiInterface.class);
        //set data
        prepareData();

        return view;
    }
    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
    private void prepareData() {
        Call<List<Food>> call = apiService.getFoods();
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>>call, Response<List<Food>> response) {
//                listFood.clear();
                listFood = response.body();
                //Toast.makeText(getContext(),""+listFood.size(),Toast.LENGTH_LONG).show();
                foodAdapter = new FoodAdapter(getContext(),listFood);
                recyclerFood.setAdapter(foodAdapter);
            }
            @Override
            public void onFailure(Call<List<Food>>call, Throwable t) {
                // Log error here since request failed
                Log.e("Error:______________", t.toString());
                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
