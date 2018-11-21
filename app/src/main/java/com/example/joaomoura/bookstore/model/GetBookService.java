package com.example.joaomoura.bookstore.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GetBookService {


    // este e o metodo quando existe uma query
    @GET("volumes?")
    Call<Books> getListBook(@Query("q") String tema );

}