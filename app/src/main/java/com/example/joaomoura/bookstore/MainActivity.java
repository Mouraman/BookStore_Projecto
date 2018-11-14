package com.example.joaomoura.bookstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.joaomoura.bookstore.Adapter.MeuAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvLivros;
    private RecyclerView.LayoutManager layoutManager;
    private MeuAdapter adapter;
    private List<String> livros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvLivros = (RecyclerView) findViewById(R.id.rvLivros);
        rvLivros.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rvLivros.setLayoutManager(layoutManager);



        adapter = new MeuAdapter(this, livros);
        rvLivros.setAdapter(adapter);

    }
}
