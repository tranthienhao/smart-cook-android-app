package tth14110049.vn.edu.hcmute.smartcook.Controller.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.MenuAdapter;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_menu_tab, container, false);

        //set the view
        recyclerMenu = view.findViewById(R.id.list_menu);

        prepareData();

        return view;
    }
    private void prepareData() {
        Menu menu = new Menu();
        listMenu.add(menu);
        listMenu.add(menu);
        listMenu.add(menu);
        recyclerMenu.setLayoutManager(new GridLayoutManager(getContext(), 1));
        menuAdapter = new MenuAdapter(getContext(),listMenu);
        recyclerMenu.setNestedScrollingEnabled(false);
        recyclerMenu.setAdapter(menuAdapter);
    }
}
