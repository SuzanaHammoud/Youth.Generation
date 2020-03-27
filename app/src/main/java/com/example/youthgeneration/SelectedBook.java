package com.example.youthgeneration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.youthgeneration.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import ModelJavaClass.BookTemplateCreated;

public class SelectedBook extends AppCompatActivity {
    private static final String TAG = "SelectedBook Activity";

    private Button btn;
    private String isbn;
    private DatabaseReference ref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectedbook);
        Log.d(TAG, "onCreate: started.");

        intitatingIntent();

        btn = findViewById(R.id.SelectedBookbtnid);

        ref = FirebaseDatabase.getInstance().getReference("ReservedBooks");

        /**
         * creating new object of FirebaseLibrary class to receive the data from database
         * then compare selected book isbn within database
         * then saved the selected book details in Reserved Books
         * create a toast to show user successful reserved
         */
        new FireBaseLibrary().readBooks(new FireBaseLibrary.DataStatus() {
            @Override
            public void DataisLoaded(final ArrayList<BookTemplateCreated> booklist, ArrayList<String> keyslist) {

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = ref.push().getKey();

                        for(int i = 0; i < booklist.size(); i++){

                            if(booklist.get(i).getIsbn().equals(isbn)){

                                ref.child(id).setValue(booklist.get(i));
                                Toast.makeText(SelectedBook.this, "Book Reserved Successful", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
            }

        });
    }

    private void intitatingIntent(){
        Log.d(TAG, "intitatingIntent: checking if the intent has extra");

        // if u dont use the if statement and u try to get extras and the intent doesnt have this will make the app crash
        // so is better to check then to retrieve the data
        if(getIntent().hasExtra("imageUrl") && getIntent().hasExtra("description") && getIntent().hasExtra("isbn")){

            Log.d(TAG, "intitatingIntent: found that the intent has extras");
            String imageUrl = getIntent().getStringExtra("imageUrl");
            String description = getIntent().getStringExtra("description");
            isbn = getIntent().getStringExtra("isbn");

            // we call the method from below and fill the parameters
            setImageAndName(imageUrl,description);
        }
    }

    // after getting the data i have create a method that will save the data

    private void setImageAndName(String imageUrl, String description){
        Log.d(TAG, "setImageAndName: setting the url image and description ");

        TextView txt = findViewById(R.id.SelectedBookDescriptionid);
        ImageView img = findViewById(R.id.SelectedBookImageid);
        txt.setText(description);

        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(img);

    }
}
