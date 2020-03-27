package com.example.youthgeneration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.youthgeneration.R;

public class RLPage extends AppCompatActivity {
    Button register;
    Button login;
    //call Oncreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_l_page);
        register = (Button)findViewById(R.id.register);//get the button id
        login = (Button)findViewById(R.id.loginActivity);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RLPage.this, Register.class);//when user click on register button, it locate register page
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RLPage.this, com.example.youthgeneration.Login.class);//when user click on Login button, it locate Login page
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed()
    {


        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(RLPage.this);


        builder.setMessage("Do you want to exit ?");


        builder.setTitle("Alert !");


        builder.setCancelable(false);


        builder
                .setPositiveButton(
                        "Yes",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {


                                finish();
                            }
                        });


        builder.setNegativeButton(
                "No",
                new DialogInterface
                        .OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which)
                    {


                        dialog.cancel();
                    }
                });


        AlertDialog alertDialog = builder.create();


        alertDialog.show();
    }
}
