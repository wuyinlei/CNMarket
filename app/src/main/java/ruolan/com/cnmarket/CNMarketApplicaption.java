package ruolan.com.cnmarket;

import android.app.Application;
import android.content.Context;

/**
 * Created by wuyinlei on 2017/1/19.
 */

public class CNMarketApplicaption extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static CNMarketApplicaption getApplicaption(Context context){
        return (CNMarketApplicaption) context.getApplicationContext();
    }
}
