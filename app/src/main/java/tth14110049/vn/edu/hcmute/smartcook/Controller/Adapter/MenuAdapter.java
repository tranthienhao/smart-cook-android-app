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

import tth14110049.vn.edu.hcmute.smartcook.Controller.Activity.MenuDetailsActivity;
import tth14110049.vn.edu.hcmute.smartcook.Model.Menu;
import tth14110049.vn.edu.hcmute.smartcook.R;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private Context context;
    private List<Menu> menusList;

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvMeal, tvCookTime;
        private ImageView ivFood1, ivFood2, ivFood3;

        public MenuViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_menu_name);
            tvMeal = view.findViewById(R.id.tv_meal);
            tvCookTime = view.findViewById(R.id.tv_cook_time);
            ivFood1 = view.findViewById(R.id.iv_food1);
            ivFood2 = view.findViewById(R.id.iv_food2);
            ivFood3 = view.findViewById(R.id.iv_food3);
        }
    }


    public MenuAdapter(Context context, List<Menu> menusList) {
        this.context = context;
        this.menusList = menusList;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);

        return new MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        final Menu menu = menusList.get(position);
        holder.tvName.setText(menu.getName());
        holder.tvMeal.setText(menu.getMeal());
        holder.tvCookTime.setText(menu.getCookingTime());
        // on item click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MenuDetailsActivity.class);
                intent.putExtra("Menu", menu);
                context.startActivity(intent);
            }
        });
        //load image
        if (!menu.getFoodImage1().isEmpty()) {
            Picasso.with(context).load(menu.getFoodImage1()).fit().centerCrop().into(holder.ivFood1);
        }
        if (!menu.getFoodImage2().isEmpty()) {
            Picasso.with(context).load(menu.getFoodImage2()).fit().centerCrop().into(holder.ivFood2);
        }else {
            holder.ivFood2.setVisibility(View.GONE);
        }
        if (!menu.getFoodImage3().isEmpty()) {
            Picasso.with(context).load(menu.getFoodImage3()).fit().centerCrop().into(holder.ivFood3);
        }else {
            holder.ivFood3.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return menusList.size();
    }
}