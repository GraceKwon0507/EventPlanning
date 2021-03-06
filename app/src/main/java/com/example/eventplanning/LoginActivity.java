package com.example.eventplanning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventplanning.databinding.ActivityLoginBinding;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;


public class LoginActivity extends AppCompatActivity {
    //StringBuilder data = new StringBuilder();
    int counter = 3;  //count number of attempts
    int leftAttempts = 3;

    CallbackManager callbackManager;
    LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setLoginViewModel(new LoginViewModel());
        activityLoginBinding.executePendingBindings();

//        AccessToken accessToken = AccessToken.getCurrentAccessToken ();
//        boolean isLoggedIn = accessToken != null && ! accessToken.isExpired ();

        final EditText username = (EditText) findViewById(R.id.textUserName);
        final EditText password = (EditText) findViewById(R.id.textPassword);

        final Button submit = (Button) findViewById(R.id.submitButton);
        final Button register = (Button) findViewById(R.id.registerButton);
        final Button facebookLoginButton = (Button) findViewById(R.id.facebookLoginButton);
        
        //Facebook login
        FacebookSdk.sdkInitialize(LoginActivity.this);
        callbackManager = CallbackManager.Factory.create();
        facebookLogin();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!username.getText().toString().equals("") && !password.getText().toString().equals("")){
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference userCredentialsDatabase = database.getReference("User");

                userCredentialsDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(username.getText().toString().equals(snapshot.child(username.getText().toString()).child("credentials").child("username").getValue())
                                && password.getText().toString().equals(snapshot.child(username.getText().toString()).child("credentials").child("userPassword").getValue())){
                            Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else{
                            Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_LONG).show();
                            showMessage(); //call showMessage() to trigger alert dialog message
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            } else if(username.getText().toString().equals("") && !password.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Please enter your username", Toast.LENGTH_LONG).show();
            } else if(!username.getText().toString().equals("") && password.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(getApplicationContext(), "Please enter your username and password", Toast.LENGTH_LONG).show();
            }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CredentialsActivity.class);
                startActivity(intent);
            }
        });

        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginManager.logInWithReadPermissions(
                        LoginActivity.this,
                        Arrays.asList(
                                "email",
                                "public_profile",
                                "user_birthday"));
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("User-Information");
    }

    public void facebookLogin()
    {
        loginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();

        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        if (object != null) {
                            try {
                                String name = object.getString("name");
                                String email = object.getString("email");
                                String fbUserID = object.getString("id");

                                disconnectFromFacebook();

                                // do action after Facebook login success
                                // or call your API
                            }
                            catch (NullPointerException | JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString(
                        "fields",
                        "id, name, email, gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel()
            {
                Log.v("LoginScreen", "---onCancel");
            }

            @Override
            public void onError(FacebookException error)
            {
                // here write code when get error
                Log.v("LoginScreen", "----onError: " + error.getMessage());
            }
        });
    }

    public void disconnectFromFacebook()
    {
        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(),
                "/me/permissions/",
                null,
                HttpMethod.DELETE,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse graphResponse)
                    {
                        LoginManager.getInstance().logOut();
                    }
                })
                .executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // add this line
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void showMessage () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        leftAttempts = counter - 1;
        builder.setCancelable(true);
        builder.setTitle("Password Tries remaining");
        builder.setMessage("Tries left => " + leftAttempts);
        builder.show();
    }
}