package ruolan.com.cnmarket.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import ruolan.com.cnmarket.been.IndexBean;
import ruolan.com.cnmarket.common.rx.RxHttpResponseCompat;
import ruolan.com.cnmarket.common.rx.subscribe.ProgressSubscriber;
import ruolan.com.cnmarket.data.AppInfoModel;
import ruolan.com.cnmarket.presenter.contract.AppInfoContract;


public class RecommendPresenter extends BasePresenter<AppInfoModel, AppInfoContract.View> {

//    private AppInfoContract.View mView;
//
//    private AppInfoModel mModel;


    @Inject
    public RecommendPresenter(AppInfoModel recommendModel, AppInfoContract.View view) {
        super(recommendModel, view);
    }


    public void requestDatas() {

        Activity activity;

        if (mView instanceof Fragment) {
            activity = ((Fragment) mView).getActivity();
        } else {
            activity = (Activity) mView;
        }


        mModel.getIndex().compose(RxHttpResponseCompat.<IndexBean>compatResult())
                .subscribe(new ProgressSubscriber<IndexBean>(activity,mView) {
                    @Override
                    public void onNext(IndexBean indexBean) {
                        if (indexBean != null) {
                            mView.showResult(indexBean);
                        } else {
                            mView.showNoData();
                        }
                    }
                });

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

//        RxPermissions rxPermissions = new RxPermissions(activity);
//
//        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
//                //flatMap转换器
//                .flatMap(new Func1<Boolean, Observable<PageBean<AppInfo>>>() {
//
//                    @Override
//                    public Observable<PageBean<AppInfo>> call(Boolean aBoolean) {
//                        if (aBoolean) {
//                            return mModel.getApps().compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult());
//                        } else {
//                            return Observable.empty();
//                        }
//                    }
//                }).subscribe(new ProgressSubscriber<PageBean<AppInfo>>(activity, mView) {
//
//            @Override
//            protected boolean isShowDialog() {
//                return super.isShowDialog();
//            }
//
//            @Override
//            public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                if (appInfoPageBean != null) {
//                    mView.showResult(appInfoPageBean.getDatas());
//                } else {
//                    mView.showNoData();
//                }
//            }
//        });



//        mModel.getApps()
////                .subscribeOn(Schedulers.io())  //切换到子线程操作
////                .observeOn(AndroidSchedulers.mainThread()) //请求结果切换到主线程进行操作
//                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
//                .subscribe(new ProgressDialogSubscriber<PageBean<AppInfo>>(activity) {
//                    @Override
//                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                        if (appInfoPageBean != null) {
//                            mView.showResult(appInfoPageBean.getDatas());
//                        } else {
//                            mView.showNoData();
//                        }
//                    }
//
//                    @Override
//                    protected boolean isShowDialog() {
//                        return super.isShowDialog();
//                    }
//                });
    }
}
