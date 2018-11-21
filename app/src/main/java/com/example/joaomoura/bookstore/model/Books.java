package com.example.joaomoura.bookstore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Books {
    @SerializedName("items")
    @Expose
    public List<Book> books;
}
