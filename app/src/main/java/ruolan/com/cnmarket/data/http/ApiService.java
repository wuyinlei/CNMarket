package ruolan.com.cnmarket.data.http;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ruolan.com.cnmarket.been.AppInfo;
import ruolan.com.cnmarket.been.BaseBean;
import ruolan.com.cnmarket.been.PageBean;
import ruolan.com.cnmarket.been.requestbean.LoginRequestBean;
import rx.Observable;

public interface ApiService {


    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";



//    @GET("featured")
//    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);

    @GET("featured2")
    public Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);

    @GET("index")
    public Observable<BaseBean> index();

    @GET("topList")
    public Observable<BaseBean> topList(@Query("page") int page);

    @POST("login")
    public Observable<BaseBean> login(@Body LoginRequestBean bean);

    @FormUrlEncoded
    @POST
    public void login2(@Field("phone") String phone);
}
