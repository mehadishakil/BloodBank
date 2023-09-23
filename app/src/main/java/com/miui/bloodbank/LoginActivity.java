package com.miui.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.miui.bloodbank.databinding.ActivityLoginBinding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    //viewBinding
    ActivityLoginBinding loginBinding;
    private final String loginUrl = "http://10.0.2.2/bloodbank/login.php";
    EditText mobile, password;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //viewBinding
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());


        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        loginBinding.tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });



        //when open activity first check sharedPreference
        String mbl = sharedPreferences.getString(KEY_MOBILE, null);
        String pass = sharedPreferences.getString(KEY_PASSWORD, null);

        if(mbl != null && pass != null)
        {
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent);
        }



        loginBinding.loginBtnIDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobile = findViewById(R.id.mobileID_L);
                password = findViewById(R.id.passwordID_L);
                String qry = "?mobile="+mobile.getText().toString().trim()+"&password="+password.getText().toString().trim();

                class dbProcess extends AsyncTask<String, Void, String>
                {
                    protected void onPostExecute(String data)
                    {
                        if(data.equals("found"))
                        {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(KEY_MOBILE, mobile.getText().toString());
                            editor.putString(KEY_PASSWORD, password.getText().toString());
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mobile.setText("");
                            password.setText("");
                            Toast.makeText(LoginActivity.this, data, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    protected String doInBackground(String... params) {
                        String furl = params[0];

                        try {
                            URL url = new URL(furl);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                            return br.readLine();

                        }catch (Exception ex)
                        {
                            return ex.getMessage();
                        }
                    }
                }

                dbProcess obj = new dbProcess();
                obj.execute(loginUrl+qry);

            }
        });




    }
}