package com.gs.gsviewpager.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gs.gsviewpager.R;

import java.util.ArrayList;
import java.util.Objects;


public class GSViewPagerAdapterFromURL extends PagerAdapter {

    private final ArrayList<String> listImage;
    private final Context context;
    private boolean success;

    public GSViewPagerAdapterFromURL(ArrayList<String> listImage, Context context) {
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
        final ImageView loadImageView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = Objects.requireNonNull(inflater).inflate(R.layout.slide_image_item_disable_zoom,container,false);
        imageView = itemView.findViewById(R.id.slider_image_view);
        loadImageView = itemView.findViewById(R.id.load_image_view);
        Glide.with(context).asGif().fitCenter().load(R.raw.load).into(loadImageView);

        Glide.with(context)
                .load(listImage.get(position))
                .centerInside()
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        success = false;
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        success = true;
                        return false;
                    }
                })
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (success){
                    imageView.buildDrawingCache();
                    Bitmap bmap = imageView.getDrawingCache();
                    showDialog(bmap,container);
                }
            }
        });
        container.addView(itemView);
        return itemView;
    }

    private void showDialog(Bitmap bitmap, ViewGroup container) {
        final ImageView imageView;
        final ImageView loadImageView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = Objects.requireNonNull(inflater).inflate(R.layout.slide_image_item,container,false);

        imageView = itemView.findViewById(R.id.slider_image_view);
        loadImageView = itemView.findViewById(R.id.load_image_view);
        loadImageView.setVisibility(View.GONE);
        imageView.setImageBitmap(bitmap);

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setView(itemView);
        AlertDialog alert = dialog.create();
        alert.show();
        alert.setCancelable(true);
        Objects.requireNonNull(alert.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
