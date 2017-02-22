package ruolan.com.cnmarket.presenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ruolan.com.cnmarket.been.AppInfo;
import ruolan.com.cnmarket.been.PageBean;
import ruolan.com.cnmarket.data.RecommentModel;
import ruolan.com.cnmarket.presenter.contract.RecommentContract;

/**
 * Created by wuyinlei on 2017/2/22.
 */

public class RecommentPresenter implements RecommentContract.Presenter {

    private RecommentContract.View mView;

    private RecommentModel mModel;

    public RecommentPresenter(RecommentContract.View view) {
        this.mView = view;

        mModel = new RecommentModel();
    }

    @Override
    public void requestDatas() {
        mView.showLoading();
        mModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                if (response != null) {
                    mView.showResult(response.body().getDatas());
                } else {
                    mView.showNoData();
                }
                mView.dismissLoading();
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                mView.dismissLoading();
                mView.showError(t.getMessage());
            }
        });
    }
}
