package ruolan.com.cnmarket.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ruolan.com.cnmarket.been.AppInfo;
import ruolan.com.cnmarket.been.BaseBean;
import ruolan.com.cnmarket.been.PageBean;
import ruolan.com.cnmarket.common.rx.RxErrorHandler;
import ruolan.com.cnmarket.common.rx.RxHttpResponseCompat;
import ruolan.com.cnmarket.common.rx.subscribe.ErrorHandlerSubscriber;
import ruolan.com.cnmarket.common.rx.subscribe.ProgressDialogSubscriber;
import ruolan.com.cnmarket.data.RecommendModel;
import ruolan.com.cnmarket.presenter.contract.RecommendContract;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.SafeSubscriber;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;


public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {

//    private RecommendContract.View mView;
//
//    private RecommendModel mModel;


    @Inject
    public RecommendPresenter(RecommendModel recommendModel, RecommendContract.View view) {
        super(recommendModel, view);
    }


    public void requestDatas() {

        Activity activity = null;

        if (mView instanceof Fragment) {
            activity = ((Fragment) mView).getActivity();
        } else {
            activity = (Activity) mView;
        }

//        mModel.getApps(new Callback<PageBean<AppInfo>>() {
//            @Override
//            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
//                if (response != null) {
//                    mView.showResult(response.body().getDatas());
//                } else {
//                    mView.showNoData();
//                }
//                mView.dismissLoading();
//            }
//
//            @Override
//            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
//                mView.dismissLoading();
//                mView.showError(t.getMessage());
//            }
//        });
        mModel.getApps()
//                .subscribeOn(Schedulers.io())  //切换到子线程操作
//                .observeOn(AndroidSchedulers.mainThread()) //请求结果切换到主线程进行操作
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressDialogSubscriber<PageBean<AppInfo>>(activity) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        if (appInfoPageBean != null) {
                            mView.showResult(appInfoPageBean.getDatas());
                        } else {
                            mView.showNoData();
                        }
                    }

                    @Override
                    protected boolean isShowDialog() {
                        return super.isShowDialog();
                    }
                });
    }
}
