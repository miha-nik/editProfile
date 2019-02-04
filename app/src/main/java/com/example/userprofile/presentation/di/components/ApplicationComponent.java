package com.example.userprofile.presentation.di.components;


import com.example.userprofile.presentation.di.modules.ApplicationModule;
import com.example.userprofile.data.net.ApiServiceModul;
import com.example.userprofile.presentation.activity.UserProfileActivity;
import com.example.userprofile.presentation.activity.EditUserProfileActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class, ApiServiceModul.class})
public interface ApplicationComponent {

    void inject(UserProfileActivity target);
    void inject(EditUserProfileActivity target);
}
