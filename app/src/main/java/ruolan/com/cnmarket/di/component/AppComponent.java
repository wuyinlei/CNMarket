package ruolan.com.cnmarket.di.component;


import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import ruolan.com.cnmarket.CNMarketApplication;
import ruolan.com.cnmarket.common.rx.RxErrorHandler;
import ruolan.com.cnmarket.data.http.ApiService;
import ruolan.com.cnmarket.di.module.AppModule;
import ruolan.com.cnmarket.di.module.HttpModule;

@Component(modules = {AppModule.class, HttpModule.class})
@Singleton
public interface AppComponent {

     ApiService getApiService();

     public CNMarketApplication getApplicaption();

     RxErrorHandler getRxErrorHandler();

}
