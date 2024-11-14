package com.example.android_quanlychitieu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class tablayout_khoanchi extends AppCompatActivity {
    private TabLayout mtabLayout;
    private ViewPager mviewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout_khoanchi);

        mtabLayout = findViewById(R.id.tab_layout);
        mviewPage = findViewById(R.id.viewpager);

        ViewPage viewPageAdapter = new ViewPage(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewPage.setAdapter(viewPageAdapter);

        // Kết nối TabLayout với ViewPager
        mtabLayout.setupWithViewPager(mviewPage);
    }
}
