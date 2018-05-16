package tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tth14110049.vn.edu.hcmute.smartcook.Controller.Activity.GetFoodByCategoryActivity;
import tth14110049.vn.edu.hcmute.smartcook.Model.Category;
import tth14110049.vn.edu.hcmute.smartcook.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context context;
    private List<Category> categoriesList;

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private ImageView ivCategoryImage;

        public CategoryViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_category_name);
            ivCategoryImage = view.findViewById(R.id.iv_category_image);
        }
    }


    public CategoryAdapter(Context context, List<Category> categoriesList) {
        this.context = context;
        this.categoriesList = categoriesList;

    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);

        // on item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, GetFoodByCategoryActivity.class));
            }
        });
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Category category = categoriesList.get(position);
        holder.tvName.setText(category.getName());
        //load image
        if (category.getImage() != null) {
            Picasso.with(context).load(category.getImage()).fit().centerCrop().into(holder.ivCategoryImage);
        }
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }
}