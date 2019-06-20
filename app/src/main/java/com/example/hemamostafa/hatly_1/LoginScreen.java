package com.example.hemamostafa.hatly_1;

        import android.content.Intent;

        import android.support.annotation.NonNull;

        import android.os.Bundle;
        import android.util.Log;
        import android.util.Patterns;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.hemamostafa.hatly_1.Base.MyBaseActivity;


        import com.facebook.AccessToken;
        import com.facebook.AccessTokenTracker;
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


public class LoginScreen extends MyBaseActivity implements View.OnClickListener {

    private Button loginWithMailBtn,facebookBtn_2;
    private TextView forgetPassword ;
    private TextView gotoSignup;
    private EditText email,password;
    private GoogleSignInOptions gso;  // Google XML Button
    private FirebaseAuth mAuth;       //Authentication object
    private GoogleSignInClient mGoogleSignInClient;  // object to Complete Google Sign-in Authentication
    final private int RC_SIGN_IN=150;
    private String stringMail;
    private String stringPassword;
    // All Facebook Compponant
    private CallbackManager mCallbackManager;
    private LoginButton facebookBtn;
    private AccessTokenTracker accessTokenTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        mCallbackManager = CallbackManager.Factory.create();
        inititViews();


        //FacebookSdk.sdk
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

                if (currentAccessToken == null) {
                    mAuth.signOut();
                }
            }
        };

        facebookBtn= findViewById(R.id.facebook_gone_btn);
        facebookBtn.setReadPermissions("email", "public_profile");
        facebookBtn.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(activity,"Facebook Success",Toast.LENGTH_SHORT).show();
                handleFacebookAccessToken(loginResult.getAccessToken());


            }

            @Override
            public void onCancel() {
                Toast.makeText(activity,"Facebook Cancel",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(activity,error +"",Toast.LENGTH_SHORT).show();


            }
        });
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
         mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);

    }



    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            Toast.makeText(activity,"You are Log-in"+ currentUser.getEmail(),Toast.LENGTH_SHORT).show();
            startActivity(new Intent(activity, MyNewHome.class));
        }
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
        mCallbackManager.onActivityResult(requestCode,resultCode,data);

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
            case R.id.login_with_mail_password:
               validateLoginUser();
                break;
            case R.id.forgetPassword_txt:
                startActivity(new Intent(activity, ResetPassword.class));
                 break;
            case R.id.dontHaveAccount:
                startActivity(new Intent(activity, SignUp_Screen.class));
                break;
            case R.id.fb_btn:
                Toast.makeText(activity,"fb_btn Clicked",Toast.LENGTH_SHORT).show();
                buttonClickLoginFB();
                break;

        }
    }

    public  void buttonClickLoginFB(){

        facebookBtn.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(activity,"Facebook Success",Toast.LENGTH_SHORT).show();
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(activity,"Facebook Cancel",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(activity,error +"",Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void handleFacebookAccessToken(AccessToken token) {
       // Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(activity, "You Are Log in With Facebook.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(activity,MyNewHome.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(activity, "Facebook SignUp Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void inititViews(){
        // Initialize Firebase objects
            mAuth = FirebaseAuth.getInstance();
            loginWithMailBtn =findViewById(R.id.login_with_mail_password);
            facebookBtn_2=findViewById(R.id.fb_btn);
            forgetPassword = findViewById(R.id.forgetPassword_txt);
            gotoSignup=findViewById(R.id.dontHaveAccount);
            email=(EditText)findViewById(R.id.editTextEmail);
            password=(EditText)findViewById(R.id.editTextPassword);
            loginWithMailBtn.setOnClickListener(this);
            facebookBtn_2.setOnClickListener(this);

        }

    private void validateLoginUser() {
        stringMail = email.getText().toString();
        stringPassword = password.getText().toString();

        if (stringMail.isEmpty()) {
            email.setError(getString(R.string.input_error_name));
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(stringMail).matches()) {
            email.setError(getString(R.string.input_error_email_invalid));
            email.requestFocus();
            return;
        }
        if (stringPassword.isEmpty()) {
            password.setError(getString(R.string.input_error_name));
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(stringMail, stringPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(activity, "Log-in Succesfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent( activity,MyNewHome.class));

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(activity, "Log-in  failed.",Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
        }
