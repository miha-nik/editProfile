package com.example.userprofile.presentation.presenter;

import android.util.Log;

import com.example.userprofile.data.model.User;
import com.example.userprofile.domain.interactor.GetUser;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class UserProfilePresenter implements UserPresenter.Presenter {

    private UserPresenter.ShowView view;
    private GetUser getUser;
    private CompositeDisposable disposable;

    private Scheduler observerThread;

    @Inject
    UserProfilePresenter(GetUser getUser){
        this.getUser = getUser;
        this.disposable = new CompositeDisposable();
        this.observerThread = AndroidSchedulers.mainThread();
    }

    UserProfilePresenter(GetUser getUser, Scheduler observerThread){
        this.getUser = getUser;
        this.disposable = new CompositeDisposable();
        this.observerThread = observerThread;
    }

    @Override
    public void attachView(UserPresenter.View view) {
        this.view = (UserPresenter.ShowView)view;
    }

    @Override
    public void detachView() {
        if(view!=null){
            view.hideLoading();
            view.hideRetry();
        }
        this.view = null;
        disposable.clear();
    }

    @Override
    public void start() {
        if(view!=null){
            view.hideRetry();
            view.showLoading();
        }
        getUser();
    }

    private void getUser(){
        disposable.add(this.getUser.user()
                .subscribeOn(Schedulers.io())
                .observeOn(observerThread)
                .doOnTerminate(()->{
                    if(UserProfilePresenter.this.view!=null)
                        UserProfilePresenter.this.view.hideLoading();
                })
                .subscribe(
                        user -> {
                            if(UserProfilePresenter.this.view!=null){
                                UserProfilePresenter.this.view.set(user);
                            }
                        },
                        error ->{
                            if(UserProfilePresenter.this.view!=null){
                                UserProfilePresenter.this.view.showRetry();
                            }
                            }
                ));
    }
}
