package com.example.userprofile.domain.interactor;

import com.example.userprofile.domain.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetUserTest {

    GetUser getUser;
    @Mock
    UserRepository mockRepository;

    @Before
    public void setUp() throws Exception {
        getUser = new GetUser(mockRepository);
    }

    @Test
    public void user() {
        getUser.user();
        verify(mockRepository).getUser();
    }
}