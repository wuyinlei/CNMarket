package ruolan.com.cnmarket.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ruolan.com.cnmarket.CNMarketApplicaption;

/**
 * Created by wuyinlei on 2017/1/9.
 */

public abstract class BaseFragment extends Fragment {


    private Unbinder mUnbinder;

    private CNMarketApplicaption mApplication;

    public View mRootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        mRootView = inflater.inflate(setLayout(), container, false);
        mUnbinder=  ButterKnife.bind(this, mRootView);
        init();
        initData();


        return mRootView;
    }

    protected abstract void initData();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        this.mApplication = (CNMarketApplicaption) getActivity().getApplication();
       // setupAcitivtyComponent(mApplication.getAppComponent());



    }
    


    public abstract int setLayout();

  //  public abstract  void setupAcitivtyComponent(AppComponent appComponent);


    public abstract void  init();
}
