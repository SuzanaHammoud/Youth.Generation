package com.example.youthgeneration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import Adaptors.BottomBuyBookAdapter;
import Adaptors.BottomDefaultAdapter;
import Adaptors.TopBuyBooksAdapter;
import Adaptors.TopLibraryBooksAdapter;
import Adaptors.TopSellBooksAdapter;
import ModelJavaClass.BookTemplateCreated;
import ModelJavaClass.SellBookModel;

public class Books extends AppCompatActivity {

    private static final String TAG = "Books Activity";

    private ArrayList<String> sellbookkeys = new ArrayList<>();
    private ArrayList<String> librarybookkeys = new ArrayList<>();

    ArrayList<String> imgList = new ArrayList<>();
    ArrayList<String> titleList = new ArrayList<>();
    ArrayList<String> newsList = new ArrayList<>();

    private RecyclerView bottomRecyclerview;
    private RecyclerView topRecyclerViewLibrary, topRecyclerViewBuyBooks, topRecyclerViewSellBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleviews);

        Log.d(TAG, "onCreate: A fost initializat");

        topRecyclerViewBuyBooks = findViewById(R.id.Books_TopRecylerView2);
        topRecyclerViewLibrary = findViewById(R.id.Books_TopRecylerView1);
        topRecyclerViewSellBooks = findViewById(R.id.Books_TopRecylerView3);
        bottomRecyclerview = findViewById(R.id.Books_bottomRecyclerid);

        topRecyclerViewAdapterSell();
        preCreatedModels();

        new FirebaseDatabaseHelper().readBooks(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataisLoaded(ArrayList<SellBookModel> booklist, ArrayList<String> keyslist) {
                topRecyclerViewBuyBooks.setLayoutManager(new LinearLayoutManager(Books.this, LinearLayoutManager.HORIZONTAL, false));
                sellbookkeys = keyslist;
                topRecyclerViewBuyBooks.setAdapter(new TopBuyBooksAdapter(Books.this, bottomRecyclerview,booklist));
            }

//            @Override
//            public void DataisInserted() {
//
//            }
//
//            @Override
//            public void DataisUpdated() {
//
//            }
//
//            @Override
//            public void DataisDeleted() {
//
//            }
        });

        new FireBaseLibrary().readBooks(new FireBaseLibrary.DataStatus() {
            @Override
            public void DataisLoaded(ArrayList<BookTemplateCreated> booklist, ArrayList<String> keyslist) {
                topRecyclerViewLibrary.setLayoutManager(new LinearLayoutManager(Books.this, LinearLayoutManager.HORIZONTAL, false));
                librarybookkeys = keyslist;
                topRecyclerViewLibrary.setAdapter(new TopLibraryBooksAdapter(Books.this,bottomRecyclerview,booklist));

            }

        });


    }

    private void topRecyclerViewAdapterSell() {

        Log.d(TAG, "initRecylerView: recylerview is initiating");

        topRecyclerViewSellBooks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        topRecyclerViewSellBooks.setAdapter(new TopSellBooksAdapter(this, bottomRecyclerview));


        bottomRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        bottomRecyclerview.setAdapter(new BottomDefaultAdapter(this, imgList,titleList,newsList));




    }

    /**
     * preCreatedModels() pre chosen img, title and news.
     */

    public void preCreatedModels(){

        imgList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRbYfVQSIbOaKpBFYt4kD2whCSKwzsdOTlYzzKvrRkH5Dz4D6iu");
        imgList.add("https://i.imgur.com/D5hHW.jpg");
        imgList.add("https://i.pinimg.com/originals/5b/d2/7d/5bd27d00b0c0f6f4290b3b07254006a1.jpg");
        imgList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQezOb75VINgCbA5FGNY44KHDEHsFXQ987KLFKzJvd3qmjetpWK");

        // ------------------------------------------------------------------------------------------------------------------------------

        titleList.add("Welcome dear Student");
        titleList.add("Only happy people are allowed");
        titleList.add("No worries everything is going to be fine");
        titleList.add("Don't love yourself more than books");

        //--------------------------------------------------------------------------------------------------------------------------------

        newsList.add("Councils are legally obligated to provide libraries with free book loans, but their cuts are often triggered by central government funding restrictions. Many libraries remaining open are suffering from cutbacks in other ways, such as reduced opening hours, staffing and maintenance.");
        newsList.add("Libraries are much more than buildings full of books - they are places to relax and escape manic reality, if only for a little while. There are some beautiful libraries around the world, many with stunning architecture to admire whether you are a book lover or not.");
        newsList.add("Library supporters across the country have been setting up petitions and protest groups to fight the cuts. If you want to get involved, contact your local councillor to let them know why libraries are so important and check on social media for campaigns to join. ");
        newsList.add("Libraries contain worlds and I was a space traveller. In Plymouth, where I first stepped through the doors, there was a reading scheme which culminated in being given a small embossed shield by Patrick Moore, who was nearly crushed to death by overexcited children at the handing-out ceremony.");

    }



}
