package com.example.joaomoura.bookstore.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joaomoura.bookstore.R;

import java.util.List;

public class MeuAdapter extends RecyclerView.Adapter<MeuAdapter.MeuViewHolder> {
private Context context;
private List<String> livros;

public MeuAdapter (Context context, List<String> livros){
    this.context=context;
    this.livros=livros;
}

public MeuViewHolder onCreateViewHolder (ViewGroup viewGroup, int i){
    View view = LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
    MeuViewHolder meuViewHolder = new MeuViewHolder(view);
    return meuViewHolder;
}

    public void onBindViewHolder( MeuViewHolder meuViewHolder, int i) {
        meuViewHolder.tvNomeLivro.setText(livros.get(i));

    }

    public int getItemCount() {
        return livros.size();
    }

    public static class MeuViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvNomeLivro;
        TextView tvDataLivro;

        public MeuViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cvItem);
            tvNomeLivro = (TextView) itemView.findViewById(R.id.tvNomeLivro);
            tvDataLivro = (TextView) itemView.findViewById(R.id.tvDataLivro);

        }
    }
}

