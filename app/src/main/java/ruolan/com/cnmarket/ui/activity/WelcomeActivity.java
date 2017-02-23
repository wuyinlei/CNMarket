package ruolan.com.cnmarket.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.eftimoff.androipathview.PathView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ruolan.com.cnmarket.R;
import ruolan.com.cnmarket.common.Constants;
import ruolan.com.cnmarket.common.utils.ACache;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.img_start)
    ImageView mImageView;
    @BindView(R.id.activity_welcome)
    LinearLayout mActivityWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        //、 mImageView.
        ObjectAnimator animator = ObjectAnimator.ofFloat(mActivityWelcome,"alpha",0.0f,1.0f);
        animator.setDuration(2000);//动画执行的时间

        animator.start();

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                jump();
            }
        });

//        mPathView.getPathAnimator()
//                .delay(1000)
//                .duration(3000)
//                .interpolator(new AccelerateDecelerateInterpolator())
//                .listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
//                    @Override
//                    public void onAnimationEnd() {
//
//                    }
//                });
    }

    private void jump() {
        String IS_SHOW_GUIDE = ACache.get(this).getAsString(Constants.IS_SHOW_GUIDE);
        // 第一次启动进入引导页面
        if(null == IS_SHOW_GUIDE){
            startActivity(new Intent(this,GuideActivity.class));

        }
        else{
            startActivity(new Intent(this,MainActivity.class));
        }


        this.finish();
    }
}
