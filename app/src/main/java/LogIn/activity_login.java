package LogIn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.no4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Home.MainActivity;

public class activity_login extends AppCompatActivity {

    private static final String TAG = "activity_login";

    // Firebase stuff
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    private Context mContext;
    private ProgressBar mProgressBar;
    private EditText mEmail;
    private EditText mPassword;
    private Button btnLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: Login Page");


        mContext = activity_login.this;
        mProgressBar = (ProgressBar) findViewById(R.id.login_request_progressBar);
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        btnLogin = (Button) findViewById(R.id.login_btn);
        mProgressBar.setVisibility(View.GONE);  // makes progressbar disappear as soon as activity is opened

        setupFirebaseAuth();
        init();

    }

    private boolean isStringNull(String string){
        Log.d(TAG, "isStringNull: checking string if null.");

        if(string.equals("")){
            return true;
        }
        else{
            return false;
        }
    }


    /*
    -----------------------------------Firebase ---------------------------------
     */

    private void init(){

        //initialize the button for logging in
        btnLogin.setOnClickListener(v -> {
            Log.d(TAG, "onClick: attempting to log in.");

            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString();

            if(isStringNull(email) && isStringNull(password)){
                Toast.makeText(mContext, "You must fill out all the fields", Toast.LENGTH_SHORT).show();
            }
            else{
                mProgressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(activity_login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "signInWithEmail:failed", task.getException());

                                    Toast.makeText(activity_login.this, getString(R.string.auth_failed),
                                            Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Log.d(TAG, "signInWithEmail: successful login");
                                    Toast.makeText(activity_login.this, getString(R.string.auth_success),
                                            Toast.LENGTH_SHORT).show();
                                }
                                mProgressBar.setVisibility(View.GONE);

                                // ...
                            }
                        });
            }

        });

        TextView linkSignUp = (TextView) findViewById(R.id.sign_up);
        linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to register screen");
                Intent intent = new Intent(activity_login.this, activity_signup.class);
                startActivity(intent);
            }
        });

         /*
         If the user is logged in then navigate to HomeActivity and call 'finish()'
          */
        if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(activity_login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }



    /*
    -----------------------------------Firebase Authentication Part ---------------------------------
     */
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();

            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in: " + user.getUid());
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
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
