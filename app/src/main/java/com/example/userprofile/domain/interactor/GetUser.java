package com.example.userprofile.domain.interactor;

import com.example.userprofile.data.model.User;
import com.example.userprofile.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;


public class GetUser {

    UserRepository repository;

    @Inject
    GetUser(UserRepository repository){
        this.repository = repository;
    }
    public Observable<User> user(){return this.repository.getUser();}
}
