package com.jtanveer.raydar.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jtanveer.raydar.R;
import com.jtanveer.raydar.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance())
                    .commitNow();
        }
    }
}
