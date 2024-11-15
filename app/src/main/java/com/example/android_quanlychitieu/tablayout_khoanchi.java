package com.example.android_quanlychitieu;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class tablayout_khoanchi extends AppCompatActivity {
    private TabLayout mtabLayout;
    private ViewPager mviewPage;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout_khoanchi);
        drawerLayout = findViewById(R.id.drawer_layout);
        mtabLayout = findViewById(R.id.tab_layout);
        mviewPage = findViewById(R.id.viewpager);

        ViewPage viewPageAdapter = new ViewPage(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewPage.setAdapter(viewPageAdapter);

        // Kết nối TabLayout với ViewPager
        mtabLayout.setupWithViewPager(mviewPage);

        ImageView menuIcon = findViewById(R.id.menu_icon);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_khoanthu) {
                    // Xử lý cho khoản thu
                } else if (id == R.id.nav_khoanchi) {
                    // Xử lý cho khoản chi
                } else if (id == R.id.nav_thongke) {
                    // Xử lý cho thống kê
                }  else if (id == R.id.nav_thoat) {
                    // Xử lý cho thoát
                    finish();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


    }

}
