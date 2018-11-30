package com.example.joaomoura.bookstore;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.joaomoura.bookstore.Adapter.BookStoreAdapter;
import com.example.joaomoura.bookstore.Adapter.SetOnClickListBook;
import com.example.joaomoura.bookstore.model.Book;
import com.example.joaomoura.bookstore.model.Books;
import com.example.joaomoura.bookstore.model.GetBookService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondFragment extends Fragment implements SetOnClickListBook {
    private View view;
    private RecyclerView listBook;
    private RecyclerView.LayoutManager layoutManager;
    private Books books;
    private String id;

    public View onCreateView(LayoutInflater inflater, ViewGroup rvLivros, Bundle savedInstanceState) {
        view = LayoutInflater.from(rvLivros.getContext()).inflate(R.layout.fragment_, rvLivros, false);

        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listBook = (RecyclerView) view.findViewById(R.id.rvLivros);
        listBook.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        listBook.setLayoutManager(layoutManager);

        books = new Books();

        Retrofit retrofit = new Retrofit.
                Builder().
                addConverterFactory(GsonConverterFactory.create()).
                baseUrl("https://www.googleapis.com/books/v1/").build();
        GetBookService bookService = retrofit.create(GetBookService.class);


        Call<Books> booksCall = bookService.getListBook("android");

        booksCall.enqueue(new Callback<Books>() {
            @Override
            public void onResponse(Call<Books> call, Response<Books> response) {
                if (response.isSuccessful()) {
                    Log.d("RETROFIT", "DEU!!!!!!!");

                    books = response.body();

                    BookStoreAdapter bookStoreAdapter = new BookStoreAdapter(getActivity(), books.books, SecondFragment.this);

                    listBook.setAdapter(bookStoreAdapter);

                } else {
                    Log.d("RETROFIT", "NOK");
                }
            }


            @Override
            public void onFailure(Call<Books> call, Throwable t) {
                Log.d("RETROFIT", "FAIL");
                Log.d("RETROFIT", t.getMessage());
            }
        });

    }

    @Override
    public void setBookItemClick(int position) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", books.books.get(position).getId());
        detailFragment.setArguments(bundle);
        getActivity().getFragmentManager().beginTransaction().replace(R.id.rvLivros, detailFragment, "detail").addToBackStack(null).commit();

    }
}