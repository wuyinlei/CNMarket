package ruolan.com.cnmarket.data.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ruolan.com.cnmarket.been.AppInfo;
import ruolan.com.cnmarket.been.PageBean;

/**
 * Created by wuyinlei on 2017/1/19.
 */

public interface ApiService {

    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    @GET("featured")
    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);

}
