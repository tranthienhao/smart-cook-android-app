package tth14110049.vn.edu.hcmute.smartcook.Controller.Activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.MenuAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.WeeklyMenuAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Retrofit2.ApiClient;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Retrofit2.ApiInterface;
import tth14110049.vn.edu.hcmute.smartcook.Model.Menu;
import tth14110049.vn.edu.hcmute.smartcook.R;

public class WeeklyMenuActivity extends AppCompatActivity {
    ImageButton btnBack;
    private RecyclerView recyclerMenu;
    private WeeklyMenuAdapter weeklyMenuAdapter;
    private List<Menu> listMenu = new ArrayList<>();
    private ApiInterface apiService;
    ACProgressFlower loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_menu);

        //set the view
        btnBack= findViewById(R.id.btn_back);
        recyclerMenu = findViewById(R.id.list_menu);

        //setup recycler
        recyclerMenu.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
        recyclerMenu.setNestedScrollingEnabled(false);
        recyclerMenu.setAdapter(weeklyMenuAdapter);

        //get apiService
        apiService = ApiClient.getClient().create(ApiInterface.class);

        //btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //initialize loading dialog
        loadingDialog = new ACProgressFlower.Builder(WeeklyMenuActivity.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text("Loading data")
                .fadeColor(Color.DKGRAY).build();
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setCancelable(false);

        //set data
        prepareData();

    }
    private void prepareData() {
        loadingDialog.show();
        Call<List<Menu>> call = apiService.getWeeklyMenus();
        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                listMenu = response.body();
                weeklyMenuAdapter = new WeeklyMenuAdapter(getBaseContext(),listMenu);
                recyclerMenu.setAdapter(weeklyMenuAdapter);
                //dismiss loading dialog
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                // Log error here since request failed
                Log.e("GET MENU ERROR", t.toString());
                Toast.makeText(getBaseContext(),t.toString(),Toast.LENGTH_LONG).show();
                //dismiss loading dialog
                loadingDialog.dismiss();
            }
        });
    }
}
