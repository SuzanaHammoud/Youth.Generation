package com.example.youthgeneration;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ModelJavaClass.BookTemplateCreated;

public class FireBaseMyBooks {

    private FirebaseDatabase mDatabase;
    private DatabaseReference ref;
    private ArrayList<BookTemplateCreated> booklist = new ArrayList<>();

    public interface DataStatus{

        void DataisLoaded(ArrayList<BookTemplateCreated> booklist, ArrayList<String> keyslist);
//        void DataisInserted();
//        void DataisUpdated();
//        void DataisDeleted();
    }


    public FireBaseMyBooks() {

        mDatabase = FirebaseDatabase.getInstance();
        ref = mDatabase.getReference("ReservedBooks");
    }

    public void readBooks(final FireBaseMyBooks.DataStatus dataStatus){

        /**
         * create a firebase query, this one is select all
         * after retrieve all the information from database, store it into Array list
         */

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                booklist.clear();
                ArrayList<String> keys = new ArrayList<>();

                for (DataSnapshot dss : dataSnapshot.getChildren()){
                    keys.add(dss.getKey());
                    BookTemplateCreated books = dss.getValue(BookTemplateCreated.class);
                    booklist.add(books);
                }
                dataStatus.DataisLoaded(booklist,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
