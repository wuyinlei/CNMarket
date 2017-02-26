package ruolan.com.cnmarket.di.module;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import ruolan.com.cnmarket.data.AppInfoModel;
import ruolan.com.cnmarket.data.http.ApiService;
import ruolan.com.cnmarket.presenter.contract.AppInfoContract;


@Module
public class RankingModule {

    public AppInfoContract.RankingView mView;

    public RankingModule(AppInfoContract.RankingView view) {
        mView = view;
    }

//    @Provides
//    public AppInfoContract.Presenter providePresenter(AppInfoContract.View view,AppInfoModel module){
//        return new RecommendPresenter(view,module);
//    }

    @Provides
    public AppInfoContract.RankingView provideView(){
        return mView;
    }


    @Provides
    public ProgressDialog provideProgressDialog(AppInfoContract.View view){
        return new ProgressDialog(((Fragment)view).getActivity());
    }

    @Provides
    public AppInfoModel provideModel(ApiService apiService){
        return new AppInfoModel(apiService);
    }



//
//    @Provides
//    public RecommendAppAdapter provideAdapter(){
//        return null;
//    }

}
