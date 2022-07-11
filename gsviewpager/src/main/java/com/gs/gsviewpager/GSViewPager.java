package com.gs.gsviewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.gs.gsviewpager.adapters.GSViewPagerAdapterBitMapImage;
import com.gs.gsviewpager.adapters.GSViewPagerAdapterFragment;
import com.gs.gsviewpager.adapters.GSViewPagerAdapterFromURL;
import com.gs.gsviewpager.adapters.GSViewPagerAdapterImage;

import java.util.ArrayList;

public class GSViewPager extends ViewPager {
    private static final String TAG = GSViewPager.class.getSimpleName();

    private GSViewPagerAdapterFragment adapterFragment;
    private GSViewPagerAdapterFromURL adapterImageURL;
    private GSViewPagerAdapterBitMapImage adapterImageBitMap;
    private GSViewPagerAdapterImage adapterImage;

    private ArrayList<Drawable> listDrawableImage;
    private ArrayList<Bitmap> listBitMapImage;
    private ArrayList<String> listUlrImage;
    private ArrayList<Fragment> listFragment;
    private ArrayList<View> listViews;
    private ArrayList<String> fragmentTitleList;

    private FragmentManager fm;
    private Boolean parallax;

    private int oldPosition = 0;
    private int offSet = 0;


    private boolean enabled;

    public GSViewPager(@NonNull Context context) {
        super(context);
        this.enabled = true;
    }

    public GSViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }


    public void loadListDrawable(ArrayList<Drawable> listDrawableImage, Boolean parallax) {
        this.listDrawableImage = listDrawableImage;
        this.parallax = parallax;
        loadDrawables();
    }

    public void loadListBitMap(ArrayList<Bitmap> listBitMapImage, Boolean parallax) {
        this.listBitMapImage = listBitMapImage;
        this.parallax = parallax;
        loadBitmaps();
    }


    public void loadListURL(ArrayList<String> listUlrImage, Boolean parallax) {
        this.listUlrImage = listUlrImage;
        this.parallax = parallax;
        loadURLs();
    }

    public void loadFragmentList(ArrayList<Fragment> listFragment, FragmentManager fm, Boolean parallax) {
        this.listFragment = listFragment;
        this.fm = fm;
        this.parallax = parallax;
        loadFragments();
    }


    public void loadFragmentList(ArrayList<Fragment> listFragment, ArrayList<String> fragmentTitleList, FragmentManager fm, Boolean parallax) {
        this.listFragment = listFragment;
        this.fragmentTitleList = fragmentTitleList;
        this.fm = fm;
        this.parallax = parallax;
        loadFragments();
    }


    public void removeCurrentItem() {
        int position = getCurrentItem();

        if (listDrawableImage != null && listDrawableImage.size() > 0) {
            listDrawableImage.remove(position);
            adapterImage.notifyDataSetChanged();
        }

        if (listBitMapImage != null && listBitMapImage.size() > 0) {
            listBitMapImage.remove(position);
            adapterImageBitMap.notifyDataSetChanged();
        }

        if (listUlrImage != null && listUlrImage.size() > 0) {
            listUlrImage.remove(position);
            adapterImageURL.notifyDataSetChanged();
        }

        if (listFragment != null && listFragment.size() > 0) {
            listFragment.remove(position);
            adapterFragment.notifyDataSetChanged();
        }
    }

    public void addNewItem(Object object) {
        try {
            Drawable drawable = (Drawable) object;
            listDrawableImage.add(drawable);
            adapterImage.notifyDataSetChanged();
        } catch (ClassCastException e) {
            Log.i(TAG, "No es Drawable");
        }

        try {
            Bitmap bitmap = (Bitmap) object;
            listBitMapImage.add(bitmap);
            adapterImageBitMap.notifyDataSetChanged();
        } catch (ClassCastException e) {
            Log.i(TAG, "No es Bitmap");
        }

        try {
            String url = (String) object;
            listUlrImage.add(url);
            adapterImageURL.notifyDataSetChanged();
        } catch (ClassCastException e) {
            Log.i(TAG, "No es URL");
        }

        try {
            Fragment fragment = (Fragment) object;
            listFragment.add(fragment);
            adapterFragment.notifyDataSetChanged();
        } catch (ClassCastException e) {
            Log.i(TAG, "No es Bitmap");
        }
    }


    private void loadFragments() {
        if (this.fragmentTitleList == null) {
            adapterFragment = new GSViewPagerAdapterFragment(fm,GSViewPagerAdapterFragment.BEHAVIOR);
            setAdapter(adapterFragment);
            adapterFragment.setFragments(listFragment);
        } else {
            adapterFragment = new GSViewPagerAdapterFragment(fm,GSViewPagerAdapterFragment.BEHAVIOR);
            setAdapter(adapterFragment);
            adapterFragment.setFragments(listFragment,fragmentTitleList);
        }

        if (parallax) {
            setPageTransformer(true, new Transformer());
        }
    }


    private void loadDrawables() {
        adapterImage = new GSViewPagerAdapterImage(listDrawableImage, getContext());
        setAdapter(adapterImage);
        setOffscreenPageLimit(3);
        if (parallax) {
            setPageTransformer(false, new Transformer());
        }
    }

    private void loadBitmaps() {
        adapterImageBitMap = new GSViewPagerAdapterBitMapImage(listBitMapImage, getContext());
        setAdapter(adapterImageBitMap);
        setOffscreenPageLimit(3);
        if (parallax) {
            setPageTransformer(false, new Transformer());
        }
    }

    private void loadURLs() {
        adapterImageURL = new GSViewPagerAdapterFromURL(listUlrImage, getContext());
        setAdapter(adapterImageURL);
        setOffscreenPageLimit(3);
        if (parallax) {
            setPageTransformer(false, new Transformer());
        }
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
