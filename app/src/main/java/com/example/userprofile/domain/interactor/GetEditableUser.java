package com.example.userprofile.domain.interactor;

import com.example.userprofile.data.model.EditUser;
import com.example.userprofile.data.model.User;
import com.example.userprofile.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetEditableUser {

    UserRepository repository;
    @Inject
    GetEditableUser(UserRepository repository){
        this.repository = repository;
    }

    public Observable<EditUser> userWithAttributes(){return this.repository.editableUser();}
    public Observable<User> updateUser(User user){return this.repository.updateUser(user);}
}
