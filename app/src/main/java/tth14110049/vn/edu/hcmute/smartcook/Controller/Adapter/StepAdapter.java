package tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tth14110049.vn.edu.hcmute.smartcook.Model.Step;
import tth14110049.vn.edu.hcmute.smartcook.R;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {
    private Context context;
    private List<Step> stepsList;

    public class StepViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNumber, tvContent;

        public StepViewHolder(View view) {
            super(view);
            tvNumber = view.findViewById(R.id.tv_step_number);
            tvContent = view.findViewById(R.id.tv_step_content);
        }
    }


    public StepAdapter(Context context, List<Step> stepsList) {
        this.context = context;
        this.stepsList = stepsList;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_step, parent, false);

        return new StepViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        Step step = stepsList.get(position);
        holder.tvNumber.setText(""+ position);
        holder.tvContent.setText("Đây là bước"+ position);
    }

    @Override
    public int getItemCount() {
        return stepsList.size();
    }
}