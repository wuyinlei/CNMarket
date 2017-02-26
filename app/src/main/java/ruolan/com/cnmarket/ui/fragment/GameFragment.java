package ruolan.com.cnmarket.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import ruolan.com.cnmarket.R;
import ruolan.com.cnmarket.base.BaseFragment;
import ruolan.com.cnmarket.common.Constants;
import ruolan.com.cnmarket.di.component.AppComponent;
import ruolan.com.cnmarket.di.component.DaggerAppInfoComponent;
import ruolan.com.cnmarket.di.module.AppInfoModule;
import ruolan.com.cnmarket.presenter.AppInfoPresenter;
import ruolan.com.cnmarket.ui.adapter.AppInfoAdapter;


public class GameFragment extends BaseAppInfoFragment {


    @Override
    public int type() {
        return AppInfoPresenter.GAME;
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this)).build().injectGameFragment(this);
    }



    public static Fragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.FRAGMENT_TITLE, title);
        GameFragment fragment = new GameFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public AppInfoAdapter builderAdapter() {
        return AppInfoAdapter.builder().showPosition(false)
                .showBrief(true).showCategoryName(false).build();
    }

}
