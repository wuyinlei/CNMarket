package ruolan.com.cnmarket.presenter.contract;

import java.util.List;

import ruolan.com.cnmarket.been.AppInfo;
import ruolan.com.cnmarket.presenter.BasePresenter;
import ruolan.com.cnmarket.ui.BaseView;

/**
 * Created by wuyinlei on 2017/2/22.
 */

public interface RecommentContract {

    interface  View extends BaseView{
        void showResult(List<AppInfo> appInfos);

        void showLoading();

        void dismissLoading();

        void showNoData();

        void showError(String msg);
    }

    interface Presenter extends BasePresenter{



        public void requestDatas();

    }
}
