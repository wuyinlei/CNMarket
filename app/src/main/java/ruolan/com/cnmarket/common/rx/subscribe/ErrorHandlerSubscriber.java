package ruolan.com.cnmarket.common.rx.subscribe;



import android.content.Context;
import android.util.Log;

import ruolan.com.cnmarket.common.exception.BaseException;
import ruolan.com.cnmarket.common.rx.RxErrorHandler;

public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscribe<T> {

    private RxErrorHandler mRxErrorHandler = null;

    private Context mContext;

    public ErrorHandlerSubscriber(Context context) {
        this.mContext = context;
        mRxErrorHandler = new RxErrorHandler(mContext);
    }

    @Override
    public void onError(Throwable e) {
        BaseException baseException =  mRxErrorHandler.handlerError(e);

        if(baseException==null){
            e.printStackTrace();
            Log.d("ErrorHandlerSubscriber",e.getMessage());
        }
        else {
            mRxErrorHandler.showErrorMessage(baseException);
        }
    }
}
