package ruolan.com.cnmarket.di;

import dagger.Component;
import ruolan.com.cnmarket.ui.fragment.RecommendFragment;

/**
 * Created by wuyinlei on 2017/2/22.
 */

@Component  (modules = RecommendModule.class)
public interface RecommendComponent {


    void inject(RecommendFragment fragment);
}
