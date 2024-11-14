package com.example.android_quanlychitieu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPage extends FragmentStatePagerAdapter {

    public ViewPage(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new PageFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 2; // Số lượng các trang
    }

    @NonNull
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Khoản Chi";
            case 1:
                return "Loại Chi";
            default:
                return "";
        }
    }
}
