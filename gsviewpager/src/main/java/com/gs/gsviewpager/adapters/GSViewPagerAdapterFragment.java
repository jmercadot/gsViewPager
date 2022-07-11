package com.gs.gsviewpager.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class GSViewPagerAdapterFragment extends FragmentPagerAdapter {

    public  static final int BEHAVIOR = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

    private ArrayList<Fragment> listFragment;
    private ArrayList<String> listTitle;



    public void setFragments(ArrayList<Fragment> listFragment,ArrayList<String> listTitle){
        this.listFragment = listFragment;
        this.listTitle = listTitle;
        notifyDataSetChanged();
    }

    public void setFragments(ArrayList<Fragment> listFragment){
        this.listFragment = listFragment;
        this.listTitle = new ArrayList<>();
        notifyDataSetChanged();
    }

    public GSViewPagerAdapterFragment(FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.listFragment = new ArrayList<>();
        this.listTitle = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (listFragment.size() == listTitle.size()) {
            return listTitle.get(position);
        } else {
            return "";
        }
    }
}
