package com.example.userprofile.domain.interactor;

import com.example.userprofile.data.model.User;
import com.example.userprofile.domain.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetEditableUserTest {

    private GetEditableUser getEditableUser;
    @Mock
    UserRepository mockRepository;

    @Before
    public void setUp() throws Exception {
        getEditableUser = new GetEditableUser(mockRepository);
    }

    @Test
    public void userWithAttributes() {
        getEditableUser.userWithAttributes();
        verify(mockRepository).editableUser();
    }

    @Test
    public void updateUser() {
        User user = new User();
        getEditableUser.updateUser(user);
        verify(mockRepository).updateUser(user);
    }
}