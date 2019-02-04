package com.example.userprofile.presentation.di.modules;

import android.app.Application;
import android.content.Context;

import com.example.userprofile.data.DataRepository;
import com.example.userprofile.domain.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private Application application;
    public ApplicationModule(Application application){this.application = application;}

    @Provides
    @Singleton
    public Context provirdeContext(){return application;}

    @Provides
    @Singleton
    public UserRepository provideUserRepository(DataRepository repository){ return repository; }

}
