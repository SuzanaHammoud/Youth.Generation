package Adaptors;
import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youthgeneration.FirebaseDatabaseHelper;
import com.example.youthgeneration.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ModelJavaClass.SellBookModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class BottomBuyBookAdapter extends RecyclerView.Adapter<BottomBuyBookAdapter.ViewHolder>{
    private static final String TAG = "BottomBuyBookAdapter is being created >??";

    private ArrayList<SellBookModel> book = new ArrayList<>();
    private ArrayList<String> keys = new ArrayList<>();
    private Context mContext;


    public BottomBuyBookAdapter(Context mContext, ArrayList<SellBookModel> book) {
        this.mContext = mContext;
        this.book = book;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buybookmodel,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        SellBookModel books = book.get(position);

        Glide.with(mContext)
                .asBitmap()
                .load(book.get(position).getImageUrl())
                .into(holder.img);

        holder.title.setText(books.getBookTitle());
        holder.author.setText(books.getBoookAuthor());
        holder.categories.setText(books.getBookCategory());
        holder.price.setText(books.getBookPrice());
        holder.userid.setText(books.getUserId());
        holder.userEmail.setText(books.getUserEmail());

    }

    @Override
    public int getItemCount() {
        return book.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView img;
        private TextView title, author,categories,price,userid,userEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.BuyBookImageid);
            title = itemView.findViewById(R.id.BuyBookTitleid);
            author = itemView.findViewById(R.id.BuyBookAuthorid);
            categories = itemView.findViewById(R.id.BuyBookCategoryid);
            price = itemView.findViewById(R.id.BuyBookPriceid);
            userid = itemView.findViewById(R.id.BuyBookUserid);
            userEmail = itemView.findViewById(R.id.BuyBookUserEmailid);
        }


    }
}
