package ruolan.com.cnmarket.di.module;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ruolan.com.cnmarket.CNMarketApplication;

@Module
public class AppModule {

    private CNMarketApplication mApplication;

    public AppModule(CNMarketApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public CNMarketApplication provideApplicaption(){
        return mApplication;
    }


    @Provides
    @Singleton
    public Gson provideGson(){
        return new Gson();
    }
}
