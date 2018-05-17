package tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import tth14110049.vn.edu.hcmute.smartcook.Controller.Activity.FoodDetailsActivity;
import tth14110049.vn.edu.hcmute.smartcook.Model.Food;
import tth14110049.vn.edu.hcmute.smartcook.R;


public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private Context context;
    private List<Food> foodsList;

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvDescription, tvCategory, tvCookTime;
        private ImageView ivFoodImage;

        public FoodViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_food_name);
            tvCategory = view.findViewById(R.id.tv_category_name);
            tvDescription = view.findViewById(R.id.tv_food_description);
            tvCookTime = view.findViewById(R.id.tv_cook_time);
            ivFoodImage = view.findViewById(R.id.iv_food_image);
        }
    }


    public FoodAdapter(Context context, List<Food> foodsList) {
        this.context = context;
        this.foodsList = foodsList;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false);

        return new FoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, final int position) {
        final Food food = foodsList.get(position);
        holder.tvName.setText(food.getName());
        holder.tvDescription.setText(food.getDescription());
        holder.tvCategory.setText(food.getCategoryName());
        holder.tvCookTime.setText(food.getCookingTime());

        // on item click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FoodDetailsActivity.class);
                intent.putExtra("Food", food);
                context.startActivity(intent);
//                Toast.makeText(context,food.getStep().get(0).getContent(),Toast.LENGTH_LONG).show();
            }
        });

        //load image
        if (food.getImage() != null) {
            Picasso.with(context).load(food.getImage()).fit().centerCrop().into(holder.ivFoodImage);
        }
    }

    @Override
    public int getItemCount() {
        return foodsList.size();
    }
}