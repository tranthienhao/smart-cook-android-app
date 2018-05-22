package tth14110049.vn.edu.hcmute.smartcook.Controller.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.SwipeableTabAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Fragment.CategoryTab;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Fragment.FoodTab;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Fragment.MenuTab;
import tth14110049.vn.edu.hcmute.smartcook.R;

public class MainActivity extends FragmentActivity {
    private TabHost mainTabHost;
    private ViewPager pager;
    public static RelativeLayout internetErrorLayout;
    private class TabFactory implements TabHost.TabContentFactory {
        private final Context mContext;

        private TabFactory(Context context) {
            mContext = context;
        }

        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the view
        mainTabHost = findViewById(android.R.id.tabhost);
        pager = findViewById(R.id.viewpager);
        Button btnReload = findViewById(R.id.btn_reload);
        internetErrorLayout = findViewById(R.id.layout_internet_error);

        //turn off auto reload fragment on tab change
        pager.setOffscreenPageLimit(3);

        //btnReload
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Refresh main activity upon close of dialog box
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });
        //

        mainTabHost.setup();

        //set Viewpager
        List<Fragment> fragments = new Vector<>();
        fragments.add(Fragment.instantiate(this, FoodTab.class.getName()));
        fragments.add(Fragment.instantiate(this, MenuTab.class.getName()));
        fragments.add(Fragment.instantiate(this, CategoryTab.class.getName()));

        SwipeableTabAdapter pagerAdapter = new SwipeableTabAdapter(super.getSupportFragmentManager(), fragments);
        pager.setAdapter(pagerAdapter);


        // Add new TAB
        MainActivity.AddTab(this, this.mainTabHost, this.mainTabHost.newTabSpec("Food Tab")
                .setIndicator(createTabView(mainTabHost.getContext(), getString(R.string.food), R.drawable.ic_maintab_food)));
        MainActivity.AddTab(this, this.mainTabHost, this.mainTabHost.newTabSpec("Menu Tab")
                .setIndicator(createTabView(mainTabHost.getContext(), getString(R.string.menu), R.drawable.ic_maintab_menu)));
        MainActivity.AddTab(this, this.mainTabHost, this.mainTabHost.newTabSpec("Category Tab")
                .setIndicator(createTabView(mainTabHost.getContext(), getString(R.string.category), R.drawable.ic_maintab_category)));

        //Remove strip
        mainTabHost.getTabWidget().getChildTabViewAt(0).setBackground(null);
        mainTabHost.getTabWidget().getChildTabViewAt(1).setBackground(null);
        mainTabHost.getTabWidget().getChildTabViewAt(2).setBackground(null);

        //OnTabChangedListener
        mainTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int position;
                position = mainTabHost.getCurrentTab();
                pager.setCurrentItem(position);
            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mainTabHost.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //setCurrentTab
        mainTabHost.setCurrentTab(0);

    }

    private static void AddTab(MainActivity activity,
                               TabHost tabHost, TabHost.TabSpec tabSpec) {
        // Attach a Tab view factory to the spec
        tabSpec.setContent(activity.new TabFactory(activity));
        tabHost.addTab(tabSpec);
    }
    private static View createTabView(final Context context, final String text, final int image) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tabs, null);
        TextView tvTabText = view.findViewById(R.id.tabs_text);
        ImageView ivTabImage = view.findViewById(R.id.tabs_image);
        tvTabText.setText(text);
        ivTabImage.setImageResource(image);
        return view;
    }
}

