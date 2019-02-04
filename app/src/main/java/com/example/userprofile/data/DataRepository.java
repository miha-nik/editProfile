package com.example.userprofile.data;

import com.example.userprofile.data.model.City;
import com.example.userprofile.data.model.EditUser;
import com.example.userprofile.data.model.SingleChoiceAttributes;
import com.example.userprofile.data.model.User;
import com.example.userprofile.data.net.ApiService;
import com.example.userprofile.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class DataRepository implements UserRepository {

    private final static int CURENT_USER_ID = 0;

    private ApiService apiRequest;

    private User savedUser = null;
    @Inject
    DataRepository(ApiService apiRequest){
        this.apiRequest = apiRequest;
    }

    @Override
    public Observable<User> getUser() {
        return this.apiRequest.getUser(CURENT_USER_ID).map(
                user ->
                        saveUser(user)
        );
    }

    @Override
    public Observable<User> updateUser(User user) {
        return this.apiRequest.updateUser(user, CURENT_USER_ID).map(
                saveduser ->
                        saveUser(saveduser)
        );
    }

    @Override
    public Observable<EditUser> editableUser() {
        return Observable.zip(this.apiRequest.getAttributes(), this.apiRequest.getCities(), new BiFunction<SingleChoiceAttributes, List<City>, EditUser>() {
            @Override
            public EditUser apply(SingleChoiceAttributes atrr, List<City> cities) throws Exception {
                return new EditUser(DataRepository.this.savedUser, atrr,cities);
            }
        });
    }

    private User saveUser(User user){
        this.savedUser = user;
        return savedUser;
    }

}
