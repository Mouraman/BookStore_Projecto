package com.example.joaomoura.bookstore.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetBookService {
    @GET("books")
    Call<List<Book>> getListBook();
}
