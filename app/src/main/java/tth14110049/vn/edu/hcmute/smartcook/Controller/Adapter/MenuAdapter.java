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
        private TextView tvName, tvDescription, tvCategory;
        private ImageView ivMenuImage;

        public MenuViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_menu_name);
            tvCategory = view.findViewById(R.id.tv_category_name);
            tvDescription = view.findViewById(R.id.tv_menu_description);
            ivMenuImage = view.findViewById(R.id.iv_menu_image);
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

        // on item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, MenuDetailsActivity.class));
            }
        });
        return new MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        Menu menu = menusList.get(position);
        holder.tvName.setText("THỊT KHO HỘT VỊT");
        holder.tvDescription.setText("Thịt kho có lẽ đã quá quen thuộc...");
        holder.tvCategory.setText("Món kho");

//        //load image
//        if (menu.getMenuImage() != null) {
//            Picasso.with(context).load(menu.getMenuImage()).fit().centerCrop().into(holder.ivMenuImage);
//        }
    }

    @Override
    public int getItemCount() {
        return menusList.size();
    }
}