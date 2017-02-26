package ruolan.com.cnmarket.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import ruolan.com.cnmarket.R;
import ruolan.com.cnmarket.been.AppInfo;
import ruolan.com.cnmarket.common.imageloader.ImageLoader;
import ruolan.com.cnmarket.ui.widget.BannerLayout;

/**
 * Created by wuyinlei on 2017/2/26.
 */

public class AppInfoAdapter extends BaseQuickAdapter<AppInfo,BaseViewHolder> {

    String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";



    public AppInfoAdapter() {
        super(R.layout.template_appinfo);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        ImageLoader.load(baseImgUrl + item.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name,item.getDisplayName())
                .setText(R.id.txt_brief,item.getBriefShow());
    }
}
