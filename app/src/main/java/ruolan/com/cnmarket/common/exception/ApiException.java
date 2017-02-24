package ruolan.com.cnmarket.common.exception;

import android.hardware.fingerprint.FingerprintManager;

import retrofit2.http.PUT;

/**
 * Created by wuyinlei on 2017/2/23.
 */

public class ApiException extends BaseException{


    public ApiException(int status, String message) {
        super(status, message);
    }
}
