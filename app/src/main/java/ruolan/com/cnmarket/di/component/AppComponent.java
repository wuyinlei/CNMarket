package ruolan.com.cnmarket.di.component;


import javax.inject.Singleton;

import dagger.Component;
import ruolan.com.cnmarket.data.http.ApiService;
import ruolan.com.cnmarket.di.module.AppModule;
import ruolan.com.cnmarket.di.module.HttpModule;

@Component(modules = {AppModule.class, HttpModule.class})
@Singleton
public interface AppComponent {

     ApiService getApiService();
}
