package com.example.hemamostafa.hatly_1;

        import android.content.Intent;
        import android.content.pm.PackageInfo;
        import android.content.pm.PackageManager;
        import android.content.pm.Signature;

        import android.support.annotation.NonNull;

        import android.os.Bundle;
        import android.util.Base64;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.hemamostafa.hatly_1.Base.MyBaseActivity;
        import com.facebook.AccessToken;
        import com.facebook.CallbackManager;
        import com.facebook.FacebookCallback;
        import com.facebook.FacebookException;
        import com.facebook.login.LoginResult;
        import com.facebook.login.widget.LoginButton;


        import com.google.android.gms.auth.api.signin.GoogleSignIn;
        import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
        import com.google.android.gms.auth.api.signin.GoogleSignInClient;
        import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
        import com.google.android.gms.common.api.ApiException;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthCredential;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FacebookAuthProvider;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.auth.GoogleAuthProvider;

        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;


public class LoginScreen extends MyBaseActivity implements View.OnClickListener {

    private Button facebook_btn,login_btn;
    private TextView forgetPassword ;
    private TextView gotoSignup;
    private GoogleSignInOptions gso;  // Google XML Button
    private FirebaseAuth mAuth;       //Authentication object
    private GoogleSignInClient mGoogleSignInClient;  // object to Complete Google Sign-in Authentication
    final private int RC_SIGN_IN=150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        inititViews();
        mAuth = FirebaseAuth.getInstance();// Initialize Firebase Auth
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
         mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);

         findViewById(R.id.SignInButton).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 signIn();
             }
         });

    }



    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
           Toast.makeText(activity,"You are Log-in"+ currentUser.getEmail(),Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(activity,"You are Log-out",Toast.LENGTH_SHORT).show();
    }

    private void updateUI(FirebaseUser currentUser) {

        Toast.makeText(activity,"UpdatedUI Function  ",Toast.LENGTH_SHORT).show();
        Toast.makeText(activity,currentUser.getDisplayName(),Toast.LENGTH_SHORT).show();
        Toast.makeText(activity,currentUser.getEmail(),Toast.LENGTH_SHORT).show();


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.e("log-in", "Google sign in successfully part one : ");
                firebaseAuthWithGoogle(account);
                Log.e("", "Google sign in successfully part two : ");
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.e("log-in", "Google sign in failed and this is the error : "+e);

            }

        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        Log.e("log-in", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("log-in", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("log-in", "signInWithCredential:failure", task.getException());
                            // Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }


    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }





    public void signOut(View view){
        FirebaseAuth.getInstance().signOut();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                startActivity(new Intent(LoginScreen.this, MyNewHome.class));
                break;
            case R.id.forgetPassword_txt:
                startActivity(new Intent(activity, ResetPassword.class));
                 break;
            case R.id.dontHaveAccount:
                startActivity(new Intent(activity, SignUp_Screen.class));
                break;
            case R.id.SignInButton:
                signIn();
                break;


        }
    }

        private void inititViews(){
            login_btn=findViewById(R.id.login_btn);
            facebook_btn = findViewById(R.id.fb_btn);
            forgetPassword = findViewById(R.id.forgetPassword_txt);
            gotoSignup=findViewById(R.id.dontHaveAccount);
        }

        }
