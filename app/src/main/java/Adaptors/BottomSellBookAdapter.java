package Adaptors;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.youthgeneration.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ModelJavaClass.SellBookModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class BottomSellBookAdapter extends RecyclerView.Adapter<BottomSellBookAdapter.ViewHolder> {

    private Context mContext;
    DatabaseReference ref;

    public BottomSellBookAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sellbookform,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        String[] imageUrl = new String[2];
        imageUrl[0] = "https://middleeast-business.com/wp-content/uploads/2019/06/library-2.jpg";
        imageUrl[1] = "Sell Books";

        Glide.with(mContext)
                .asBitmap()
                .load(imageUrl[0])
                .into(holder.img);

        ref = FirebaseDatabase.getInstance().getReference("BooksToSell");

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ref.push().getKey();

                SellBookModel book = new SellBookModel(holder.userid.getText().toString(),holder.useremail.getText().toString(),holder.title.getText().toString(),
                                    holder.author.getText().toString(),holder.category.getText().toString(),holder.description.getText().toString(),holder.price.getText().toString(),
                                    holder.imageUrl.getText().toString());

                ref.child(id).setValue(book);


            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView img;
        private EditText title, author, category,price, description, imageUrl, userid, useremail;
        private Button btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.SellBookImageid);
            title = itemView.findViewById(R.id.SellBookTitleid);
            author = itemView.findViewById(R.id.SellBookAuthorid);
            category = itemView.findViewById(R.id.SellBookCategoryid);
            price = itemView.findViewById(R.id.SellBookPriceid);
            description = itemView.findViewById(R.id.SellBookDescriptionid);
            imageUrl = itemView.findViewById(R.id.SellBookImageUrlid);
            userid = itemView.findViewById(R.id.SellBookuserid);
            useremail = itemView.findViewById(R.id.SellBookUserEmailid);
            btn = itemView.findViewById(R.id.SellBookButtonid);
        }

    }

}
