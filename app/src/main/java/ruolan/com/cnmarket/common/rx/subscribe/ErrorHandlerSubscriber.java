package ruolan.com.cnmarket.common.rx.subscribe;



import ruolan.com.cnmarket.common.exception.BaseException;
import ruolan.com.cnmarket.common.rx.RxErrorHandler;

public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscribe<T> {

    private RxErrorHandler mRxErrorHandler;

    public ErrorHandlerSubscriber(RxErrorHandler rxErrorHandler) {
        mRxErrorHandler = rxErrorHandler;
    }

    @Override
    public void onError(Throwable e) {
        BaseException exception = mRxErrorHandler.handlerError(e);

        mRxErrorHandler.showErrorMessage(exception);
    }
}
