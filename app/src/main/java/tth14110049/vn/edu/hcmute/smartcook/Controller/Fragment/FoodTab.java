package tth14110049.vn.edu.hcmute.smartcook.Controller.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.CardFragmentPagerAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.FoodAdapter;
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
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_food_tab, container, false);

        //set the view
        ViewPager viewPager = view.findViewById(R.id.food_suggesstion_viewpager);
        recyclerFood = view.findViewById(R.id.list_food);

        //
        pagerAdapter = new CardFragmentPagerAdapter(this.getChildFragmentManager(), dpToPixels(2, getContext()));
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setAdapter(pagerAdapter);

        //Food Adapter
        prepareData();

        return view;
    }
    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
    private void prepareData() {
        Food food = new Food();
        listFood.add(food);
        listFood.add(food);
        listFood.add(food);
        recyclerFood.setLayoutManager(new GridLayoutManager(getContext(), 1));
        foodAdapter = new FoodAdapter(getContext(),listFood);
        recyclerFood.setNestedScrollingEnabled(false);
        recyclerFood.setAdapter(foodAdapter);
    }
}
