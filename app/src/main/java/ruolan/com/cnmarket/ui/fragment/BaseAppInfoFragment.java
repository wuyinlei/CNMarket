package ruolan.com.cnmarket.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.BindView;
import ruolan.com.cnmarket.R;
import ruolan.com.cnmarket.been.AppInfo;
import ruolan.com.cnmarket.been.PageBean;
import ruolan.com.cnmarket.common.Constants;
import ruolan.com.cnmarket.di.component.AppComponent;
import ruolan.com.cnmarket.presenter.AppInfoPresenter;
import ruolan.com.cnmarket.presenter.contract.AppInfoContract;
import ruolan.com.cnmarket.ui.adapter.AppInfoAdapter;
import ruolan.com.cnmarket.ui.widget.DividerItemDecoration;


public abstract class BaseAppInfoFragment extends ProgressFragment<AppInfoPresenter>
        implements AppInfoContract.AppInfoView{
    private String mTitle;
    private TextView mTvTitle;


    @BindView(R.id.recycler_view)
    public RecyclerView mRecyclerView;

    AppInfoAdapter mInfoAdapter;

    private int page = 0;

    @Override
    protected void initData() {
        mPresenter.requestDatas(type(),page);
    }

    public abstract int type();

//    @Override
//    protected void setupAcitivtyComponent(AppComponent appComponent) {
////        DaggerRankingComponent.builder().appComponent(appComponent)
////                .rankingModule(new AppInfoModule(this)).build().inject(this);
//    }

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void init() {
//        mTvTitle = (TextView) mRootView.findViewById(R.id.title);
//        mTitle = getArguments().getString(Contants.FRAGMENT_TITLE);
//        if (!TextUtils.isEmpty(mTitle))
//            mTvTitle.setText(mTitle);

//        mInfoAdapter = new AppInfoAdapter();
//        mRecyclerView.setAdapter(mInfoAdapter);
        initRecyclerView();
    }


    public static Fragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.FRAGMENT_TITLE, title);
        RankingFragment fragment = new RankingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.addItemDecoration();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mInfoAdapter = builderAdapter();
        mInfoAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.requestDatas(type(),page);
            }
        });
        mRecyclerView.setAdapter(mInfoAdapter);

    }


    public abstract AppInfoAdapter builderAdapter();



    @Override
    public void showNoData() {
        Toast.makeText(getActivity(), "没有数据进行展示", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), "服务器开小差了" + msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showResult(PageBean<AppInfo> appInfoPageBean) {
        mInfoAdapter.addData(appInfoPageBean.getDatas());

        if (appInfoPageBean.isHasMore()) {
            page++;
        }

        mInfoAdapter.setEnableLoadMore(appInfoPageBean.isHasMore());

    }

    @Override
    public void onLoadMoreComplete() {
        mInfoAdapter.loadMoreComplete();
    }


}
