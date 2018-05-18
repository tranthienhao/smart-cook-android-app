package tth14110049.vn.edu.hcmute.smartcook.Controller;

import android.support.v7.widget.CardView;

public interface CardInterface {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}