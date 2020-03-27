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

import de.hdodenhof.circleimageview.CircleImageView;

public class BottomDefaultAdapter extends RecyclerView.Adapter<BottomDefaultAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> imgList = new ArrayList<>();
    private ArrayList<String> titleList = new ArrayList<>();
    private ArrayList<String> newsList = new ArrayList<>();



    /**
     * randomNumber() returns a random number for our arraylist
     */
    public int randomNumber(){
        double number;
        number = Math.random() * 4;
        return (int) number;
    }

    public BottomDefaultAdapter(Context mContext, ArrayList<String> imgList, ArrayList<String> titleList, ArrayList<String> newsList) {
        this.mContext = mContext;
        this.imgList = imgList;
        this.titleList = titleList;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modeldefault,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(imgList.get(randomNumber()))
                .into(holder.img);

        holder.title.setText(titleList.get(randomNumber()));
        holder.news.setText(newsList.get(randomNumber()));

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, news;
        private CircleImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.deafultImgid);
            title = itemView.findViewById(R.id.defaultTitleid);
            news = itemView.findViewById(R.id.defaultNewsid);
        }
    }
}
