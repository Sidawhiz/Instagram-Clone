package LogIn;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.no4.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import utilities.FireBaseMethods;

public class activity_signup extends AppCompatActivity {

    private static final String TAG = "activity_signup";

    //-------------firebase-----------
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FireBaseMethods firebaseMethods;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private ProgressBar mProgressBar;
    EditText mEmail;
    EditText mUsername;
    EditText mPassword;
    private Button btnSignup;
    private String email, password, username;
    private Context mContext;
    private int rat;

    private String append = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Log.d(TAG, "onCreate: Signup Page");

        mContext = activity_signup.this;
        firebaseMethods = new FireBaseMethods(mContext);

        initializewidgets();
        setupFirebaseAuth();
        init();

    }



    private void initializewidgets()
    {
        Log.d(TAG, "initializewidgets: Naming done");
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        mUsername = (EditText) findViewById(R.id.input_username);
        btnSignup = (Button) findViewById(R.id.sign_up_btn);
        mProgressBar = (ProgressBar) findViewById(R.id.SignUp_request_progressBar);
        mProgressBar.setVisibility(View.GONE);
    }

    private void init(){
        btnSignup.setOnClickListener(v -> {
            email = mEmail.getText().toString().trim();
            username = mUsername.getText().toString().trim();
            password = mPassword.getText().toString().trim();

            if(checkInputs(email, username, password)){
                mProgressBar.setVisibility(View.VISIBLE);

                firebaseMethods.registerNewEmail(email, password);
            }
        });
    }

    private boolean checkInputs(String email, String username, String password){
        Log.d(TAG, "checkInputs: checking inputs for null values.");
        if(email.equals("") || username.equals("") || password.equals("")){
            Toast.makeText(mContext, "All fields must be filled out.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }




/*
    -----------------------------------Firebase Authentication Part ---------------------------------
     */

    private void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();

            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in: I'm so happy" + user.getUid());

            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out");
            }
            // ...
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener!=null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}

