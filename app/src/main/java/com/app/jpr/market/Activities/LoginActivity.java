package com.app.jpr.market.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.jpr.market.R;
import com.app.jpr.market.models.LoginResponse;
import com.app.jpr.market.retrofit.RestClient;
import com.app.jpr.market.utils.Utils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextView signup;
    private EditText email, password;
    private Button login;

   /* private ImageView imageView;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = findViewById(R.id.register_tv);
        email = findViewById(R.id.edit_email);
        password = findViewById(R.id.edit_Passwword);
        login = findViewById(R.id.loginButton);

        /*imageView = findViewById(R.id.logoID);*/


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String gmaill = email.getText().toString();
                String passworddd = password.getText().toString();
                boolean check = validateInputs(gmaill, passworddd);


                if (check) {
                    if (Utils.isInternetConnected(LoginActivity.this)) {
                        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), gmaill);
                        RequestBody pwd = RequestBody.create(MediaType.parse("text/plain"), passworddd);

                        //TODO  display progress dialog
                        Utils.showProgressDialog(LoginActivity.this,"Please wait...");

                        RestClient.loginUser(email, pwd, new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                                switch (response.code()) {
                                    case 200:
                                       Utils.dismissProgressDialog();
                                        LoginResponse loginResponse = response.body();
                                        if (loginResponse.getStatus().equalsIgnoreCase("true")) {
                                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                            intent.putExtra("USERNAME", "gmaii");
                                            intent.putExtra("PASSWORD", "password");
                                            startActivity(intent);
                                            finish();

                                        } else {
                                            Toast.makeText(LoginActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case 500:

                                        break;
                                    default:
                                }
                            }

                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                Log.d("Fail", call.toString());
                               Utils.dismissProgressDialog();
                            }
                        });


                    } else {
                        Toast.makeText(LoginActivity.this, "no Internet connection", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);

            }
        });
    }////

    private boolean validateInputs(String gmaill, String passworddd) {
        boolean check = true;

        if (!Patterns.EMAIL_ADDRESS.matcher(gmaill).matches()) {
            email.setError("Field is empty");
            check = false;
        }


        if (passworddd.length() < 6) {
            password.setError("enter more than 6 charater");
            check = false;
        }
        return check;
    }
}////
