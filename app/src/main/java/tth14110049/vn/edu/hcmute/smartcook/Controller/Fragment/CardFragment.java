package tth14110049.vn.edu.hcmute.smartcook.Controller.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import tth14110049.vn.edu.hcmute.smartcook.Controller.Activity.FoodDetailsActivity;
import tth14110049.vn.edu.hcmute.smartcook.Controller.CardInterface;
import tth14110049.vn.edu.hcmute.smartcook.Model.Food;
import tth14110049.vn.edu.hcmute.smartcook.R;


public class CardFragment extends Fragment {
    private Food food;
    private CardView mCardView;
    private ImageView ivFoodImage;
    private TextView tvFoodName, tvCategoryName, tvCookTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_food_suggession, container, false);
        mCardView = view.findViewById(R.id.food_suggesstion_item);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * CardInterface.MAX_ELEVATION_FACTOR);

        //get food
        food = (Food) getArguments().getSerializable("Food");

        //set the view
        ivFoodImage = view.findViewById(R.id.iv_food_image);
        tvFoodName = view.findViewById(R.id.tv_food_name);
        tvCategoryName = view.findViewById(R.id.tv_category_name);
        tvCookTime = view.findViewById(R.id.tv_cook_time);

        //set text
        tvFoodName.setText(food.getName());
        tvCategoryName.setText(food.getCategoryName());
        tvCookTime.setText(food.getCookingTime());


        //set image
        if(food.getImage() != null)
            Picasso.with(getContext())
                    .load(food.getImage())
                    .fit().centerCrop()
                    .into(ivFoodImage);

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FoodDetailsActivity.class);
                intent.putExtra("Food", food);
                startActivity(intent);
            }
        });

        return view;
    }


    public CardView getCardView() {
        return mCardView;
    }
}