package tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tth14110049.vn.edu.hcmute.smartcook.Controller.CardInterface;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Fragment.CardFragment;
import tth14110049.vn.edu.hcmute.smartcook.Model.Food;

public class FoodSuggessionFragmentPagerAdapter extends FragmentStatePagerAdapter implements CardInterface {

    private List<CardFragment> mFragments;
    private float mBaseElevation;

    public FoodSuggessionFragmentPagerAdapter(FragmentManager fm, float baseElevation, List<Food> listFood) {
        super(fm);
        mFragments = new ArrayList<>();
        mBaseElevation = baseElevation;

        for(int i = 0; i< 4; i++){
            CardFragment cardFragment = new CardFragment();
            Bundle args = new Bundle();
            args.putSerializable("Food", listFood.get(i));
            cardFragment.setArguments(args);
            addCardFragment(cardFragment);
        }
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mFragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        mFragments.set(position, (CardFragment) fragment);
        return fragment;
    }

    public void addCardFragment(CardFragment fragment) {
        mFragments.add(fragment);
    }

}