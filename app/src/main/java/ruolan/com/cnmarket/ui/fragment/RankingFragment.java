package ruolan.com.cnmarket.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import ruolan.com.cnmarket.common.Constants;
import ruolan.com.cnmarket.di.component.AppComponent;
import ruolan.com.cnmarket.di.component.DaggerAppInfoComponent;
import ruolan.com.cnmarket.di.module.AppInfoModule;
import ruolan.com.cnmarket.presenter.AppInfoPresenter;
import ruolan.com.cnmarket.ui.adapter.AppInfoAdapter;


public class RankingFragment extends BaseAppInfoFragment {

    private String mTitle;
    private TextView mTvTitle;



    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this)).build().injectRankingFragment(this);
//        DaggerRankingComponent.builder().appComponent(appComponent)
//                .rankingModule(new AppInfoModule(this)).build().inject(this);
    }

    @Override
    public int type() {
        return AppInfoPresenter.TOP_LIST;
    }

    public static Fragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.FRAGMENT_TITLE, title);
        RankingFragment fragment = new RankingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public AppInfoAdapter builderAdapter() {
        return AppInfoAdapter.builder().showPosition(true)
                .showBrief(false).showCategoryName(true).build();
    }



}
