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


public abstract class ProgressFragment<T extends BasePresenter> extends Fragment implements BaseView{

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
        mRootView = (FrameLayout) inflater.inflate(R.layout.fragment_progress, container, false);
        mViewEmpty = mRootView.findViewById(R.id.view_empty);
        mViewEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClick();
            }
        });
        mViewProgress = mRootView.findViewById(R.id.view_progress);
        mViewContent = (FrameLayout) mRootView.findViewById(R.id.view_content);

        mEmptyTextView = (TextView) mRootView.findViewById(R.id.text_tip);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mApplication = (CNMarketApplication) getActivity().getApplication();
        setupAcitivtyComponent(mApplication.getAppComponent());

        setRealContentView();
        
        init();
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

    public void showView(int viewId) {
        for (int i = 0; i < mRootView.getChildCount(); i++) {
            if(mRootView.getChildAt(i).getId() == viewId){
                mRootView.getChildAt(i).setVisibility(View.VISIBLE);
            } else {
                mRootView.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }
    
   public void onEmptyViewClick(){
       
   }

    /**
     * 显示进度条view
     */
    public void showProgressView(){
        showView(R.id.view_progress);
    }

    /**
     * 显示真实布局view
     */
    public void showContentView(){
        showView(R.id.view_content);
    }

    /**
     * 显示数据为空view
     */
    public void showEmptyView(){
        showView(R.id.view_empty);
    }

    /**
     * 显示数据为空view
     */
    public void showEmptyView(int resId){
        mEmptyTextView.setText(resId);
        showView(R.id.view_empty);
    }

    /**
     * 显示数据为空view
     */
    public void showEmptyView(String msgId){
        mEmptyTextView.setText(msgId);
        showView(R.id.view_empty);
    }
    
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
