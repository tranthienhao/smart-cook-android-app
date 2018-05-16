package tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tth14110049.vn.edu.hcmute.smartcook.Model.Ingredient;
import tth14110049.vn.edu.hcmute.smartcook.R;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    private Context context;
    private List<Ingredient> ingredientList;

    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvAmount;

        public IngredientViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_ingredient_name);
            tvAmount = view.findViewById(R.id.tv_amount);
        }
    }


    public IngredientAdapter(Context context, List<Ingredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient, parent, false);

        return new IngredientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
//        holder.tvName.setText(""+ position);
//        holder.tvAmount.setText("Đây là bước"+ position);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }
}