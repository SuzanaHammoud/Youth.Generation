package com.example.youthgeneration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.youthgeneration.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText stdIdr,fNamer,sNamer,emailr,passr,cPassr;
    private Button register;
    private ProgressBar progressBar;
    DatabaseReference databaseReference;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar)findViewById(R.id.registerToolbar);//set toolbar id
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp); //set navigation back

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        auth = FirebaseAuth.getInstance();

        register = (Button) findViewById(R.id.registerBtn);
        stdIdr = (EditText) findViewById(R.id.studentid);
        fNamer = (EditText) findViewById(R.id.fName);
        sNamer = (EditText) findViewById(R.id.sName);
        emailr = (EditText) findViewById(R.id.email);
        passr = (EditText) findViewById(R.id.pass);
        cPassr = (EditText) findViewById(R.id.cPass);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        databaseReference = FirebaseDatabase.getInstance().getReference("Student");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = emailr.getText().toString().trim();
                final String stdId = stdIdr.getText().toString().trim();
                final String fName = fNamer.getText().toString().trim();
                final String sName = sNamer.getText().toString().trim();
                String pass = passr.getText().toString().trim();
                String cPass = cPassr.getText().toString().trim();

                if (stdId.isEmpty())
                {

                    stdIdr.setError("Please enter your student id");
                }
                if(fName.isEmpty())
                {

                    fNamer.setError("Please enter your first name");
                }
                if (sName.isEmpty())
                {

                    sNamer.setError("Please enter your last name");
                }
                if (email.isEmpty())
                {

                    emailr.setError("Please enter your email id");
                }
                if (pass.isEmpty())
                {

                    passr.setError("Please enter password");
                }
                if (cPass.isEmpty())
                {

                    cPassr.setError("Please enter confirm password");
                }
                if(pass.length() < 4 && pass.length() > 8)
                {

                    passr.setError("length should be greater than 4 and less than or equal to 8");
                }
                if(!cPass.equals(pass))
                {

                    cPassr.setError("Password should be matched");
                }

                progressBar.setVisibility(View.VISIBLE);

                //create user
                auth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds the auth state listener will be notified and logic to handle the signed in user can be handled in the listener.
                                if (task.isSuccessful()) {
                                    com.example.youthgeneration.Student info = new com.example.youthgeneration.Student(
                                            stdId,
                                            fName,
                                            sName,
                                            email
                                    );
                                    FirebaseDatabase.getInstance().getReference("Student")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(Register.this, "Register Successful", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(Register.this, Login.class));
                                        }
                                    });
                                }
                            }
                        });

            }
        });
    }
    //call onResume method
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);

    }
}
