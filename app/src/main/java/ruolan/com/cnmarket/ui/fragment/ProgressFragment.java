package ruolan.com.cnmarket.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ruolan.com.cnmarket.CNMarketApplication;
import ruolan.com.cnmarket.R;
import ruolan.com.cnmarket.di.component.AppComponent;
import ruolan.com.cnmarket.presenter.BasePresenter;
import ruolan.com.cnmarket.ui.BaseView;


public abstract class ProgressFragment<T extends BasePresenter> extends Fragment implements BaseView {

    //根布局
    public FrameLayout mRootView;


    private CNMarketApplication mApplication;


    private View mViewProgress;
    private FrameLayout mViewContent;
    private View mViewEmpty;
    private Unbinder mUnbinder;

    private TextView mEmptyTextView;

    @Inject
    public T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //跟布局
        mRootView = (FrameLayout) inflater.inflate(R.layout.fragment_progress, container, false);
        //空布局也就是错误布局
        mViewEmpty = mRootView.findViewById(R.id.view_empty);
        //对错误布局实现点击事件  完成加载出错或者数据为空的时候  点击重新请求数据逻辑
        mViewEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClick();
            }
        });
        //加载loading布局
        mViewProgress = mRootView.findViewById(R.id.view_progress);
        //真实的布局
        mViewContent = (FrameLayout) mRootView.findViewById(R.id.view_content);
        //加载出错或者数据为空显示的错误布局的一个提示
        mEmptyTextView = (TextView) mRootView.findViewById(R.id.text_tip);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mApplication = (CNMarketApplication) getActivity().getApplication();
        setupAcitivtyComponent(mApplication.getAppComponent());
        //加载真实的布局
        setRealContentView();
        //用于初始化布局控件  这里也可以不写  因为我们使用了ButterKnife实现对控件的注解
        init();
        //用于子类实现  请求数据方法
        initData();
    }

    protected abstract void initData();

    /**
     * 初始化控件
     */
    protected abstract void init();

    /**
     * 设置真正的布局
     */
    private void setRealContentView() {
        View realContentView = LayoutInflater.from(getActivity()).inflate(setLayout(), mViewContent, true);
        mUnbinder = ButterKnife.bind(this, realContentView);
    }

    /**
     * 判断要显示的子view  正常布局  错误布局  loading布局  根据传入的viewId
     *
     * @param viewId 需要显示的viewid
     */
    public void showView(int viewId) {
        int childCount = mRootView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (mRootView.getChildAt(i).getId() == viewId) {
                mRootView.getChildAt(i).setVisibility(View.VISIBLE);
            } else {
                mRootView.getChildAt(i).setVisibility(View.GONE);  //隐藏
            }
        }
    }

//    /**
//     * 显示加载loading布局
//     */
//    public void showProgressView() {
//        mViewProgress.setVisibility(View.VISIBLE);
//        mViewEmpty.setVisibility(View.GONE);
//        mViewContent.setVisibility(View.GONE);
//    }


    /**
     * 一个空实现  用于子类进行实现  当现实错误布局或者空数据的时候再次请求数据用
     */
    public void onEmptyViewClick() {

    }

    /**
     * 显示进度条view
     */
    public void showProgressView() {
        showView(R.id.view_progress);
    }

    /**
     * 显示真实布局view
     */
    public void showContentView() {
        showView(R.id.view_content);
    }

    /**
     * 显示数据为空view
     */
    public void showEmptyView() {
        showView(R.id.view_empty);
    }

    /**
     * 显示数据为空view
     */
    public void showEmptyView(int resId) {

        showView(R.id.view_empty);
        mEmptyTextView.setVisibility(View.VISIBLE);
        mEmptyTextView.setText(resId);
    }

    /**
     * 显示数据为空view
     */
    public void showEmptyView(String msgId) {
        showView(R.id.view_empty);
        mEmptyTextView.setVisibility(View.VISIBLE);
        mEmptyTextView.setText(msgId);
    }

//    /**
//     * 显示数据为空view
//     */
//    public void showEmptyView(int msgId){
//        mEmptyTextView.setText(msgId);
//        showView(R.id.view_empty);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    public abstract int setLayout();

    @Override
    public void showLoading() {
        showProgressView();
    }

    @Override
    public void showError(String msg) {
        showEmptyView(msg);
    }

    @Override
    public void dismissLoading() {
        showContentView();
    }

    protected abstract void setupAcitivtyComponent(AppComponent appComponent);


}
