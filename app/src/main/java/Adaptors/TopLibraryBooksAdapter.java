package Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youthgeneration.R;

import java.util.ArrayList;

import ModelJavaClass.BookTemplateCreated;
import de.hdodenhof.circleimageview.CircleImageView;

public class TopLibraryBooksAdapter extends  RecyclerView.Adapter<TopLibraryBooksAdapter.ViewHolder>{

    private Context mContext;
    private RecyclerView recyclerView;
    private ArrayList <BookTemplateCreated> book = new ArrayList<>();

    public TopLibraryBooksAdapter() {

    }

    public TopLibraryBooksAdapter(Context mContext, RecyclerView anyrecycler, ArrayList<BookTemplateCreated> book) {
        this.mContext = mContext;
        this.recyclerView = anyrecycler;
        this.book = book;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookstopcardviewmodel,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        // or return new ViewHolder (view) this way looks more cleaner
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String[] imageUrl = new String[2];
        imageUrl[0] = "https://manna.amazingfacts.org/amazingfacts/website/amazingfacts/images/skin/2018/free-book-library.jpg";
        imageUrl[1] = "Library Books";

        Glide.with(mContext)
                .asBitmap()
                .load(imageUrl[0])
                .into(holder.img);
        holder.txt.setText(imageUrl[1]);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recyclerView.setAdapter(new BooksBottomModelAdapter(mContext,book));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView img;
        private TextView txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.Books_topModelImgid);
            txt = itemView.findViewById(R.id.Books_topModelTitleid);
        }
    }
}
