package ruolan.com.cnmarket.common.utils;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.tbruyelle.rxpermissions.RxPermissions;

import rx.Observable;
import rx.Subscriber;


public class RxPermissionUtil {

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void readPhoneState(Activity activity){
        requestPermission(activity, Manifest.permission.PACKAGE_USAGE_STATS)
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }
                });
    }


    public static Observable<Boolean> requestPermission(Activity activity,String permission){
        RxPermissions rxPermissions = new RxPermissions(activity);
        return rxPermissions.request(permission);
    }

    public static Observable.Transformer<Object,Boolean> ensure(Activity activity,String permission){
        RxPermissions rxPermissions = new RxPermissions(activity);
        return rxPermissions.ensure(permission);
    }
}
