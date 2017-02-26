package ruolan.com.cnmarket.presenter;

import javax.inject.Inject;

import ruolan.com.cnmarket.been.AppInfo;
import ruolan.com.cnmarket.been.BaseBean;
import ruolan.com.cnmarket.been.PageBean;
import ruolan.com.cnmarket.common.rx.RxHttpResponseCompat;
import ruolan.com.cnmarket.common.rx.subscribe.ErrorHandlerSubscriber;
import ruolan.com.cnmarket.common.rx.subscribe.ProgressSubscriber;
import ruolan.com.cnmarket.data.AppInfoModel;
import ruolan.com.cnmarket.presenter.contract.AppInfoContract;
import rx.Observable;
import rx.Subscriber;

/**
 * 排行版 Presenter
 */
public class AppInfoPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppInfoView> {

    public static final int TOP_LIST = 0X1;
    public static final int GAME = 0X2;

    @Inject
    public AppInfoPresenter(AppInfoModel appInfoModel, AppInfoContract.AppInfoView rankingView) {
        super(appInfoModel, rankingView);
    }

    public void requestDatas(int type, int page) {

        Subscriber subscriber = null;

        if (page == 0) {

            //第一页显示loading界面
            subscriber = new ProgressSubscriber<PageBean<AppInfo>>(mContext, mView) {
                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    if (appInfoPageBean != null) {
                        mView.showResult(appInfoPageBean);
                    } else {
                        mView.showNoData();
                    }
                }
            };

        } else {
            //下一页
            subscriber = new ErrorHandlerSubscriber<PageBean<AppInfo>>(mContext) {

                @Override
                public void onCompleted() {
                    mView.onLoadMoreComplete();
                }

                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    if (appInfoPageBean != null) {
                        mView.showResult(appInfoPageBean);
                    } else {
                        mView.showNoData();
                    }
                }
            };

        }

        Observable observable = getObserver(type,page);

        observable
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);


    }

    /**
     * 请求数据
     *
     * @param type 类型
     * @param page 页数
     * @return
     */
    private Observable<BaseBean<PageBean<AppInfo>>> getObserver(int type, int page) {
        switch (type) {
            case TOP_LIST:
                return mModel.topList(page);
            case GAME:
                return mModel.games(page);
            default:
                return Observable.empty();
        }

    }


}
