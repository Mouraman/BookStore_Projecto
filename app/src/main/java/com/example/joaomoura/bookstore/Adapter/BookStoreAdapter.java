package com.example.joaomoura.bookstore.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joaomoura.bookstore.R;
import com.example.joaomoura.bookstore.model.Book;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class BookStoreAdapter extends RecyclerView.Adapter<BookStoreAdapter.BookViewHolder> {
private Context context;
private List<Book> livros;
public static SetOnClickListBook setOnClickListBook;
public BookStoreAdapter(Context context, List<Book> livros, SetOnClickListBook setOnClickListBook){
    this.context=context;
    this.livros=livros;
    this.setOnClickListBook= setOnClickListBook;
}
public static class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView ivImagemLivro;
    TextView tvNomeLivro;
    TextView tvDataLivro;
    TextView tvDetail;
    public BookViewHolder(View itemView){
        super(itemView);
        ivImagemLivro =(ImageView) itemView.findViewById(R.id.ivImagemLivro);
        tvNomeLivro=(TextView) itemView.findViewById(R.id.tvNomeLivro);
        tvDataLivro= (TextView) itemView.findViewById(R.id.tvDataLivro);
        tvDetail = (TextView) itemView.findViewById(R.id.tvDetail);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        setOnClickListBook.setBookItemClick(this.getLayoutPosition());
    }
}

public BookViewHolder onCreateViewHolder (ViewGroup viewGroup, int i){
    View view = LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
    BookViewHolder bookViewHolder = new BookViewHolder(view);
    return bookViewHolder;
}

    public void onBindViewHolder( BookViewHolder bookViewHolder, int position) {
        bookViewHolder.tvNomeLivro.setText(livros.get(position).getTitle());
        bookViewHolder.tvDataLivro.setText(livros.get(position).getPublishedDate());
        bookViewHolder.tvDetail.setText(livros.get(position).getDescription());
        Picasso.get().load(livros.get(position).getThumbnail()).into(bookViewHolder.ivImagemLivro);

    }

    public int getItemCount() {
        return livros.size();
    }



}

