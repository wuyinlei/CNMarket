package ruolan.com.cnmarket.base;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.iconics.context.IconicsLayoutInflater;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ruolan.com.cnmarket.CNMarketApplication;
import ruolan.com.cnmarket.di.component.AppComponent;
import ruolan.com.cnmarket.presenter.BasePresenter;


public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private Unbinder mUnbinder;

    private CNMarketApplication mApplication;

    @Inject
    T mPresenter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));

        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        this.mApplication = (CNMarketApplication) getApplication();
        setUpActivityComponent(mApplication.getAppComponent());
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    public abstract void setUpActivityComponent(AppComponent appComponent);

}
