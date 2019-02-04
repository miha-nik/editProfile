package com.example.userprofile.presentation;

import com.example.userprofile.presentation.di.components.ApplicationComponent;
import com.example.userprofile.presentation.di.components.DaggerApplicationComponent;
import com.example.userprofile.presentation.di.modules.ApplicationModule;

public class Application extends android.app.Application {

    private ApplicationComponent component;
    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
