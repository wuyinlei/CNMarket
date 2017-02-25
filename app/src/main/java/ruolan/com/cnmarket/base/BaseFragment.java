package ruolan.com.cnmarket.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ruolan.com.cnmarket.CNMarketApplication;
import ruolan.com.cnmarket.di.component.AppComponent;
import ruolan.com.cnmarket.presenter.BasePresenter;
import ruolan.com.cnmarket.ui.BaseView;


public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView{


    private Unbinder mUnbinder;

    private CNMarketApplication mApplication;

    public View mRootView;

    @Inject
    public T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        mRootView = inflater.inflate(setLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        this.mApplication = (CNMarketApplication) getActivity().getApplication();
        setupAcitivtyComponent(mApplication.getAppComponent());

        init();
        initData();


        return mRootView;
    }

    protected abstract void initData();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }

    protected abstract void setupAcitivtyComponent(AppComponent appComponent);


    public abstract int setLayout();

    //  public abstract  void setupAcitivtyComponent(AppComponent appComponent);


    public abstract void init();


    @Override
    public void showError(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
