package com.example.youthgeneration;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ModelJavaClass.BookTemplateCreated;
import ModelJavaClass.SellBookModel;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference ref;
    private ArrayList <SellBookModel> booklist = new ArrayList<>();

    public interface DataStatus{

        void DataisLoaded(ArrayList<SellBookModel> booklist, ArrayList<String> keyslist);
//        void DataisInserted();
//        void DataisUpdated();
//        void DataisDeleted();
    }


    public FirebaseDatabaseHelper() {

        mDatabase = FirebaseDatabase.getInstance();
        ref = mDatabase.getReference("BooksToSell");
    }

    public void readBooks(final DataStatus dataStatus){

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
                    SellBookModel books = dss.getValue(SellBookModel.class);
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
