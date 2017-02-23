package ruolan.com.cnmarket.ui.fragment;

import android.content.pm.FeatureInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.PUT;
import ruolan.com.cnmarket.R;


public class GuideFragment extends Fragment {

    @BindView(R.id.imgView)
    ImageView mImgView;
    @BindView(R.id.text)
    TextView mText;
    @BindView(R.id.rootView)
    LinearLayout mRootView;

    View mView;

    public static final String IMG_ID = "IMG_ID";
    public static final String COLOR_ID = "COLOR_ID";
    public static final String TEXT_ID ="TEXT_ID";

    public static GuideFragment newInstance(int imgResId,int bgColorResId,int textResId){
        GuideFragment guideFragment = new GuideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(IMG_ID,imgResId);
        bundle.putInt(COLOR_ID, bgColorResId);
        bundle.putInt(TEXT_ID,textResId);
        guideFragment.setArguments(bundle);
        return guideFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_guide, container, false);
        ButterKnife.bind(this, mView);
        initData();
        return mView;
    }

    private void initData() {
        Bundle bundle = getArguments();
        int colorId = bundle.getInt(COLOR_ID);
        int imgId = bundle.getInt(IMG_ID);
        int textId = bundle.getInt(TEXT_ID);

        mRootView.setBackgroundColor(getResources().getColor(colorId));
        mImgView.setImageResource(imgId);
        mText.setText(textId);
    }
}
