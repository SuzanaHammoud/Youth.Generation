package Adaptors;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youthgeneration.R;

import java.util.ArrayList;

import ModelJavaClass.BookTemplateCreated;

import com.example.youthgeneration.SelectedBook;

public class BooksBottomModelAdapter extends RecyclerView.Adapter<BooksBottomModelAdapter.ViewHolder>{

    // vars
    private Context mContext;
    private ArrayList<BookTemplateCreated> booksCreated = new ArrayList<>();

    public BooksBottomModelAdapter() {

    }

    public BooksBottomModelAdapter(Context mContext, ArrayList<BookTemplateCreated> booksCreated) {
        this.mContext = mContext;
        this.booksCreated = booksCreated;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booksbottomcardviewmodel,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(booksCreated.get(position).getImageUrl())
                .into(holder.img);

        holder.title.setText(booksCreated.get(position).getTitle());
        holder.author.setText(booksCreated.get(position).getAuthor());
        holder.edition.setText(booksCreated.get(position).getEdition());
        holder.isbn.setText(booksCreated.get(position).getIsbn());

        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, SelectedBook.class);
                i.putExtra("imageUrl",booksCreated.get(position).getImageUrl());
                i.putExtra("description",booksCreated.get(position).getDescription());
                i.putExtra("isbn",booksCreated.get(position).getIsbn());
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return booksCreated.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        // vars in our cardholder
        private ImageView img;
        private TextView title, author, edition,isbn;
        private RelativeLayout parentlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.Books_bottomImgid);
            title = itemView.findViewById(R.id.Books_bottomTitleid);
            author = itemView.findViewById(R.id.Books_bottomAuthorid);
            parentlayout = itemView.findViewById(R.id.bottomparentlayoutid);
            edition = itemView.findViewById(R.id.Books_bottomEditionid);
            isbn = itemView.findViewById(R.id.Books_bottomIsbnid);

        }
    }


}
