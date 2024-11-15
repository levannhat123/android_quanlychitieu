package com.example.android_quanlychitieu;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

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
                    setupTabLayoutKhoanthu(); // Khi chọn "Khoản Thu"
                } else if (id == R.id.nav_khoanchi) {
                    setupTabLayoutKhoanchi(); // Khi chọn "Khoản Chi"
                } else if (id==R.id.nav_thongke) {
                    setupTabLayoutThongke();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // Mặc định hiển thị các tab cho Khoản Chi
        setupTabLayoutKhoanthu();
    }

    private void setupTabLayoutKhoanchi() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new KhoanchiFragment());    // Khoản Chi
        fragments.add(new LoaichiFragment());    // Loại Chi

        List<String> titles = new ArrayList<>();
        titles.add("Khoản Chi");
        titles.add("Loại Chi");

        setupViewPager(fragments, titles);
    }

    private void setupTabLayoutKhoanthu() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new KhoanthuFragment());    // Khoản Thu
        fragments.add(new LoaithuFragment()); // Loại Thu

        List<String> titles = new ArrayList<>();
        titles.add("Khoản Thu");
        titles.add("Loại Thu");

        setupViewPager(fragments, titles);
    }
    private void setupTabLayoutThongke() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ThongkeThuFragment());    // Khoản Chi
        fragments.add(new ThongkeChiFragment());    // Loại Chi

        List<String> titles = new ArrayList<>();
        titles.add("Thông Kê Thu");
        titles.add("Thống Kê Chi");

        setupViewPager(fragments, titles);
    }

    private void setupViewPager(List<Fragment> fragments, List<String> titles) {
        ViewPage viewPageAdapter = new ViewPage(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments, titles);

        mviewPage.setAdapter(viewPageAdapter);
        viewPageAdapter.notifyDataSetChanged();
        mtabLayout.setupWithViewPager(mviewPage);
    }
}
