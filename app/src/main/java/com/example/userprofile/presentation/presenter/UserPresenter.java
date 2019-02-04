package com.example.userprofile.presentation.presenter;

import com.example.userprofile.data.model.City;
import com.example.userprofile.data.model.EditUser;
import com.example.userprofile.data.model.SingleChoiceAttributes;
import com.example.userprofile.data.model.User;

import java.util.List;

public interface UserPresenter {

    interface View{
        public void showLoading();
        public void hideLoading();
    }

    interface ShowView extends View{
        public void set(User user);
        public void showRetry();
        public void hideRetry();
    }
    interface EditView extends View{
        public void setUser(User user);
        public void setAttr(User user, SingleChoiceAttributes atrr, List<City> cities);
        public User getUserOnValidation();
        public void error();
        public void goBack();
    }
    interface Presenter{
        public void attachView(UserPresenter.View view);
        void detachView();
        void start();
    }
}
