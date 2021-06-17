package com.gs.gsviewpager.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class GSViewPagerAdapterFragment extends FragmentPagerAdapter {

    public  static final int BEHAVIOR = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

    private final ArrayList<Fragment> listFragment;
    private final ArrayList<String> listTitle;

    public GSViewPagerAdapterFragment(@NonNull FragmentManager fm, ArrayList<Fragment> listFragment, ArrayList<String> listTitle) {
        super(fm);
        this.listFragment = listFragment;
        this.listTitle = listTitle;
    }

    public GSViewPagerAdapterFragment(@NonNull FragmentManager fm, ArrayList<Fragment> listFragment) {
        super(fm);
        this.listFragment = listFragment;
        this.listTitle = new ArrayList<>();
    }

    public GSViewPagerAdapterFragment(@NonNull FragmentManager fm, ArrayList<Fragment> listFragment, ArrayList<String> listTitle, int behavior) {
        super(fm, behavior);
        this.listFragment = listFragment;
        this.listTitle = listTitle;

    }

    public GSViewPagerAdapterFragment(@NonNull FragmentManager fm, ArrayList<Fragment> listFragment, int behavior) {
        super(fm, behavior);
        this.listFragment = listFragment;
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
