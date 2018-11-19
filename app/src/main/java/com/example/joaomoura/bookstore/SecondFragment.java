package com.example.joaomoura.bookstore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private RecyclerView lvBook;
    private RecyclerView.LayoutManager layoutManager;
    private Books books;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_,container,false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        lvBook = (RecyclerView) view.findViewById(R.id.listBook);
        lvBook.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(getActivity());
        lvBook.setLayoutManager(layoutManager);

        books = new Books();

        Retrofit retrofit = new Retrofit.
                Builder().
                addConverterFactory(GsonConverterFactory.create()).
                baseUrl("https://www.googleapis.com/books/v1/volumes?").build();
        GetBookService bookService = retrofit.create(GetBookService.class);

        Call<List<Book>> booksCall = bookService.getListBook();

        booksCall.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()){
                    Log.d("RETROFIT","OK PORREIRO PA");

                    books.books=response.body();
                    BookStoreAdapter bookStoreAdapter = new BookStoreAdapter(getActivity(),books.books,SecondFragment.this);

                    lvBook.setAdapter(bookStoreAdapter);


                }else{
                    Log.d("RETROFIT","ALGUM DE ERRADO NAO ESTA CERTO!!!");
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.d("RETROFIT", "DEU MERDA CARAIO!!!!!11!!!!!11!one!1!ONE!!1ONE!!ONE!11!one!1!one!!ONE!1");
                Log.d("RETROFIT",t.getMessage());
            }
        });
    }

    @Override
    public void setBookItemClick(int position) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",books.books.get(position).getId());
        detailFragment.setArguments(bundle);
        getActivity().getFragmentManager().beginTransaction().replace(R.id.rvLivros, detailFragment, "detail").addToBackStack(null).commit();

    }
}
