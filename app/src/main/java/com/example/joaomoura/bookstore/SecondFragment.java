package com.example.joaomoura.bookstore;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.joaomoura.bookstore.Adapter.BookStoreAdapter;
import com.example.joaomoura.bookstore.Adapter.SetOnClickListBook;
import com.example.joaomoura.bookstore.model.Books;
import com.example.joaomoura.bookstore.model.GetBookService;

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
    private String texto;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_,container,false);

        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        insertItems();
        texto = "android";
        preferences(texto);

        books = new Books();


    }

    private void preferences(String texto) {
        Retrofit retrofit = new Retrofit.
                Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://www.googleapis.com/books/v1/volumes/").build();


        GetBookService bookService =retrofit.create(GetBookService.class);
        Call<Books> booksCall = bookService.getListBook(texto.replaceAll(" ","%20"));

        booksCall.enqueue(new Callback<Books>() {
            @TargetApi(Build.VERSION_CODES.M)
            @RequiresApi(api = Build.VERSION_CODES.M)

            public void onResponse(Call<Books> call, Response<Books> response) {
                if (response.isSuccessful() && response.body().getClass()!=null){
                    Log.d("RETROFIT", "OK");
                    books = response.body();
                    BookStoreAdapter bookStoreAdapter = new BookStoreAdapter(getActivity(),books.books,SecondFragment.this);
                    listBook.setAdapter(bookStoreAdapter);

                }else{
                    Toast.makeText(getContext(),"Sem resultados na pesquisa!",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Books> call, Throwable t) {

            }
        });

    }

    private void insertItems() {

        listBook = (RecyclerView) view.findViewById(R.id.rvLivros);
        listBook.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        listBook.setLayoutManager(layoutManager);

    }

    @Override
    public void setBookItemClick(int position) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", String.valueOf(books.books.get(position).getClass()));
        detailFragment.setArguments(bundle);
        getActivity().getFragmentManager().beginTransaction().replace(R.id.rvLivros, detailFragment, "detail").addToBackStack(null).commit();

    }
}