package Adaptors;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.youthgeneration.Books;
import com.example.youthgeneration.MainActivity;
import com.example.youthgeneration.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import ModelJavaClass.BookTemplateCreated;
import ModelJavaClass.SellBookModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class TopBuyBooksAdapter extends RecyclerView.Adapter<TopBuyBooksAdapter.ViewHolder> {

    private Context mContext;
    private RecyclerView recyclerView;
    private ArrayList <SellBookModel> book = new ArrayList<>();

    public TopBuyBooksAdapter() {

    }

    public TopBuyBooksAdapter(Context mContext, RecyclerView anyrecylerview,ArrayList<SellBookModel> book) {
        this.mContext = mContext;
        this.recyclerView = anyrecylerview;
        this.book = book;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookstopcardviewmodel,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        String[] imageUrl = new String[2];
        imageUrl[0] = "https://images.unsplash.com/photo-1507842217343-583bb7270b66?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80";
        imageUrl[1] = "Buy Books";

        Glide.with(mContext)
                .asBitmap()
                .load(imageUrl[0])
                .into(holder.img);

        holder.txt.setText(imageUrl[1]);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recyclerView.setAdapter(new BottomBuyBookAdapter(mContext,book));
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
