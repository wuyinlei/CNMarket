package ruolan.com.cnmarket.presenter.contract;

import java.util.List;

import ruolan.com.cnmarket.been.AppInfo;
import ruolan.com.cnmarket.been.IndexBean;
import ruolan.com.cnmarket.presenter.BasePresenter;
import ruolan.com.cnmarket.ui.BaseView;

/**
 * Created by wuyinlei on 2017/2/22.
 */

public interface RecommendContract {

    interface View extends BaseView {
        void showResult(IndexBean appInfos);

        void showNoData();

        void showError(String msg);
    }

//    interface Presenter extends BasePresenter {
//
//
//        void requestDatas();
//
//    }
}
