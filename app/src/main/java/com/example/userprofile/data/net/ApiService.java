package com.example.userprofile.data.net;

import com.example.userprofile.data.model.City;
import com.example.userprofile.data.model.SingleChoiceAttributes;
import com.example.userprofile.data.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("user")
    Observable<User> getUser(@Query("id") int id);

    @POST("updateuser")
    Observable<User> updateUser(@Body User user, @Query("id") int id);

    @GET("attributes")
    Observable<SingleChoiceAttributes> getAttributes();

    @GET("cities")
    Observable<List<City>> getCities();
}
