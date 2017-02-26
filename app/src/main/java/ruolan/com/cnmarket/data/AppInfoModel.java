package ruolan.com.cnmarket.data;

import javax.crypto.spec.OAEPParameterSpec;

import retrofit2.Callback;
import ruolan.com.cnmarket.been.AppInfo;
import ruolan.com.cnmarket.been.BaseBean;
import ruolan.com.cnmarket.been.IndexBean;
import ruolan.com.cnmarket.been.PageBean;
import ruolan.com.cnmarket.data.http.ApiService;
import rx.Observable;

/**
 * Created by wuyinlei on 2017/2/22.
 */

public class AppInfoModel {

    private ApiService mApiService;

    public AppInfoModel(ApiService apiService) {
        mApiService = apiService;
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps() {
//        HttpManager manager = new HttpManager();
//        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient())
//                .create(ApiService.class);
//        mApiService.getApps("{'page':0}").enqueue(callback);

        return mApiService.getApps("{'page':0}");
    }

    /**
     * 获取首页推荐数据
     * @return
     */
    public Observable<BaseBean<IndexBean>> getIndex() {
        return mApiService.index();
    }

    /**
     * 获取排行版数据
     * @param page  页数
     * @return
     */
    public Observable<BaseBean<PageBean<AppInfo>>> topList(int page){
        return mApiService.topList(page);
    }
}
