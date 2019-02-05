package com.example.userprofile.data;

import com.example.userprofile.data.model.EditUser;
import com.example.userprofile.data.model.User;
import com.example.userprofile.data.net.ApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataRepositoryTest {


    private DataRepository repository;
    @Mock
    private ApiService mockApiRequest;

    @Before
    public void setUp() throws Exception {
        repository = new DataRepository(mockApiRequest);
    }

    @Test
    public void getUser() {
        User user = new User();
        when(mockApiRequest.getUser(0)).thenReturn(Observable.<User>just(user));
        repository.getUser();
        verify(mockApiRequest).getUser(any(Integer.class));
    }

    @Test
    public void updateUser() {
        User user = new User();
        when(mockApiRequest.updateUser(user,0)).thenReturn(Observable.<User>just(user));
        repository.updateUser(user);
        verify(mockApiRequest).updateUser(any(User.class),any(Integer.class));
    }

    @Test
    public void editableUser() {
//        repository.editableUser();
//
//        verify(mockApiRequest).getAttributes();
//        verify(mockApiRequest).getCities();
    }
}