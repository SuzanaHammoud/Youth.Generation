package com.example.youthgeneration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.youthgeneration.MainActivity;
import com.example.youthgeneration.R;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    String url;

    public void clicked_btn(String url) {

        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }


    //call OnCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar)findViewById(R.id.dashboardToolbar);//set toolbar id
        setSupportActionBar(toolbar);
        toolbar.getOverflowIcon().setColorFilter(Color.WHITE , PorterDuff.Mode.SRC_ATOP);

        findViewById(R.id.mymoodle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                clicked_btn( "http://partnerships.moodle.roehampton.ac.uk/login/index.php");
            }

        });

        Button btn = (Button)findViewById(R.id.library);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, MainActivity.class));
            }
        });

    }
    //create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    //show menu item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                //call intent activity for logout
                Intent i = new Intent(Dashboard.this, MainActivity.class);
                startActivity(i);
            default:
                return false;
        }

    }

}
