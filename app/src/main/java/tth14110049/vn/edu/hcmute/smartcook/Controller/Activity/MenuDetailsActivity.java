package tth14110049.vn.edu.hcmute.smartcook.Controller.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import tth14110049.vn.edu.hcmute.smartcook.Model.Menu;
import tth14110049.vn.edu.hcmute.smartcook.R;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */

public class MenuDetailsActivity extends AppCompatActivity{
    ImageButton btnBack;
    TextView tvToolbarMenuName, tvMenuName, tvDescription, tvCookTime;
    ImageView ivFood1, ivFood2,ivFood3;
    Menu menuDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details);

        //get extra
        Intent intent = getIntent();
        menuDetails = (Menu) intent.getSerializableExtra("Menu");

        //set the view
        btnBack = findViewById(R.id.btn_back);
        tvToolbarMenuName = findViewById(R.id.tv_toolbar_menu_name);
        tvCookTime = findViewById(R.id.tv_cook_time);
        tvMenuName = findViewById(R.id.tv_menu_name);
        tvDescription = findViewById(R.id.tv_menu_description);
        ivFood1 = findViewById(R.id.iv_food1);
        ivFood2 = findViewById(R.id.iv_food2);
        ivFood3 = findViewById(R.id.iv_food3);

        //open foodDetail onclick image
        ivFood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),FoodDetailsActivity.class);
                i.putExtra("FoodId", menuDetails.getFoodId1());
                startActivity(i);
            }
        });

        ivFood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),FoodDetailsActivity.class);
                i.putExtra("FoodId", menuDetails.getFoodId2());
                startActivity(i);
            }
        });

        ivFood3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),FoodDetailsActivity.class);
                i.putExtra("FoodId", menuDetails.getFoodId3());
                startActivity(i);
            }
        });

        //btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //set data
        prepareData();
    }

    private void prepareData() {
        //set text
        tvToolbarMenuName.setText(menuDetails.getName());
        tvMenuName.setText(menuDetails.getName());
        tvCookTime.setText(menuDetails.getCookingTime());
        tvDescription.setText(menuDetails.getDescription());

        if(menuDetails.getFoodId3() < 1){
            ivFood3.setVisibility(View.GONE);
        }
        if(menuDetails.getFoodId2() < 1){
            ivFood2.setVisibility(View.GONE);
        }
        //set food image
        if (!menuDetails.getFoodImage1().isEmpty())
            Picasso.with(getBaseContext())
                    .load(menuDetails.getFoodImage1())
                    .fit()
                    .centerCrop()
                    .into(ivFood1);
        if (!menuDetails.getFoodImage2().isEmpty())
            Picasso.with(getBaseContext())
                    .load(menuDetails.getFoodImage2())
                    .fit()
                    .centerCrop()
                    .into(ivFood2);
        if (!menuDetails.getFoodImage3().isEmpty())
            Picasso.with(getBaseContext())
                    .load(menuDetails.getFoodImage3())
                    .fit()
                    .centerCrop()
                    .into(ivFood3);
    }

}
