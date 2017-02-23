package ruolan.com.cnmarket.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyinlei on 2017/2/23.
 */

public class GuideFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public GuideFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> fragments) {
        if (fragments == null) {
            mFragments = new ArrayList<>();
        } else {
            mFragments = fragments;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }
}
