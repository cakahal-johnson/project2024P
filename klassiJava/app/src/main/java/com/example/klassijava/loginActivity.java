package com.example.klassijava;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {

    private static final String TAG = "GOOGLEACTIVITY";
    private  static final int RC_SIGN_IN = 9001;

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private SignInButton signInButton;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = findViewById(R.id.sign_in_button);

        // goto firebase Doc at the left Tab => Authentication => google sign in and copy the Auth
        //which is below
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleSignInClient = mGoogleSignInClient.getClient(this, gso);
        mFirebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(loginActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Signing you in... Please Wait");

        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                signIn();
            }
        });


    }

    private void signIn() {
        //goto the doc n copy
        //which is here
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if(mFirebaseUser != null){
            Log.d(TAG, "User is already logged in");
            startActivity(new Intent(loginActivity.this, MainActivity.class));
            finish();
        }
    }

    // from the doc copy public void onActivityResult...
    //which is here

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Result returned from lunching the Intent from GoogleSignInApi.getSignInIntent(...)
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                //Google Sign in was successful, authenticate with firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account); // goto doc to copy this class

            }catch (ApiException e){
                //Google Sign in failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    // which is here firebaseAuthWithGoogle(account) above
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){
        Log.d(TAG, "firebaseAuthWIthGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new MediaPlayer.OnCompletionListener<AuthResult>(){
            @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                if(task.isSuccessful()){
                    //SIgn in success. update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential: success");
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();

                }else{
                    //if sign in fails. display a message to the user
                    Lod.w(TAG, "signInWithCredentials:failure", tast.getException());

                }
                //.....
            }
        });
    }
    // from the firebase Authentication setting enable email password and Google then save


    //// END OF CLASSES

}