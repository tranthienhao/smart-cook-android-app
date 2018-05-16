package tth14110049.vn.edu.hcmute.smartcook.Controller.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.CardAdapter;
import tth14110049.vn.edu.hcmute.smartcook.R;


public class CardFragment extends Fragment {

    private CardView mCardView;
    private ImageView ivNewsImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_food_suggession, container, false);
        mCardView = view.findViewById(R.id.food_suggesstion_item);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * CardAdapter.MAX_ELEVATION_FACTOR);
        ivNewsImage = view.findViewById(R.id.iv_food_image);
        Picasso.with(getContext())
                .load("http://media.phununews.vn/staticFile/Subject/2018/03/17/photo-0-15210803459392129180943_17234528.jpg")
                .fit().centerCrop()
                .into(ivNewsImage);
        return view;
    }
    public CardView getCardView() {
        return mCardView;
    }
}