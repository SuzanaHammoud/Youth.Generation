package Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youthgeneration.R;

import java.util.ArrayList;

import ModelJavaClass.BookTemplateCreated;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReservedBooksAdapter extends RecyclerView.Adapter<ReservedBooksAdapter.ViewHolder>{

    private ArrayList<BookTemplateCreated> book = new ArrayList<>();
    private Context mContext;

    public ReservedBooksAdapter(ArrayList<BookTemplateCreated> book, Context mContext) {
        this.book = book;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mybooksmodel,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(book.get(position).getImageUrl())
                .into(holder.img);

        holder.title.setText(book.get(position).getTitle());
        holder.author.setText(book.get(position).getAuthor());


    }

    @Override
    public int getItemCount() {
        return book.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView img;
        private TextView title, author;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.mybooksimgid);
            title = itemView.findViewById(R.id.mybookstitleid);
            author = itemView.findViewById(R.id.mybooksauthorid);
        }
    }



}
