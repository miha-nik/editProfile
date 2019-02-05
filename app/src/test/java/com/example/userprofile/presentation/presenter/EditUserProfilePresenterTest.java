package com.example.userprofile.presentation.presenter;

import com.example.userprofile.data.model.City;
import com.example.userprofile.data.model.EditUser;
import com.example.userprofile.data.model.Ethnicity;
import com.example.userprofile.data.model.Figure;
import com.example.userprofile.data.model.Gender;
import com.example.userprofile.data.model.Picture;
import com.example.userprofile.data.model.Religion;
import com.example.userprofile.data.model.SingleChoiceAttributes;
import com.example.userprofile.data.model.User;
import com.example.userprofile.domain.interactor.GetEditableUser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EditUserProfilePresenterTest {

    private EditUserProfilePresenter presenter;

    @Mock
    private GetEditableUser mockEditableUser;
    @Mock
    private UserPresenter.EditView mockView;

    @Before
    public void setUp() throws Exception {
        presenter = new EditUserProfilePresenter(mockEditableUser, Schedulers.trampoline());
        presenter.attachView(mockView);
    }

    @Test
    public void startSuccess() {

        EditUser user = new EditUser();
        when(mockEditableUser.userWithAttributes()).thenReturn(Observable.<EditUser>just(user));
        presenter.start();
        verify(mockView).showLoading();
        verify(mockView).hideLoading();
        verify(mockView).setUser(user.getUser());
        verify(mockView).setAttr(user.getUser(), user.getSingleChoiceAttributes(), user.getCities());
    }

    @Test
    public void pressedSave() {
        User user = getFakeUser();
        when(mockView.getUserOnValidation()).thenReturn(user);
        when(mockEditableUser.updateUser(user)).thenReturn(Observable.<User>just(user));
        presenter.pressedSave();
        verify(mockView).getUserOnValidation();
        verify(mockView,never()).error();
        verify(mockView).hideLoading();
        verify(mockView).updateReady();
    }

    private User getFakeUser(){
        return new User("AboutMe",
                "Birthday",
                "DisplayName",
                new Ethnicity(),
                new Figure(),
                new Gender(),
                "Height",
                new City(),
                new Picture(),
                new Religion()
        );
    }
}