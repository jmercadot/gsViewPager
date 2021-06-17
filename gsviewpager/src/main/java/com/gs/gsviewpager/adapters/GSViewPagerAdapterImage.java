package com.gs.gsviewpager.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.gs.gsviewpager.R;

import java.util.ArrayList;
import java.util.Objects;


public class GSViewPagerAdapterImage extends PagerAdapter {

    private final ArrayList<Drawable> listImage;
    private final Context context;

    public GSViewPagerAdapterImage(ArrayList<Drawable> listImage, Context context) {
        this.listImage = listImage;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listImage.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        final ImageView imageView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = Objects.requireNonNull(inflater).inflate(R.layout.slide_image_item_disable_zoom,container,false);
        imageView = itemView.findViewById(R.id.slider_image_view);
        imageView.setImageDrawable(listImage.get(position));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(position,container);
            }
        });
        container.addView(itemView);
        return itemView;
    }

    private void showDialog(int position, ViewGroup container) {
        final ImageView imageView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = Objects.requireNonNull(inflater).inflate(R.layout.slide_image_item,container,false);
        imageView = itemView.findViewById(R.id.slider_image_view);
        imageView.setImageDrawable(listImage.get(position));

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setView(itemView);
        AlertDialog alert = dialog.create();
        alert.show();
        Objects.requireNonNull(alert.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
