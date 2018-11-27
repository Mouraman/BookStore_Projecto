package com.example.joaomoura.bookstore;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joaomoura.bookstore.model.Book;
import com.example.joaomoura.bookstore.model.GetDetailBook;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailFragment extends Fragment {
    private View view;
    private TextView tvNomeLivro;
    private TextView tvDataLivro;
    private ImageView ivImagemLivro;
    private TextView tvDescricao;

    public DetailFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup rvLivros, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragments_detail, rvLivros, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String id = getArguments().getString("id");
        Retrofit retrofit = new Retrofit.
                Builder().
                baseUrl("https://www.googleapis.com/books/v1/volumes/").
                addConverterFactory(GsonConverterFactory.create()).
                build();

        GetDetailBook detailBook = retrofit.create(GetDetailBook.class);

        Call<List<Book>> bookCall = detailBook.getDetailBook(id);

        bookCall.enqueue(new Callback<List<Book>>() {
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response){
                if(response.isSuccessful()){
                    Log.d("RETROFIT","DEU CARAIO!!!");

                    prepareView(response.body().get(0));
                }else{
                    Log.d("RETROFIT","ALGO DE ERRADO NAO ESTA CERTO!!!");
                }
            }
            public void onFailure(Call<List<Book>> call,Throwable t){
                Log.d("RETROFIT", "DEU ME#RDA!!!!");
            }
        });


    }
private void prepareView (Book book){
        tvNomeLivro = (TextView) view.findViewById(R.id.tvNomeDetail);
        tvDataLivro = (TextView) view.findViewById(R.id.tvDateDetail);
        tvDescricao = (TextView) view.findViewById(R.id.tvDetail);
        ivImagemLivro=(ImageView)view.findViewById(R.id.ivImagemLivro);

        tvNomeLivro.setText(book.getTitle());
        tvDataLivro.setText(book.getPublishedDate());
        tvDescricao.setText(book.getDescription());
    Picasso.get().load(book.getThumbnail()).into(ivImagemLivro);
}

}
