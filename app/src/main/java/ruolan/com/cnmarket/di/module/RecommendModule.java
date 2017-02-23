package ruolan.com.cnmarket.di.module;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import ruolan.com.cnmarket.data.RecommendModel;
import ruolan.com.cnmarket.data.http.ApiService;
import ruolan.com.cnmarket.presenter.RecommendPresenter;
import ruolan.com.cnmarket.presenter.contract.RecommendContract;

/**
 * Created by wuyinlei on 2017/2/22.
 */

@Module
public class RecommendModule {

    public RecommendContract.View mView;

    public RecommendModule(RecommendContract.View view) {
        mView = view;
    }

    @Provides
    public RecommendContract.Presenter providePresenter(RecommendContract.View view,RecommendModel module){
        return new RecommendPresenter(view,module);
    }

    @Provides
    public RecommendContract.View provideView(){
        return mView;
    }


    @Provides
    public ProgressDialog provideProgressDialog(RecommendContract.View view){
        return new ProgressDialog(((Fragment)view).getActivity());
    }

    @Provides
    public RecommendModel provideModel(ApiService apiService){
        return new RecommendModel(apiService);
    }



//
//    @Provides
//    public RecommendAppAdapter provideAdapter(){
//        return null;
//    }

}
