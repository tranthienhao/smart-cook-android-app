package tth14110049.vn.edu.hcmute.smartcook.Controller.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.MenuAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Retrofit2.ApiClient;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Retrofit2.ApiInterface;
import tth14110049.vn.edu.hcmute.smartcook.Model.Menu;
import tth14110049.vn.edu.hcmute.smartcook.R;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */

public class MenuTab extends Fragment {
    private RecyclerView recyclerMenu;
    private List<Menu> listMenu = new ArrayList<>();
    private MenuAdapter menuAdapter;
    private View view;
    private ApiInterface apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_menu_tab, container, false);

        //set the view
        recyclerMenu = view.findViewById(R.id.list_menu);

        //setup recycler
        recyclerMenu.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerMenu.setNestedScrollingEnabled(false);
        recyclerMenu.setAdapter(menuAdapter);

        //get apiService
        apiService = ApiClient.getClient().create(ApiInterface.class);

        //set data
        prepareData();

        return view;
    }
    private void prepareData() {
        Call<List<Menu>> call = apiService.getMenus();
        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                listMenu = response.body();
                menuAdapter = new MenuAdapter(getContext(),listMenu);
                recyclerMenu.setAdapter(menuAdapter);
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                // Log error here since request failed
                Log.e("GET MENU ERROR", t.toString());
                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
