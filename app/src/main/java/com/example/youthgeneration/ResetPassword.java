package com.example.youthgeneration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.youthgeneration.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    EditText emailFor;
    Button changePassBtn;
    ProgressBar progressBarFor;
    private FirebaseAuth auth;
    //call Oncreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        emailFor = (EditText) findViewById(R.id.emailForget);
        changePassBtn = (Button) findViewById(R.id.changePassBtn);
        progressBarFor = (ProgressBar) findViewById(R.id.progressBarFor);
        Toolbar toolbar = (Toolbar)findViewById(R.id.forPassToolbar);//set toolbar id
        auth = FirebaseAuth.getInstance();
        //set onClickListener method
        changePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String emailIdFor = emailFor.getText().toString();
                if (TextUtils.isEmpty(emailIdFor)) {
                    //show the toast
                    Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                    return;
                }
                //set the visibility
                progressBarFor.setVisibility(View.VISIBLE);
                //set the forget password mail
                auth.sendPasswordResetEmail(emailIdFor).addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            //if task is successful, shows the message
                            Toast.makeText(ResetPassword.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            //if task unsucessful, shows the message
                            Toast.makeText(ResetPassword.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }
                        //set the gone visibility to progressBar
                        progressBarFor.setVisibility(View.GONE);
                    }
                });
            }
        });

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp); //set navigation back
        //call setOnclickListener method
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}