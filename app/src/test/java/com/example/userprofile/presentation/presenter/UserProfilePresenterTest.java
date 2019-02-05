package com.example.userprofile.presentation.presenter;

import com.example.userprofile.data.model.User;
import com.example.userprofile.domain.interactor.GetUser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserProfilePresenterTest {

    private UserProfilePresenter presenter;
    @Mock
    UserPresenter.ShowView mockView;
    @Mock
    GetUser mockGetUser;

    @Before
    public void setUp() throws Exception {
        presenter = new UserProfilePresenter(mockGetUser, Schedulers.trampoline());
        presenter.attachView(mockView);
    }

    @Test
    public void startSuccess() {
        when(mockGetUser.user()).thenReturn(Observable.<User>just(new User()));
        presenter.start();
        verify(mockView).hideRetry();
        verify(mockView).showLoading();
        verify(mockView).hideLoading();
        verify(mockView).set(any(User.class));
        verify(mockView,never()).showRetry();
    }

    @Test
    public void startFail() {
        when(mockGetUser.user()).thenReturn(Observable.error(UnknownError::new));
        presenter.start();
        verify(mockView).hideRetry();
        verify(mockView).showLoading();
        verify(mockView).hideLoading();
        verify(mockView,never()).set(any(User.class));
        verify(mockView).showRetry();
    }

    @Test
    public void detachView() {
        presenter.detachView();
    }
}