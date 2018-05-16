package tth14110049.vn.edu.hcmute.smartcook.Controller.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import tth14110049.vn.edu.hcmute.smartcook.R;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */

public class MenuDetailsActivity extends AppCompatActivity{
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //set the view
        btnBack = findViewById(R.id.btn_back);

        //btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
