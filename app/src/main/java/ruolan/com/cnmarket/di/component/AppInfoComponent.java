package ruolan.com.cnmarket.di.component;

import dagger.Component;
import ruolan.com.cnmarket.di.FragmentScope;
import ruolan.com.cnmarket.di.module.AppInfoModule;
import ruolan.com.cnmarket.ui.fragment.GameFragment;
import ruolan.com.cnmarket.ui.fragment.RankingFragment;

@FragmentScope  //自定义的范围
@Component  (modules = AppInfoModule.class,dependencies = AppComponent.class)
public interface AppInfoComponent {


    void injectRankingFragment(RankingFragment fragment);

    void injectGameFragment(GameFragment fragment);
}
