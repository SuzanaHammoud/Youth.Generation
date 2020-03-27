package Adaptors;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.youthgeneration.R;

import java.util.ArrayList;

import ModelJavaClass.SellBookModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class TopSellBooksAdapter extends RecyclerView.Adapter<TopSellBooksAdapter.ViewHolder> {

    private static final String TAG = "BooksWidgetsAdapterTopV";

    // vars
    private Context mContext;
    RecyclerView clickrecylerview;

    public TopSellBooksAdapter() {

    }

    public TopSellBooksAdapter(Context mContext, RecyclerView anyrecylerview) {
        this.clickrecylerview = anyrecylerview;
        this.mContext = mContext;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        String[] imageUrl = new String[2];
        imageUrl[0] = "https://middleeast-business.com/wp-content/uploads/2019/06/library-2.jpg";
        imageUrl[1] = "Sell Books";

        Glide.with(mContext)
                .asBitmap()
                .load(imageUrl[0])
                .into(holder.img);

        holder.txt.setText(imageUrl[1]);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 clickrecylerview.setAdapter( new BottomSellBookAdapter(mContext));

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
