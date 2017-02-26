package ruolan.com.cnmarket.presenter;

import javax.inject.Inject;

import ruolan.com.cnmarket.been.AppInfo;
import ruolan.com.cnmarket.been.PageBean;
import ruolan.com.cnmarket.common.rx.RxHttpResponseCompat;
import ruolan.com.cnmarket.common.rx.subscribe.ErrorHandlerSubscriber;
import ruolan.com.cnmarket.common.rx.subscribe.ProgressSubscriber;
import ruolan.com.cnmarket.data.AppInfoModel;
import ruolan.com.cnmarket.presenter.contract.AppInfoContract;
import rx.Subscriber;

/**
 * 排行版 Presenter
 */
public class RankingPresenter extends BasePresenter<AppInfoModel,AppInfoContract.RankingView> {


    @Inject
    public RankingPresenter(AppInfoModel appInfoModel, AppInfoContract.RankingView rankingView) {
        super(appInfoModel, rankingView);
    }

    public void requestDatas(int page){

        Subscriber subscriber = null;

        if (page == 0) {

            //第一页显示loading界面
            mModel.topList(page)
                    .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                    .subscribe(new ProgressSubscriber<PageBean<AppInfo>>(mContext, mView) {
                        @Override
                        public void onNext(PageBean<AppInfo> appInfoPageBean) {
                            if (appInfoPageBean != null) {
                                mView.showResult(appInfoPageBean);
                            } else {
                                mView.showNoData();
                            }
                        }
                    });
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

            mModel.topList(page)
                    .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                    .subscribe(subscriber);
        }


    }


}
