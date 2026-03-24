package com.sangptit.nguyenluutansang_n22dccn068.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.sangptit.nguyenluutansang_n22dccn068.fragments.BookGridFragment;
import com.sangptit.nguyenluutansang_n22dccn068.fragments.CartFragment;
import com.sangptit.nguyenluutansang_n22dccn068.fragments.ProfileFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BookGridFragment();
            case 1:
                return new CartFragment();
            case 2:
                return new ProfileFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
