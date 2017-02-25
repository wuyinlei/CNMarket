package ruolan.com.cnmarket.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruolan.com.cnmarket.R;
import ruolan.com.cnmarket.common.utils.DeviceUtils;
import rx.functions.Action1;

public class LoginActivity extends AppCompatActivity {

    private static final int READ_PHONE_STATE_CODE = 1000;
    @BindView(R.id.requestPermission)
    Button mRequestPermission;
    @BindView(R.id.activity_login)
    RelativeLayout mActivityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.requestPermission)
    public void onClick(){

        RxPermissions rxPermissions = new RxPermissions(this);

        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean){
                            //授权成功
                        } else {
                            //授权失败
                        }
                    }
                });

//       if (ActivityCompat.checkSelfPermission(this,
//               Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
//           //未授权的
//           ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},READ_PHONE_STATE_CODE);
//       } else {
//           //已经授权的
//           Toast.makeText(this, DeviceUtils.getIMEI(this) +"fdasfsafsf", Toast.LENGTH_SHORT).show();
//       }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == READ_PHONE_STATE_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //授权成功
                Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, DeviceUtils.getIMEI(this), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "用户拒绝授权", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
