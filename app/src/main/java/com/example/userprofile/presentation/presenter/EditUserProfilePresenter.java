package com.example.userprofile.presentation.presenter;

import android.support.test.espresso.idling.CountingIdlingResource;
import android.util.Log;

import com.example.userprofile.data.model.EditUser;
import com.example.userprofile.data.model.User;
import com.example.userprofile.domain.interactor.GetEditableUser;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


@Singleton
public class EditUserProfilePresenter implements UserPresenter.Presenter {

    private UserPresenter.EditView view;
    private GetEditableUser editableUser;
    private CompositeDisposable disposable;
    private EditUser user;

    private Scheduler observerThread;

    private CountingIdlingResource countingIdlingResource;
    @Inject
    EditUserProfilePresenter(GetEditableUser editableUser){
        this.editableUser = editableUser;
        disposable = new CompositeDisposable();
        this.observerThread = AndroidSchedulers.mainThread();
    }
    EditUserProfilePresenter(GetEditableUser editableUser, Scheduler observerThread){
        this.editableUser = editableUser;
        disposable = new CompositeDisposable();
        this.observerThread = observerThread;
    }

    public void setIdlingResource(CountingIdlingResource countingIdlingResource) {
        this.countingIdlingResource = countingIdlingResource;
    }
    @Override
    public void attachView(UserPresenter.View view) {
        this.view = (UserPresenter.EditView)view;
    }

    @Override
    public void detachView() {
        if(view!=null)view.hideLoading();
        this.view = null;
        disposable.clear();
    }

    @Override
    public void start() {
        getUser();
    }

    private void getUser(){
        if(view!=null)view.showLoading();
        if(countingIdlingResource!=null)countingIdlingResource.increment();
        disposable.add(this.editableUser.userWithAttributes()
                .subscribeOn(Schedulers.io())
                .observeOn(observerThread)
                .doOnTerminate(()->{
                    if(view!=null)view.hideLoading();
                })
                .subscribe(
                        user -> {
                            if(view!=null){
                                view.setUser(user.getUser());
                                view.setAttr(user.getUser(), user.getSingleChoiceAttributes(), user.getCities());
                            }
                            if(countingIdlingResource!=null)
                                countingIdlingResource.decrement();
                        },
                        error ->{
                            Log.d("getUser",error.getLocalizedMessage());
                            if(countingIdlingResource!=null)
                                countingIdlingResource.decrement();
                        }
                ));
    }


    public void pressedSave(){
        if(view==null) return;

        User updateUser = view.getUserOnValidation();

        if(!validateUseer(updateUser)){
            view.error();
            return;
        }

        disposable.add(this.editableUser.updateUser(updateUser)
                .subscribeOn(Schedulers.io())
                .observeOn(observerThread)
                .doOnTerminate(()->{
                    if(view!=null)view.hideLoading();
                })
                .subscribe(
                        user -> {
                            if(view!=null)
                                view.updateReady();
                        },
                        error ->{
                            if(view!=null)
                                view.updateError();
                        }
                ));

    }

    private boolean validateUseer(User user){
        if(user.getDisplayName().isEmpty())return false;
        if(user.getBirthday().isEmpty())return false;
        if(user.getGender()==null)return false;
        if(user.getEthnicity()==null)return false;
        if(user.getReligion()==null)return false;
        if(user.getFigure()==null)return false;
        if(user.getAboutMe().isEmpty())return false;
        if(user.getLocation()==null)return false;
        return true;
    }
}
