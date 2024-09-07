package com.continuidadoperativa.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.widget.Toast;

import com.continuidadoperativa.myapplication.databinding.ActivityMainBinding;
import com.gs.gsviewpager.GSViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        binding.ExampleViewPager.loadFragmentList(fragments,getSupportFragmentManager(), false);
        binding.ExampleViewPager.disablePagingEffect();
        binding.ExampleViewPager.setOnMoveListener(() -> {
            Toast.makeText(this, "Se movio!", Toast.LENGTH_SHORT).show();
        });
    }
}