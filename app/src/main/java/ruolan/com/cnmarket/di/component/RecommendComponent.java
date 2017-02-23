package ruolan.com.cnmarket.di.component;

import dagger.Component;
import ruolan.com.cnmarket.di.FragmentScope;
import ruolan.com.cnmarket.di.module.RecommendModule;
import ruolan.com.cnmarket.ui.fragment.RecommendFragment;

@FragmentScope  //自定义的范围
@Component  (modules = RecommendModule.class,dependencies = AppComponent.class)
public interface RecommendComponent {


    void inject(RecommendFragment fragment);
}
