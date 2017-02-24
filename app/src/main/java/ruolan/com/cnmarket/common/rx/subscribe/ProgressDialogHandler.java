package ruolan.com.cnmarket.common.rx.subscribe;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ruolan.com.cnmarket.R;


public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG=1;

    public static final int DISMISS_PROGRESS_DIALOG = 0;

    private SweetAlertDialog mAlertDialog;

    private Context mContext;

    private boolean cancelable;

    private OnProgressCancelListener mOnProgressCancelListener;

    public ProgressDialogHandler(Context context, boolean cancelable, OnProgressCancelListener cancelListener){
        super();
        this.mContext = context;
        this.cancelable = cancelable;
        this.mOnProgressCancelListener = cancelListener;

        initProgressDialog();
    }

    private void initProgressDialog() {
        if (mAlertDialog == null){
            mAlertDialog = new SweetAlertDialog(mContext,SweetAlertDialog.PROGRESS_TYPE);
            mAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#a5dc86"));
            mAlertDialog.setTitleText(mContext.getResources().getString(R.string.loading));

            if (cancelable){
                mAlertDialog.setCancelText(mContext.getResources().getString(R.string.close));
                mAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.cancel();

                        if (mOnProgressCancelListener != null){
                            mOnProgressCancelListener.onCancelProgress();
                        }

                    }
                });
            }
        }

    }

    public void showProgressDialog(){
        if (mAlertDialog != null && !mAlertDialog.isShowing()){
            mAlertDialog.show();
        }
    }

    public void dismissProgressDialog(){
        if (mAlertDialog != null){
            mAlertDialog.dismiss();
            mAlertDialog = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case SHOW_PROGRESS_DIALOG:
                showProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:

                dismissProgressDialog();
                break;
        }
    }

    interface OnProgressCancelListener{

        void onCancelProgress();
    }


}
