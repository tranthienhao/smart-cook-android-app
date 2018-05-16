package tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class SwipeableTabAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public SwipeableTabAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
     */
    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    /* (non-Javadoc)
     * @see android.support.v4.view.CustomPagerAdapter#getCount()
     */
    @Override
    public int getCount() {
        return this.fragments.size();
    }

}