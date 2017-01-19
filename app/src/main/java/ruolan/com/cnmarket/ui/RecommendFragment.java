package ruolan.com.cnmarket.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ruolan.com.cnmarket.Contants;
import ruolan.com.cnmarket.R;
import ruolan.com.cnmarket.base.BaseFragment;

/**
 * Created by wuyinlei on 2017/1/19.
 */

public class RecommendFragment extends BaseFragment {

    private String mTitle;
    private TextView mTvTitle;



    @Override
    public int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void init() {
        mTvTitle = (TextView) mRootView.findViewById(R.id.title);
        mTitle = getArguments().getString(Contants.FRAGMENT_TITLE);
        if (!TextUtils.isEmpty(mTitle))
            mTvTitle.setText(mTitle);
    }



    public static Fragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(Contants.FRAGMENT_TITLE, title);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
