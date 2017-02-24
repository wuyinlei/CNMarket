package ruolan.com.cnmarket.common.rx.subscribe;

import android.app.ProgressDialog;
import android.content.Context;

import dagger.Component;
import ruolan.com.cnmarket.common.rx.RxErrorHandler;


public abstract class ProgressDialogSubscriber<T> extends ErrorHandlerSubscriber<T> {

    private ProgressDialog mProgressDialog;

    private Context mContext;

    public ProgressDialogSubscriber(Context context, RxErrorHandler rxErrorHandler) {
        super(rxErrorHandler);
        this.mContext = context;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (isShowDialog())
            showProgressDialog();
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
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
        dismissProgressDialog();
    }

    private void initProgressDialog() {
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("loading....");
    }

    private void showProgressDialog() {
        mProgressDialog.show();
    }

    private void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
