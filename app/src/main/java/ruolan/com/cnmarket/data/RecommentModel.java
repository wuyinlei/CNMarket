package ruolan.com.cnmarket.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ruolan.com.cnmarket.been.AppInfo;
import ruolan.com.cnmarket.been.PageBean;
import ruolan.com.cnmarket.data.http.ApiService;
import ruolan.com.cnmarket.data.http.HttpManager;

/**
 * Created by wuyinlei on 2017/2/22.
 */

public class RecommentModel {


    public void  getApps(Callback<PageBean<AppInfo>> callback){
        HttpManager manager = new HttpManager();
        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient())
                .create(ApiService.class);
        apiService.getApps("{'page':0}").enqueue(callback);
    }
}
