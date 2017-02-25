package ruolan.com.cnmarket.common.rx.subscribe;

import android.content.Context;

import ruolan.com.cnmarket.common.exception.BaseException;
import ruolan.com.cnmarket.common.rx.RxErrorHandler;
import ruolan.com.cnmarket.ui.BaseView;


public abstract class ProgressSubscriber<T> extends ErrorHandlerSubscriber<T>
        implements ProgressDialogHandler.OnProgressCancelListener {

//    private ProgressDialog mProgressDialog;

    private RxErrorHandler mErrorHandler;

    private Context mContext;

    private BaseView mBaseView;



    public ProgressSubscriber(Context context, BaseView baseView) {
        super(context);
        this.mContext = context;
        this.mBaseView = baseView;
        mErrorHandler = new RxErrorHandler(mContext);
    }


    @Override
    public void onStart() {
        super.onStart();
        if (isShowDialog())
           this.mBaseView.showLoading();
    }

    @Override
    public void onCompleted() {
        if (isShowDialog()){
            this.mBaseView.dismissLoading();
        }
    }

    /**
     * 本次请求是否显示dialog
     *
     * @return  true / false
     */
    protected boolean isShowDialog() {
        return true;
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        BaseException exception = mErrorHandler.handlerError(e);
        if (isShowDialog()){
            this.mBaseView.showError(exception.getDisplayMessage());
        }
    }

//    private void initProgressDialog() {
//        mProgressDialog = new ProgressDialog(mContext);
//        mProgressDialog.setMessage("loading....");
//    }

//    private void showProgressDialog() {
//        mProgressDialogHandler.showProgressDialog();
//    }
//
//    private void dismissProgressDialog() {
//        if (mProgressDialog != null && mProgressDialog.isShowing()) {
//            mProgressDialog.dismiss();
//        }
//    }

    @Override
    public void onCancelProgress() {
        unsubscribe();
    }
}
