package tth14110049.vn.edu.hcmute.smartcook.Controller.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.FoodAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.MenuAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Retrofit2.ApiClient;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Retrofit2.ApiInterface;
import tth14110049.vn.edu.hcmute.smartcook.Model.Food;
import tth14110049.vn.edu.hcmute.smartcook.Model.Menu;
import tth14110049.vn.edu.hcmute.smartcook.R;

public class AllMenuActivity extends AppCompatActivity {

    ImageButton btnBack;
    TextView tvMenu;
    private RecyclerView recyclerMenu;
    private List<Menu> listMenu = new ArrayList<>();
    private MenuAdapter menuAdapter;
    ApiInterface apiService;
    SwipeRefreshLayout swipeRefreshLayout;
    int skip = 0; // giá trị ban đầu để skip
    boolean isNoMoreFood = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_menu);

        //set the view
        btnBack = findViewById(R.id.btn_back);
        tvMenu = findViewById(R.id.tv_toolbar_menu);
        recyclerMenu = findViewById(R.id.list_menu);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        //SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                skip = 0;
                isNoMoreFood = false;
                listMenu.clear();
                prepareData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        //get ApiInterface
        apiService = ApiClient.getClient().create(ApiInterface.class);

        //recyclerFood init
        recyclerMenu.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
        menuAdapter = new MenuAdapter(getBaseContext(), listMenu);
        recyclerMenu.setNestedScrollingEnabled(true);
        recyclerMenu.setAdapter(menuAdapter);
        recyclerMenu.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // kiểm tra recycler đã kéo tới item cuối chưa
                if (!recyclerView.canScrollVertically(1)) {
                    skip = skip + 5;
                    // nếu đã không còn món ăn. không load nữa
                    if (!isNoMoreFood) {
                        prepareData();
                    }
                }
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
        //initialize loading dialog
        final ACProgressFlower loadingDialog;
        loadingDialog = new ACProgressFlower.Builder(AllMenuActivity.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text("Loading data")
                .fadeColor(Color.DKGRAY).build();
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setCancelable(false);
        loadingDialog.show();


        tvMenu.setText("Tất cả thực đơn");
        Call<List<Menu>> call = apiService.getMenus(skip);
        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                if(response != null) {
                    if (!response.body().isEmpty()) {
                        listMenu.addAll(response.body());
                        menuAdapter = new MenuAdapter(getBaseContext(), listMenu);
                        recyclerMenu.setAdapter(menuAdapter);
                        //scroll to bottom
                        int position = recyclerMenu.getAdapter().getItemCount() - 1 - response.body().size();
                        if (skip != 0)
                            recyclerMenu.scrollToPosition(position);
                    } else {
                        Toast.makeText(getBaseContext(), "Không còn thực đơn", Toast.LENGTH_LONG).show();
                        isNoMoreFood = true;
                    }
                }
                //dismiss loading dialog
                loadingDialog.dismiss();
            }
            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                // Log error here since request failed
                Log.e("Error:______________", t.toString());
                Toast.makeText(getBaseContext(), t.toString(), Toast.LENGTH_LONG).show();
                //dismiss loading dialog
                loadingDialog.dismiss();
            }
        });
    }
}