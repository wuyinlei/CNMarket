package ruolan.com.cnmarket.common.rx.subscribe;

import android.content.Context;



public abstract class ProgressDialogSubscriber<T> extends ErrorHandlerSubscriber<T>
        implements ProgressDialogHandler.OnProgressCancelListener {

//    private ProgressDialog mProgressDialog;

    private ProgressDialogHandler mProgressDialogHandler;

    private Context mContext;

    public ProgressDialogSubscriber(Context context) {
        super(context);
        this.mContext = context;
        mProgressDialogHandler = new ProgressDialogHandler(mContext,true,this);
    }


    @Override
    public void onStart() {
        super.onStart();
        if (isShowDialog())
            this.mProgressDialogHandler.showProgressDialog();
    }

    @Override
    public void onCompleted() {
        if (isShowDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();
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
        if (isShowDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();
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
