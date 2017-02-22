package ruolan.com.cnmarket.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ruolan.com.cnmarket.R;
import ruolan.com.cnmarket.been.AppInfo;

/**
 * Created by wuyinlei on 2017/2/22.
 */

public class RecommendAppAdapter extends RecyclerView.Adapter<RecommendAppAdapter.ViewHolder> {


    private List<AppInfo> mDatas;

    private Context mContext;

    public RecommendAppAdapter(Context context, List<AppInfo> datas) {
        this.mContext = context;
        mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.template_recomend_app, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        AppInfo appInfo = mDatas.get(position);

//        holder.text_size.setText(appInfo.);
        String baseImageUrl = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
        Picasso.with(mContext).load(baseImageUrl + appInfo.getIcon()).into(holder.img_icon);
        holder.text_title.setText(appInfo.getDisplayName());
        holder.text_size.setText((appInfo.getApkSize()/1024/1024) + "MB");
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_icon;
        TextView text_title, text_size;


        public ViewHolder(View itemView) {
            super(itemView);
            img_icon = (ImageView) itemView.findViewById(R.id.img_icon);
            text_title = (TextView) itemView.findViewById(R.id.text_title);
            text_size = (TextView) itemView.findViewById(R.id.text_size);
        }
    }
}
