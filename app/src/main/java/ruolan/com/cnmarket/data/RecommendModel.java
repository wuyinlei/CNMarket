package ruolan.com.cnmarket.data;

import retrofit2.Callback;
import ruolan.com.cnmarket.been.AppInfo;
import ruolan.com.cnmarket.been.BaseBean;
import ruolan.com.cnmarket.been.PageBean;
import ruolan.com.cnmarket.data.http.ApiService;
import rx.Observable;

/**
 * Created by wuyinlei on 2017/2/22.
 */

public class RecommendModel {

    private ApiService mApiService;

    public RecommendModel(ApiService apiService) {
        mApiService = apiService;
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps() {
//        HttpManager manager = new HttpManager();
//        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient())
//                .create(ApiService.class);
//        mApiService.getApps("{'page':0}").enqueue(callback);

        return  mApiService.getApps("{'page':0}");
    }
}
