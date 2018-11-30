package com.example.joaomoura.bookstore.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDetailBook {
    @GET("{.}")
    Call<List<Book>> getDetailBook(@Path("id")String id);
}
