package tth14110049.vn.edu.hcmute.smartcook.Controller.Activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.SwipeableTabAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Fragment.CategoryTab;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Fragment.FoodTab;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Fragment.MenuTab;
import tth14110049.vn.edu.hcmute.smartcook.R;

public class MainActivity extends FragmentActivity {
    private TabHost mainTabHost;
    private ViewPager pager;

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

