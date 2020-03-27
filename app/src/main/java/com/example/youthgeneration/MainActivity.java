package com.example.youthgeneration;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1= findViewById(R.id.mainbutton);
        btn2 = findViewById(R.id.reservedbtnid);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    navToLibrary();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navToMyBooks();
            }
        });

    }

    public void navToLibrary(){
        Intent i = new Intent(this, Books.class);
        startActivity(i);
    }

    public void navToMyBooks(){
        Intent i = new Intent(this,BooksReserved.class);
        startActivity(i);
    }
}
