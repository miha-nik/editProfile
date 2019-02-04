package com.example.userprofile.domain.repository;

import com.example.userprofile.data.model.EditUser;
import com.example.userprofile.data.model.User;
import java.util.List;
import io.reactivex.Observable;

public interface UserRepository {
  Observable<User> getUser();
  Observable<User> updateUser(User user);
  Observable<EditUser> editableUser();
}
