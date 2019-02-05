package com.example.userprofile.data;

import com.example.userprofile.data.model.User;
import com.example.userprofile.data.net.ApiService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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
        repository.getUser();
        verify(mockApiRequest).getUser(any(Integer.class));
    }

    @Test
    public void updateUser() {
        User user = new User();
        repository.updateUser(user);
        verify(mockApiRequest).updateUser(user,any(Integer.class));
    }

    @Test
    public void editableUser() {
        repository.editableUser();

        verify(mockApiRequest).getAttributes();
        verify(mockApiRequest).getCities();
    }
}