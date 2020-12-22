package com.example.awaistrialapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderApi {

    @GET("users")
    Call<List<User>> getUsers();
}
