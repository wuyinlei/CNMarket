package ruolan.com.cnmarket.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ruolan.com.cnmarket.Contants;
import ruolan.com.cnmarket.R;
import ruolan.com.cnmarket.base.BaseFragment;
import ruolan.com.cnmarket.been.AppInfo;
import ruolan.com.cnmarket.been.PageBean;
import ruolan.com.cnmarket.data.http.ApiService;
import ruolan.com.cnmarket.data.http.HttpManager;
import ruolan.com.cnmarket.ui.adapter.RecommentAppAdapter;

/**
 * Created by wuyinlei on 2017/1/19.
 */

public class RecommendFragment extends BaseFragment {

    private String mTitle;
    private TextView mTvTitle;

    @BindView(R.id.recycler_view)
    public RecyclerView mRecyclerView;

    private List<AppInfo> mAppInfos;
    private RecommentAppAdapter mAppAdapter;


    @Override
    protected void initData() {

//        Log.d("RecommendFragment", "mRecyclerView:" + mRecyclerView);
        HttpManager manager = new HttpManager();
        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient())
                .create(ApiService.class);
        apiService.getApps("{'page':0}").enqueue(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                PageBean<AppInfo> pageBean = response.body();

                List<AppInfo> appInfos = pageBean.getDatas();
                initRecyclerView(appInfos);

            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {

            }
        });
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void init() {
        ButterKnife.bind(this,mRootView);
//        mTvTitle = (TextView) mRootView.findViewById(R.id.title);
//        mTitle = getArguments().getString(Contants.FRAGMENT_TITLE);
//        if (!TextUtils.isEmpty(mTitle))
//            mTvTitle.setText(mTitle);
//        Toast.makeText(getActivity(), mTitle, Toast.LENGTH_SHORT).show();

       // Toast.makeText(getActivity(), "mRecyclerView:" + mRecyclerView, Toast.LENGTH_SHORT).show();
    }

    private void initRecyclerView(List<AppInfo> appInfos){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.addItemDecoration();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAppAdapter = new RecommentAppAdapter(getActivity(), appInfos);
        mRecyclerView.setAdapter(mAppAdapter);

    }


    public static Fragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(Contants.FRAGMENT_TITLE, title);
        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
