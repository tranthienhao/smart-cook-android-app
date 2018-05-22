package tth14110049.vn.edu.hcmute.smartcook.Controller.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.CategoryAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Retrofit2.ApiClient;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Retrofit2.ApiInterface;
import tth14110049.vn.edu.hcmute.smartcook.Model.Category;
import tth14110049.vn.edu.hcmute.smartcook.R;

import static tth14110049.vn.edu.hcmute.smartcook.Controller.Activity.MainActivity.internetErrorLayout;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */

public class CategoryTab extends Fragment {
    private RecyclerView recyclerCategory;
    private List<Category> listCategory = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    ApiInterface apiService;
    private View view;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_category_tab, container, false);

        //set the view
        recyclerCategory = view.findViewById(R.id.list_category);
        swipeRefreshLayout =  view.findViewById(R.id.swipeRefreshLayout);

        //SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                prepareData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // recyclerCategory init
        recyclerCategory.setLayoutManager(new GridLayoutManager(getContext(), 2));
        categoryAdapter = new CategoryAdapter(getContext(),listCategory);
        categoryAdapter.notifyDataSetChanged();
        recyclerCategory.setAdapter(categoryAdapter);
        recyclerCategory.setNestedScrollingEnabled(false);

        //get ApiInterface
        apiService = ApiClient.getClient().create(ApiInterface.class);

        prepareData();

        return view;
    }

    private void prepareData() {
        Call<List<Category>> call = apiService.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>>call, Response<List<Category>> response) {
//                listCategory.clear();
                listCategory = response.body();
                //Toast.makeText(getContext(),""+listCategory.size(),Toast.LENGTH_LONG).show();
                categoryAdapter = new CategoryAdapter(getContext(),listCategory);
                recyclerCategory.setAdapter(categoryAdapter);
                //   loadingDialog.dismiss();
            }
            @Override
            public void onFailure(Call<List<Category>>call, Throwable t) {
                // Log error here since request failed
                Log.e("GET CATEGORY ERROR", t.toString());
                internetErrorLayout.setVisibility(View.VISIBLE);
            }
        });
    }
}
