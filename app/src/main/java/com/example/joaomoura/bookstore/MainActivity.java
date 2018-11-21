package com.example.joaomoura.bookstore;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.joaomoura.bookstore.Adapter.BookStoreAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvLivros;
    private RecyclerView.LayoutManager layoutManager;
    android.app.FragmentManager manager;
    android.app.FragmentTransaction transaction;
    private BookStoreAdapter adapter;
    private List<String> livros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        transaction.add(R.id.rvLivros, new SecondFragment());

        transaction.commit();



    }
}
