package ruolan.com.cnmarket.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruolan.com.cnmarket.R;
import ruolan.com.cnmarket.common.Constants;
import ruolan.com.cnmarket.common.utils.ACache;
import ruolan.com.cnmarket.ui.adapter.GuideFragmentAdapter;
import ruolan.com.cnmarket.ui.fragment.GuideFragment;
import ruolan.com.cnmarket.ui.widget.CircleIndicator;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{


    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.btn_enter)
    Button mBtnEnter;
    @BindView(R.id.indicator)
    CircleIndicator mIndicator;
    @BindView(R.id.activity_guide)
    RelativeLayout mActivityGuide;
    private GuideFragmentAdapter mGuideFragmentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        mViewPager.setCurrentItem(0);

        List<Fragment> fragments = new ArrayList<>();


        fragments.add(GuideFragment.newInstance(R.mipmap.guide_1,R.color.color_bg_guide1,R.string.guide_1));
        fragments.add(GuideFragment.newInstance(R.mipmap.guide_2,R.color.color_bg_guide2,R.string.guide_2));
        fragments.add(GuideFragment.newInstance(R.mipmap.guide_3,R.color.color_bg_guide3,R.string.guide_3));

        mGuideFragmentAdapter = new GuideFragmentAdapter(getSupportFragmentManager());

        mGuideFragmentAdapter.setFragments(fragments);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(mGuideFragmentAdapter.getCount());
        mViewPager.setAdapter(mGuideFragmentAdapter);
        mIndicator.setViewPager(mViewPager);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mBtnEnter.setVisibility((position == mGuideFragmentAdapter.getCount() - 1) ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @OnClick(R.id.btn_enter)
    public void onClick() {

        ACache.get(this).put(Constants.IS_SHOW_GUIDE,"0");
        startActivity(new Intent(this,MainActivity.class));
        this.finish();

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
