package ruolan.com.cnmarket.common.exception;

/**
 * Created by wuyinlei on 2017/2/23.
 */

public class BaseException extends Exception {

    //API 方面

    /**api错误*/
    public static final int API_ERROR=0X0;

    //网络错误
    public static final int NETWORK_ERROR=0x1;

    //http错误
    public static final int HTTP_ERROR=0x2;

    //json错误
    public static final int JSON_ERROR=0x3;

    //未知错误
    public static final int UNKNOWN_ERROR=0x4;

    //运行时异常  包含自定义的一些异常
    public static final int RUNTIME_ERROR=0x5;

    //无法解析该域名
    public static final int UNKNONWNHOST_ERROR=0x6;

    //网络连接超时
    public static final int SOCKET_TIMEOUT_ERROR=0x7;

    //无网络连接
    public static final int SOCKET_ERROR=0x8;


    //服务器错误
    public static final int ERROR_API_SYSTEM = 10000;

    //登录错误  用户名密码错误
    public static final int ERROR_API_LOGIN = 10001;

    //调用无权限api
    public static final int ERROR_API_PERMISSION = 10002;

    //账户被冻结
    public static final int ERROR_API_ACCOUNT_FREEZE = 10003;


    //http方面
    public static final int ERROR_HTTP_400 = 400;

    public static final int ERROR_HTTP_404 = 404;

    public static final int ERROR_HTTP_405 = 405;

    public static final int ERROR_HTTP_500 = 500;


    private int code;
    private String displayMessage;

    public BaseException(){

    }

    public BaseException( int code, String displayMessage) {
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }
}
