package ruolan.com.cnmarket.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import ruolan.com.cnmarket.R;
import ruolan.com.cnmarket.base.BaseFragment;
import ruolan.com.cnmarket.common.Constants;

/**
 * Created by wuyinlei on 2017/1/19.
 */

public class CategoryFragment extends BaseFragment {

    private String mTitle;
    private TextView mTvTitle;


    @Override
    protected void initData() {

    }

    @Override
    public int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
//        mTvTitle = (TextView) mRootView.findViewById(R.id.title);
//        mTitle = getArguments().getString(Contants.FRAGMENT_TITLE);
//        if (!TextUtils.isEmpty(mTitle))
//            mTvTitle.setText(mTitle);
    }


    public static Fragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.FRAGMENT_TITLE, title);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
