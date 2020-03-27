package com.example.youthgeneration;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adaptors.ReservedBooksAdapter;
import ModelJavaClass.BookTemplateCreated;

public class BooksReserved extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference ref;
    private ArrayList<String> key = new ArrayList<>();
    private ArrayList <BookTemplateCreated> book = new ArrayList<>();
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);
        recyclerView = findViewById(R.id.mybooksRecyclerviewid);

        new FireBaseMyBooks().readBooks(new FireBaseMyBooks.DataStatus() {
            @Override
            public void DataisLoaded(ArrayList<BookTemplateCreated> booklist, ArrayList<String> keyslist) {

                recyclerView.setLayoutManager(new LinearLayoutManager(BooksReserved.this, LinearLayoutManager.VERTICAL,false));
                adapter =  new ReservedBooksAdapter(booklist,BooksReserved.this);
                new ItemTouchHelper(itemTouch).attachToRecyclerView(recyclerView);
                recyclerView.setAdapter(adapter);
                book= booklist;
                key = keyslist;
            }
        });
    }

        ItemTouchHelper.SimpleCallback itemTouch = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            ref = FirebaseDatabase.getInstance().getReference("ReservedBooks").child(key.get(viewHolder.getAdapterPosition()));
            ref.removeValue();
            book.remove(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();
        }
    };
}
